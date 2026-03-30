package com.example.dlReader.llm;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class LLMService {

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public String extractDrivingLicenseData(String text) {
         System.out.println("Testing 01: " + text);
        String prompt = """
        You are an AI that extracts Driving License information.

        Return ONLY valid JSON.
        Do not add explanations or extra text.

        Fields:
        document_type, name, dob, license_number, issue_date, expiry_date, address

        If a field is missing, return empty string.

        Text:
        """ + text;

//         String prompt = """
// You are a strict information extraction system.

// TASK:
// Extract Driving License information ONLY from the OCR text below.

// CRITICAL RULES:
// - Use ONLY values that explicitly appear in the OCR text.
// - Do NOT guess, infer, or generate placeholder values.
// - If a field is missing or unclear, output null.
// - Output ONLY valid JSON. No extra text.

// Return JSON in this exact schema:
// {
//   "document_type": "driving_license",
//   "name": null,
//   "dob": null,
//   "license_number": null,
//   "issue_date": null,
//   "expiry_date": null,
//   "address": null
// }

// OCR TEXT:
// <<<
// """ + text + """
// >>>
// """;


        

        try {
            // ✅ Let Jackson handle JSON safely
            Map<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("model", "llama3");
            bodyMap.put("prompt", prompt);
            bodyMap.put("stream", false);

            String body = mapper.writeValueAsString(bodyMap);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:11434/api/generate"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            return extractJsonFromOllama(response.body());

        } catch (Exception e) {
            throw new RuntimeException("LLM call failed", e);
        }
    }

    private String extractJsonFromOllama(String ollamaResponse) {
        try {
            JsonNode root = mapper.readTree(ollamaResponse);

            if (root.has("response")) {
                return root.get("response").asText();
            }

            if (root.has("message") && root.get("message").has("content")) {
                return root.get("message").get("content").asText();
            }

            if (root.has("error")) {
                throw new RuntimeException("Ollama error: " + root.get("error").asText());
            }


            throw new RuntimeException("Unknown Ollama response: " + ollamaResponse);

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Ollama response", e);
        }
    }
}
