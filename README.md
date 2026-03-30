# 🚗 AI-Powered Driving License Reader

An AI-powered system that extracts information from a Driving License (Image/PDF) using OCR and converts it into structured JSON using a self-hosted LLM.

---

## 🔥 Features

- Upload Driving License (Image/PDF)
- Text extraction using PaddleOCR
- Convert extracted text to JSON using Llama3 (LLM)
- Fast microservice-based architecture
- Supports both image and PDF inputs

---

## 🏗️ Architecture

    Frontend (Vite)
          ↓
    Spring Boot Backend
          ↓
    Python OCR Service (PaddleOCR)
          ↓
    Extracted Text
          ↓
    Llama3 (via Ollama)
          ↓
    Structured JSON Output

---

## 🛠️ Tech Stack

### Backend
- Java (Spring Boot)
- WebClient

### Frontend
- Vite + JavaScript

### OCR Service
- Python (FastAPI)
- PaddleOCR
- OpenCV

### AI / LLM
- Llama3 via Ollama

---

## 📂 Project Structure

    Backend/
    Frontend/
    Python-OCR/

---

## ⚙️ Setup Instructions

### 1. Clone Repository

    git clone <your-repo-url>
    cd project-folder

---

## 🧠 LLM Setup (Ollama + Llama3)

### Install Ollama

Download: https://ollama.com/download

Verify:

    ollama --version

### Pull Model

    ollama pull llama3

### Run Model

    ollama run llama3

Make sure Ollama runs at: http://localhost:11434

---

## 🐍 Python OCR Service Setup

    cd Python-OCR

    python -m venv ocrenv
    ocrenv\Scripts\activate

    pip install --upgrade pip
    pip install -r requirements.txt

    python -m uvicorn ocr_service:app --host 0.0.0.0 --port 8001

---

## ☕ Spring Boot Backend Setup

    cd Backend
    mvn spring-boot:run

---

## 🌐 Frontend Setup (Vite)

    cd FrontEnd

    npm install
    npm run dev

---

## 🔄 Application Flow

1. Upload file from frontend  
2. Backend sends file to Python OCR service  
3. OCR extracts raw text  
4. Backend sends text to Llama3 (Ollama)  
5. LLM returns structured JSON  

---

## ⚠️ Notes

- Use high-quality images for better OCR accuracy  
- Recommended Python version: 3.10 or 3.11  
- Ensure all services are running:

  - Backend: 8080  
  - OCR: 8001  
  - Ollama: 11434  

---

## 🙌 Author

Sriharish Ravikumar
