# 🚗 AI-Powered Driving License Reader

This project is an **AI-based document reader** that extracts information from a Driving License using OCR and converts it into structured JSON using a self-hosted LLM.

---

## 🔥 Features

* 📄 Upload Driving License (Image/PDF)
* 🔍 Extract text using **PaddleOCR**
* 🧠 Convert raw text into structured JSON using **Llama3 (self-hosted)**
* ⚡ Fast API-based architecture
* 🧩 Supports both Image and PDF files

---

## 🏗️ Architecture

```
Frontend / Postman
        ↓
Spring Boot Backend (Java)
        ↓
Python OCR Service (PaddleOCR)
        ↓
Extracted Text
        ↓
Llama3 (LLM)
        ↓
Structured JSON Output
```

---

## 🛠️ Tech Stack

### Backend

* Java (Spring Boot)
* WebClient (for API calls)

### OCR Service

* Python (FastAPI)
* PaddleOCR
* OpenCV

### AI / LLM

* Llama3 (self-hosted)

---

## 📂 Project Structure

```
Backend/
 ├── controller/
 ├── service/
 │    ├── DocumentProcessing.java
 │    ├── PaddleOcrClient.java
 ├── config/
 │    └── WebClientConfig.java

Python-OCR/
 ├── ocr_service.py
 ├── requirements.txt
```

---

## ⚙️ Setup Instructions

### 🔹 1. Clone Repository

```bash
git clone <your-repo-url>
cd project-folder
```

---

## 🐍 Python OCR Service Setup

### Step 1: Create Virtual Environment

```bash
python -m venv ocrenv
ocrenv\Scripts\activate
```

### Step 2: Install Dependencies

```bash
pip install --upgrade pip
pip install -r requirements.txt
```

### Step 3: Run OCR Service

```bash
python -m uvicorn ocr_service:app --host 0.0.0.0 --port 8001
```

---

## ☕ Spring Boot Setup

```bash
mvn spring-boot:run
```

---

## 🌐 Frontend Setup (Simple)

### Option 1: HTML

```html
<input type="file" id="file" />
<button onclick="upload()">Upload</button>

<script>
async function upload() {
  const file = document.getElementById('file').files[0];
  const formData = new FormData();
  formData.append('file', file);

  const res = await fetch('http://localhost:8080/upload', {
    method: 'POST',
    body: formData
  });

  const data = await res.text();
  console.log(data);
}
</script>
```

---

## 🔗 Flow

1. Upload file
2. Spring Boot → Python OCR
3. OCR → Text
4. LLM → JSON

---

## ⚠️ Notes

* Use original images
* Python 3.10/3.11 recommended

---

## 🙌 Author

Sriharish Ravikumar
