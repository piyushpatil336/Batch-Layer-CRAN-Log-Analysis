# Batch Layer Implementation

## Big Data Management – Assignment 01

### Student Details

| Field | Information |
|---------|-------------|
| Name | Piyush Ashok Patil |
| Student ID | 3191958 |
| Course | MSc in Big Data Management and Analytics |
| Instructor | Dr. Aqeel Kazmi |
| Submission Date | 14 March 2026 |

---

## Introduction

This project demonstrates the implementation of **Hadoop MapReduce** to analyze **CRAN package download logs**. The dataset contains information about package downloads, including:

- Download Date
- Package Name
- Country
- Operating System

Multiple MapReduce jobs were developed to perform analytical queries and generate insights from the dataset.

---

## Environment Setup

### Operating System
- Ubuntu (Virtual Machine)

### Framework
- Apache Hadoop

### Programming Language
- Java

### Verify Hadoop Services

Run the following command to check active Hadoop services:

```bash
jps
```

Example output:

```text
NameNode
DataNode
ResourceManager
NodeManager
SecondaryNameNode
Jps
```

---

## Dataset Preparation

### Dataset Source
CRAN Package Download Logs

### Steps Performed

1. Download the dataset.
2. Extract the CSV file.
3. Create an HDFS directory.
4. Upload the dataset to HDFS.

Example:

```bash
hdfs dfs -mkdir /cranlogs
hdfs dfs -put cran_logs.csv /cranlogs/
hdfs dfs -ls /cranlogs
```

---

## MapReduce Implementation

Each analytical query was implemented using the following Java components:

- Mapper Class
- Reducer Class
- Driver Class

### Query 1 – Number of Downloads of ggplot2

**Objective:**  
Count the total number of downloads for the package `ggplot2`.

**Output:**  
Total download count of ggplot2.

---

### Query 2 – Downloads per Country

**Objective:**  
Calculate the number of package downloads grouped by country.

**Output:**  
Country-wise download statistics.

---

### Query 3 – Top 10 Most Downloaded Packages

**Objective:**  
Identify the ten most frequently downloaded packages.

**Output:**  
List of top 10 packages ranked by download count.

---

### Query 4 – Most Popular Package in Ireland

**Objective:**  
Filter records from Ireland (`IE`) and determine the most downloaded package.

**Output:**  
Most popular package among Irish users.

---

### Query 5 – Most Popular Operating System

**Objective:**  
Count downloads grouped by operating system.

**Output:**  
Operating system usage statistics.

---

## Project Structure

```text
Batch-Layer-Implementation/
│
├── data/
│   └── cran_logs.csv
│
├── src/
│   ├── Query1/
│   ├── Query2/
│   ├── Query3/
│   ├── Query4/
│   └── Query5/
│
├── output/
│
├── screenshots/
│   ├── hdfs_upload.png
│   ├── jps_output.png
│   └── query_results.png
│
└── README.md
```

---

## Results Summary

The MapReduce analysis produced the following insights:

- `ggplot2` recorded a significant number of downloads.
- Certain countries generated the highest download traffic.
- A small number of packages dominated overall download activity.
- The most popular package in Ireland was identified successfully.
- Operating system statistics revealed user platform preferences.

---

## Conclusion

The Hadoop MapReduce framework successfully processed the CRAN package download dataset and generated meaningful analytical results. The project demonstrates how distributed computing can be used to process and analyze large-scale datasets efficiently.

Key learning outcomes:

- Hadoop ecosystem fundamentals
- HDFS data storage
- MapReduce programming in Java
- Large-scale data processing
- Batch layer implementation for analytics

---

## Technologies Used

- Apache Hadoop
- HDFS
- Java
- Ubuntu Linux
- MapReduce

---

## Author

**Piyush Ashok Patil**  
MSc in Big Data Management and Analytics  
Student ID: 3191958
