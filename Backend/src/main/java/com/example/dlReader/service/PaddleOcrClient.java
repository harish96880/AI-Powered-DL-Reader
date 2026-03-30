package com.example.dlReader.service;

import com.example.dlReader.dto.OcrResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PaddleOcrClient {

    private final WebClient webClient;

    public PaddleOcrClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public String extractText(MultipartFile file) {
        try {
            MultipartBodyBuilder builder = new MultipartBodyBuilder();

            // ✅ send file to FastAPI service
            builder.part("file", new ByteArrayResource(file.getBytes()) {
                        @Override
                        public String getFilename() {
                            return file.getOriginalFilename();
                        }
                    })
                    .contentType(MediaType.APPLICATION_OCTET_STREAM);

            OcrResponse response = webClient.post()
                    .uri("http://localhost:8001/ocr") // Python service
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData(builder.build()))
                    .retrieve()
                    .bodyToMono(OcrResponse.class)
                    .block();

            if (response == null || response.getText() == null) {
                throw new RuntimeException("OCR service returned empty response");
            }

            return response.getText();

        } catch (Exception e) {
            throw new RuntimeException("Failed to call PaddleOCR service", e);
        }
    }
}
