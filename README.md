# SeleniumFramework

A Java-based test automation framework built on **Selenium WebDriver**, **TestNG**, and the **Page Object Model (POM)** design pattern. It supports data-driven testing via Apache POI and generates rich HTML reports using ExtentReports.

---

## Tech Stack

| Tool / Library | Version | Purpose |
|---|---|---|
| Java | 17 | Core language |
| Selenium WebDriver | 4.27.0 | Browser automation |
| TestNG | 7.0.0 | Test execution & management |
| Apache POI | 3.9 | Excel-based data-driven testing |
| ExtentReports | 5.0.8 | HTML test reporting |
| Maven | — | Build & dependency management |

---

## Project Structure

```
SeleniumFramework/
├── src/
│   ├── main/java/          # Page classes (POM), utilities, base classes
│   └── test/
│       ├── java/           # Test classes
│       └── resources/
│           └── testrunners/
│               └── testng_regression.xml   # TestNG suite file
├── reports/                # ExtentReports HTML output
├── test-output/            # TestNG default output
├── pom.xml                 # Maven project descriptor
└── README.md
```

---

## Prerequisites

- **Java 17** or higher installed and `JAVA_HOME` set
- **Maven 3.6+** installed
- **Chrome / Firefox** browser installed (matching WebDriver version)

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/gunjandashora/SeleniumFramework.git
cd SeleniumFramework
```

### 2. Install dependencies

```bash
mvn clean install -DskipTests
```

### 3. Run all tests

```bash
mvn test
```

This triggers the TestNG suite defined at `src/test/resources/testrunners/testng_regression.xml`.

### 4. Run a specific suite file

```bash
mvn test -DsuiteXmlFile=src/test/resources/testrunners/testng_regression.xml
```

---

## Test Reports

After a test run, reports are generated in the `reports/` directory. Open the HTML file in any browser:

```
reports/ExtentReport.html
```

TestNG's default output (including `emailable-report.html`) is available in the `test-output/` folder.

---

## Design Patterns

**Page Object Model (POM):** Each application page is represented as a dedicated Java class encapsulating its locators and actions. This keeps test code clean and maintainable.

**Data-Driven Testing:** Test data is managed in Excel files and read using Apache POI, allowing tests to run across multiple data sets without code changes.

---

## Configuration

Update browser type, base URL, implicit wait, and other settings inside your framework's configuration file (e.g., `config.properties` under `src/test/resources/`).

---


---

## License

This project is open-source. Add a `LICENSE` file if you intend to publish or share it publicly.
