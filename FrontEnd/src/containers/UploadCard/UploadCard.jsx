import React, { useState } from "react";
import styles from "./UploadCard.module.css";

export default function UploadCard({ onResult }) {
  const [file, setFile] = useState(null);
  const [fileName, setFileName] = useState("");
  const [loading, setLoading] = useState(false);

  const handleFileChange = (e) => {
    const selectedFile = e.target.files[0];
    if (selectedFile) {
      setFile(selectedFile);
      setFileName(selectedFile.name);
    }
  };

  const handleUpload = async () => {
    if (!file) {
      alert("Please select a file");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      setLoading(true);

      const response = await fetch(
        "http://localhost:8080/api/document/upload",
        {
          method: "POST",
          body: formData,
        }
      );

      const data = await response.json();
      onResult(data); // 🔥 send result to HomePage

    } catch (error) {
      console.error(error);
      alert("Upload failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className={styles.card}>
      <h3 className={styles.title}>Upload Driving Licence</h3>

      <label className={styles.uploadBox}>
        <input
          type="file"
          accept=".jpg,.jpeg,.pdf"
          onChange={handleFileChange}
          hidden
        />
        <span>Click to upload</span>
        <small>JPG, JPEG or PDF</small>
      </label>

      {fileName && (
        <p className={styles.fileName}>
          Selected: {fileName}
        </p>
      )}

      {/* 🔥 Upload button */}
      <button
        className={styles.uploadBtn}
        onClick={handleUpload}
        disabled={loading}
      >
        {loading ? "Processing..." : "Upload"}
      </button>
    </div>
  );
}
