# APSD-LIBRARY

**Streamlining Excellence in Exam Management Systems**

[![Last Commit](https://img.shields.io/github/last-commit/Fede-7/APSD-Library?style=flat-square)](https://github.com/Fede-7/APSD-Library)
[![Java](https://img.shields.io/badge/Java-17%2B-orange?style=flat-square&logo=java)](https://www.oracle.com/java/)
[![Languages](https://img.shields.io/github/languages/count/Fede-7/APSD-Library?style=flat-square)](https://github.com/Fede-7/APSD-Library)
---

## Table of Contents

- [Overview](#overview)
  - [Why APSD-Library?](#why-apsd-library)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Usage](#usage)
  - [Testing](#testing)

---

## Overview

**APSD-LIBRARY** is a comprehensive developer toolkit designed to facilitate exam-related workflows and data management within complex systems. It offers a robust foundation for building, testing, and managing container-based data structures with ease.

### Why APSD-Library?

This project aims to streamline exam processing and data handling through modular, reliable components. The core features include:

- ⚙️ **Build Automation**: PowerShell and Makefile scripts automate compilation, testing, and execution.
- 🧪 **Extensive Testing Frameworks**: Standardized tests validate containers, sequences, and collections.
- 🧱 **Modular Data Structures**: Abstract interfaces for vectors, chains, sets, and sequences.
- 🧮 **Utility Components**: Classes like `Box` and `Natural` numbers for efficient data handling.
- 📋 **Exam Management Tools**: Streamlined workflows and system consistency.

---

## Getting Started

### Prerequisites

This project requires:

- **Programming Language**: Java
- **Package Manager**: Maven

---

### Installation

Follow these steps to build and install APSD-Library:

```bash
# 1. Clone the repository
git clone https://github.com/rede-J/APSD-Library.git

# 2. Navigate to the project directory
cd APSD-Library

# 3. Install the dependencies using Maven
mvn install

# 4. run test + debug log
make clean && make && make runtest | tee runtest.log

---

### Test Progress:

## 1° Test:
Test run finished after 448 ms
[        56 containers found      ]
[         0 containers skipped    ]
[        56 containers started    ]
[         0 containers aborted    ]
[        56 containers successful ]
[         0 containers failed     ]
[        82 tests found           ]
[         0 tests skipped         ]
[        82 tests started         ]
[         0 tests aborted         ]
[        27 tests successful      ]
[        55 tests failed          ]

## 2° Test:
Test run finished after 367 ms
[        56 containers found      ]
[         0 containers skipped    ]
[        56 containers started    ]
[         0 containers aborted    ]
[        56 containers successful ]
[         0 containers failed     ]
[        82 tests found           ]
[         0 tests skipped         ]
[        82 tests started         ]
[         0 tests aborted         ]
[        46 tests successful      ]
[        36 tests failed          ]

##3° Test:
Test run finished after 348 ms
[        56 containers found      ]
[         0 containers skipped    ]
[        56 containers started    ]
[         0 containers aborted    ]
[        56 containers successful ]
[         0 containers failed     ]
[        82 tests found           ]
[         0 tests skipped         ]
[        82 tests started         ]
[         0 tests aborted         ]
[        53 tests successful      ]
[        29 tests failed          ]

## 4° Test:
Test run finished after 362 ms
[        56 containers found      ]
[         0 containers skipped    ]
[        56 containers started    ]
[         0 containers aborted    ]
[        56 containers successful ]
[         0 containers failed     ]
[        82 tests found           ]
[         0 tests skipped         ]
[        82 tests started         ]
[         0 tests aborted         ]
[        68 tests successful      ]
[        14 tests failed          ]


