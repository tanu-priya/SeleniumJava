# Selenium Tests

Brief Selenium test suite (Maven + Allure) for the project.

**Prerequisites**
- Java 11+ (JDK installed and `java` on PATH)
- Maven 3.6+ (`mvn` on PATH)
- Git (for uploading)
- Allure commandline (optional, for viewing reports)

**Project layout**
- `src/` — test sources and resources
- `src/main/java` — main Java sources (if any)
- `src/main/resources` — main resources
- `src/test/java` — test classes (Selenium tests)
- `src/test/resources` — test resources (test data, drivers, configs)
- `allure-results/` — Allure raw results produced by test runs (repo root)
- `target/` — Maven build output and generated reports
- `logs/` — run logs
- `screenshots/` — captured screenshots on failures

**Folder structure (example)**
```
seleniumtests/
├─ pom.xml
├─ README.md
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  └─ resources/
│  └─ test/
│     ├─ java/
│     └─ resources/
├─ allure-results/
├─ logs/
├─ screenshots/
└─ target/
```

**Other information**
- Maven: tests are executed via `mvn test` (uses Surefire/Failsafe depending on project setup).
- Allure results: tests should produce `allure-results` JSON files; these are read by the Allure CLI to generate reports.
- Allure Maven plugin (optional): to automatically generate reports during the build you can add the Allure Maven plugin to `pom.xml` and bind report generation to a Maven phase. Example snippet (pom.xml):

```xml
<plugin>
	<groupId>io.qameta.allure</groupId>
	<artifactId>allure-maven</artifactId>
	<version>2.10.0</version>
	<executions>
		<execution>
			<id>allure-report</id>
			<phase>verify</phase>
			<goals>
				<goal>serve</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```

- If your tests already write results into repository-root `allure-results/`, you can run `allure serve allure-results` directly.
- Recommended `.gitignore` entries: `target/`, `allure-report/`, `*.log`, `**/drivers/*` (if you keep browser drivers locally).
- CI: I can add a GitHub Actions workflow that runs `mvn test` and uploads `allure-results` as artifacts and/or publishes the generated Allure report.

---
If you'd like, I can now create a `.gitignore` and a simple GitHub Actions workflow to run tests and store Allure artifacts.

**Jenkins (CI) integration**

Included files:
- `Jenkinsfile` — example declarative pipeline that runs tests, archives Allure results, and (if the Allure Jenkins plugin is installed) publishes the Allure report.
- `.gitignore` — excludes `target/`, `allure-report/`, `allure-results/`, logs and screenshots.

Jenkins guide:

1. Create a new Pipeline job (or Multibranch Pipeline) in Jenkins and point it to this repository.

2. Install and configure tools (global Jenkins configuration):
	- JDK (e.g., JDK11)
	- Maven
	- (optional) Allure Jenkins plugin to show reports inside Jenkins UI

3. Use the included `Jenkinsfile`. The pipeline runs `mvn -B clean test` by default. If your Jenkins agents are Windows-based, edit the `Jenkinsfile` to use the `bat` step instead of `sh`.

4. Allure publishing options:
	- Recommended: install the Allure Jenkins plugin and let the `allure` step publish the report from `target/allure-results`.
	- Fallback: archive the `allure-results/` folder as build artifacts and generate the report locally using the Allure CLI (or download artifacts and run `allure generate`).

5. Example adjustments you may need:
	- If your tests write results to the repository root `allure-results/`, update the path in the `Jenkinsfile` and Allure plugin configuration.
	- Configure global Maven and JDK tool names in Jenkins to match any `tools` block (if used).

6. After a successful run:
	- If Allure plugin is present: open the Allure Report link from the Jenkins build page.
	- Otherwise: download the archived `allure-results` and generate a report locally:

```powershell
allure generate allure-results -o allure-report
allure open allure-report
```

If you'd like, I can also: add a Windows-friendly `Jenkinsfile` variant, add a GitHub Actions workflow, or create a CI job template for your Jenkins instance.

**Run tests (Windows PowerShell)**
```powershell
# run all tests
mvn clean test

# run a single test class
mvn -Dtest=MyTestClass test
```

After running, test results and Allure result files are typically in `target/allure-results` or the repository `allure-results/` folder.

**Generate and view Allure report**

1) Install Allure (Windows)

Using Chocolatey:
```powershell
choco install allure
# or (if you use Scoop)
scoop install allure
```

2) Run tests (creates results in `target/allure-results`)

```powershell
mvn clean test
```

3a) Serve report (generates and opens a local server)

```powershell
allure serve target/allure-results
```

3b) Or generate a static report and open it manually

```powershell
allure generate target/allure-results -o target/allure-report
allure open target/allure-report
```

Notes
- If your Maven build is configured to output results into a different folder, point `allure` commands to that folder instead.
- If you already have an `allure-results/` folder at repository root, you can run `allure serve allure-results`.

**Upload to GitHub (quick)**
```powershell
git init
git add .
git commit -m "Add README"
git remote add origin <your-repo-url>
git branch -M main
git push -u origin main
```

**.gitignore suggestions**
- `target/`
- `allure-report/`
- `*.log`

---
Created for quick GitHub upload and local runs. If you want, I can add a `.github/workflows` CI that runs tests and publishes Allure artifacts.
