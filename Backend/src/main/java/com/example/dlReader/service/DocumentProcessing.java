package com.example.dlReader.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentProcessing {

    private final PaddleOcrClient paddleOcrClient;

    public DocumentProcessing(PaddleOcrClient paddleOcrClient) {
        this.paddleOcrClient = paddleOcrClient;
    }

    public String extractText(MultipartFile file) {
        String text = paddleOcrClient.extractText(file);
        return cleanText(text);
    }

    private String cleanText(String text) {
        if (text == null) return "";
        return text.replace("\r", "\n").replaceAll("\n{2,}", "\n").trim();
    }
}
