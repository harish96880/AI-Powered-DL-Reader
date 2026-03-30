package com.example.dlReader.dto;

import lombok.Data;

@Data
public class DrivingLicenseResponse {
    private String document_type;
    private String name;
    private String dob;
    private String license_number;
    private String issue_date;
    private String expiry_date;
    private String address;
}
