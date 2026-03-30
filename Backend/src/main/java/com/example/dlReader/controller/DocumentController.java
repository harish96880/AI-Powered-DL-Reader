package com.example.dlReader.controller;


import com.example.dlReader.service.DocumentProcessing;
import com.example.dlReader.llm.LLMService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/document")
@CrossOrigin(origins = "http://localhost:5173")
public class DocumentController {

    private final DocumentProcessing documentProcessing;
    private final LLMService llmService;

    public DocumentController(DocumentProcessing documentProcessing,
                              LLMService llmService) {
        this.documentProcessing = documentProcessing;
        this.llmService = llmService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {

        String extractedText = documentProcessing.extractText(file);
        String llmJson = llmService.extractDrivingLicenseData(extractedText);

        return ResponseEntity.ok(llmJson);
    }
}
