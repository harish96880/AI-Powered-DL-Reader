import React from 'react'
import { useState } from "react";
import styles from './HomePage.module.css'
import Title from '../../containers/Title/Title'
import UploadCard from '../../containers/UploadCard/UploadCard'
import DisplayResult from '../../containers/DisplayResult.jsx/DisplayResult';

export default function HomePage() {
  const [result, setResult] = useState(null);
  const [error, setError] = useState("");

  const isInvalidDrivingLicense = (data) => {
  if (!data) return true;

  const { license_number } = data;
  if (!license_number || license_number.trim() === "") {
    return true;
  }

  return false;
};

  const handleResult = (data) => {
    if (isInvalidDrivingLicense(data)) {
      setError("❌ This does not appear to be a valid Driving License.");
      setResult(null);
    } else {
      setError("");
      setResult(data);
    }
  };

  return (
    <section className={styles.home}>
      <Title />
      <UploadCard onResult={handleResult} />

      {error && (
        <p className={styles.error}>
          {error}
        </p>
      )}

      {result && <DisplayResult result={result} />}
    </section>
  );
}