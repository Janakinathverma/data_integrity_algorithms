# Error Detection & Correction Algorithms (Java)

This repository contains Java implementations of various **Error Detection and Error Correction** techniques used in Data Communication and Coding Theory. Each program is clean, well-structured, and takes **user input** for better understanding of how each algorithm works.

---

## 📌 Contents

### ✔ 1D Parity (VRC)

* Generates even parity for data bits
* Detects single-bit error
* Cannot correct errors

### ✔ 2D Parity

* Takes data matrix as input
* Computes row & column parity
* Detects & **corrects** a single-bit error

### ✔ CRC (Cyclic Redundancy Check)

* User inputs data and generator polynomial
* Performs modulo-2 division (XOR-based)
* Appends CRC to data
* Detects errors in received frame

### ✔ Hamming Code (7,4)

* User inputs 4 data bits
* Generates 7-bit Hamming code
* Detects **and corrects** a single-bit error
* Syndrome calculation included

---

## 📁 Directory Structure

```
/your-directory-name
│
├── OneDParity.java
├── TwoDParity.java
├── CRC.java
├── HammingCode.java
├── ChecksumUtil.java
└── README.md
```

---

## 🚀 How to Run

Compile and run any program using:

```
javac FileName.java
java FileName
```

Each program prompts for user input and demonstrates encoding, error introduction, detection, and correction (where possible).

---

## 💡 Future Additions

You can add more techniques like:

* LRC (Longitudinal Redundancy Check)
* Hamming (8,4) SECDED

---

## 📜 License

Feel free to use and modify the code for learning or academic projects.
