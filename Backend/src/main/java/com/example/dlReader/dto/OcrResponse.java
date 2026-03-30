package com.example.dlReader.dto;

public class OcrResponse {
    private String type;
    private String text;
    private Integer pages;

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Integer getPages() { return pages; }
    public void setPages(Integer pages) { this.pages = pages; }
}
