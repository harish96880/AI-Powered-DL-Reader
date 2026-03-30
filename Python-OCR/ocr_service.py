from fastapi import FastAPI, UploadFile, File
from paddleocr import PaddleOCR
import numpy as np
import cv2
import traceback

from pdf2image import convert_from_bytes

app = FastAPI(title="PaddleOCR Service")

ocr = PaddleOCR(
    use_angle_cls=True, 
    lang="en"             
)

def preprocess(img_bgr):
    gray = cv2.cvtColor(img_bgr, cv2.COLOR_BGR2GRAY)
    gray = cv2.GaussianBlur(gray, (3, 3), 0)
    th = cv2.threshold(gray, 0, 255, cv2.THRESH_BINARY + cv2.THRESH_OTSU)[1]
    return cv2.cvtColor(th, cv2.COLOR_GRAY2BGR)



def ocr_image(img_bgr):
    result = ocr.ocr(img_bgr)
    if isinstance(result, list) and len(result) > 0 and isinstance(result[0], dict):
        rec_texts = result[0].get("rec_texts", [])
        rec_scores = result[0].get("rec_scores", [])

        lines = []
        for i, text in enumerate(rec_texts):
            text = str(text).strip()
            score = float(rec_scores[i]) if i < len(rec_scores) else 1.0
            if text:
                lines.append(text)

        return "\n".join(lines).strip()

    lines = []
    for page in result:
        for item in page:
            try:
                text = item[1][0]
                conf = item[1][1]
                if text:
                    lines.append(text)
            except:
                continue

    return "\n".join(lines).strip()\



@app.get("/")
def home():
    return {"status": "ok", "message": "PaddleOCR service running"}


@app.post("/ocr")
async def extract_text(file: UploadFile = File(...)):
    try:
        data = await file.read()
        filename = (file.filename or "").lower()

        if filename.endswith(".pdf"):
            pages = convert_from_bytes(data, dpi=300)

            all_text = []
            for i, page_img in enumerate(pages):
                img_rgb = np.array(page_img)
                img_bgr = cv2.cvtColor(img_rgb, cv2.COLOR_RGB2BGR)

                text, raw = ocr_image(img_bgr)

                if text:
                    all_text.append(text)

            final_text = "\n\n".join(all_text).strip()

            return {"type": "pdf", "pages": len(pages), "text": final_text}

        npimg = np.frombuffer(data, np.uint8)
        img_bgr = cv2.imdecode(npimg, cv2.IMREAD_COLOR)

        if img_bgr is None:
            print("ERROR: Unable to decode image")
            return {"type": "error", "text": "", "error": "Unable to decode image"}

        text = ocr_image(img_bgr)

        cv2.imwrite("debug_received.png", img_bgr)

        return {"type": "image", "text": text}

    except Exception as e:
        print("\nOCR API ERROR:", str(e))
        print(traceback.format_exc())
        return {"type": "error", "text": "", "error": str(e)}
