# AI Powered Driving License Document Reader (Self-Hosted LLM + PaddleOCR)

This project is an AI-powered document reader that extracts structured information from a **Driving License** document (Image/PDF).  
The core goal of this project is to build an end-to-end pipeline where:

✅ OCR extracts raw text from document  
✅ A **self-hosted LLM** processes the extracted text  
✅ Final output is returned as **structured JSON** and displayed on UI

---

## 🔥 Why This Project?

Most document reader apps rely on cloud APIs (paid services).  
In this project, I built the system using:

- **Self-hosted LLM** (runs on local machine)
- **Local OCR service** (Python PaddleOCR)
- Clean structured JSON output for UI usage

This makes the project:
- Offline friendly
- Cost-free (no paid APIs)
- Realistic and production-style (service-based architecture)

---

## ✅ Supported Document

- **Driving License** (English only)

---

## 🧠 Output JSON Format

The AI extracts only these fields:

```json
{
  "document_type": "Driving License",
  "name": "",
  "dob": "",
  "license_number": "",
  "issue_date": "",
  "expiry_date": "",
  "address": ""
}