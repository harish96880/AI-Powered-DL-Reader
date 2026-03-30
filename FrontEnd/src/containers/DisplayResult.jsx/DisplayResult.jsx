import React from "react";
import styles from "./DisplayResult.module.css";

export default function DisplayResult({ result }) {
  return (
    <div className={styles.card}>
      <h3 className={styles.heading}>Extracted Driving License Details</h3>

      <div className={styles.row}>
        <span>Name</span>
        <span>{result.name || "-"}</span>
      </div>

      <div className={styles.row}>
        <span>Date of Birth</span>
        <span>{result.dob || "-"}</span>
      </div>

      <div className={styles.row}>
        <span>License Number</span>
        <span>{result.license_number || "-"}</span>
      </div>

      <div className={styles.row}>
        <span>Expiry Date</span>
        <span>{result.expiry_date || "-"}</span>
      </div>

      <div className={styles.row}>
        <span>Address</span>
        <span>{result.address || "-"}</span>
      </div>
    </div>
  );
}
