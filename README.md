# First Mobile App Framework

An Android UI automation framework using Appium, Selenium, and TestNG. The tests use the Page Object Model (POM) to keep screen interactions separate from test scenarios and assertions.

## Framework structure

```text
src/test/java/
├── pages/       # Screen actions, waits, and screen state queries
├── support/     # Appium session lifecycle, options, and environment helpers
└── tests/       # Test setup, scenarios, and assertions
```

| Area | Responsibility |
| --- | --- |
| `pages/LoginPage.java` | Locates login controls, enters credentials, submits the form, and checks the signed-in marker. |
| `pages/MainActivityPage.java` | Checks that OpenChamber is in the foreground and reports the current activity. |
| `pages/ConnectByAddressPage.java` | Waits for and opens the connect-by-address flow, then checks the available fields. |
| `support/BaseAppiumTest.java` | Starts and closes the Appium session once per test class. |
| `support/AppiumOptionsFactory.java` | Builds the shared OpenChamber Android capabilities. |
| `support/Environment.java` | Reads required environment variables and values with defaults. |
| `tests/` | Keeps each test focused on its scenario and expected outcomes. |

## Design practices

- **POM:** Tests call descriptive page methods instead of locating or interacting with screen elements directly.
- **Single responsibility:** Page classes handle one screen or flow; test classes describe scenarios; support classes handle driver setup and configuration.
- **DRY:** Shared session cleanup, OpenChamber capabilities, and environment-variable handling are defined once.
- **KISS:** The framework uses small page classes and explicit waits without adding unnecessary layers or abstractions.
- **Encapsulation:** Appium selectors and screen details stay in page classes. Tests express expected behavior through assertions.

## Requirements

- JDK 25, as configured by `maven.compiler.release` in `pom.xml`.
- Maven.
- Appium Server with the UiAutomator2 driver installed and running.
- An Android emulator or device connected to ADB.
- The app installed on the device, or an APK/APKS path for the login test.

The dependency versions are managed in `pom.xml` (Appium Java Client, Selenium, and TestNG).

## Configuration

The Appium server URL defaults to `http://127.0.0.1:4723`, and the Android device name defaults to `Android Emulator`.

Set the following variables as needed:

| Variable | Used by | Purpose |
| --- | --- | --- |
| `APPIUM_SERVER_URL` | All tests | Appium server URL. Defaults to `http://127.0.0.1:4723`. |
| `ANDROID_DEVICE_NAME` | All tests | Desired Android device name. Defaults to `Android Emulator`. |
| `ANDROID_UDID` | OpenChamber tests | Optional device UDID when more than one device is available. |
| `ANDROID_APP_PATH` | Login test | Optional path to an `.apk` or `.apks` file to install and launch. |
| `ANDROID_APP_PACKAGE` | Login test | App package to launch when `ANDROID_APP_PATH` is not set. |
| `ANDROID_APP_ACTIVITY` | Login test | App activity to launch when `ANDROID_APP_PATH` is not set. |
| `LOGIN_USERNAME_ID` | Login test | Appium resource ID for the username field. |
| `LOGIN_PASSWORD_ID` | Login test | Appium resource ID for the password field. |
| `LOGIN_BUTTON_ID` | Login test | Appium resource ID for the login button. |
| `LOGIN_SUCCESS_MARKER_ID` | Login test | Appium resource ID visible after a successful login. |
| `APP_TEST_USERNAME` | Login test | Test account username. |
| `APP_TEST_PASSWORD` | Login test | Test account password. |

If `ANDROID_APP_PATH` is unset, both `ANDROID_APP_PACKAGE` and `ANDROID_APP_ACTIVITY` are required by the login test. Keep credentials in environment variables; do not commit them to the repository.

Example PowerShell configuration:

```powershell
$env:APPIUM_SERVER_URL = "http://127.0.0.1:4723"
$env:ANDROID_DEVICE_NAME = "Android Emulator"
$env:ANDROID_APP_PATH = "C:\path\to\app.apk"
$env:LOGIN_USERNAME_ID = "com.example:id/username"
$env:LOGIN_PASSWORD_ID = "com.example:id/password"
$env:LOGIN_BUTTON_ID = "com.example:id/login"
$env:LOGIN_SUCCESS_MARKER_ID = "com.example:id/home"
$env:APP_TEST_USERNAME = "your-test-user"
$env:APP_TEST_PASSWORD = "your-test-password"
```

The OpenChamber tests launch the installed package `com.openchamber.app` with `.MainActivity`.

## Run

Run the complete TestNG suite:

```bash
mvn test
```

Run one test class:

```bash
mvn -Dtest=OpenChamberSmokeTest test
mvn -Dtest=ConnectByAddressOptionsTest test
mvn -Dtest=LoginPageTest test
```

The Appium server, Android device, target app, and required environment variables must be ready before running the matching tests.

