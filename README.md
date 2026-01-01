# Android Compose Project

A modern Android application built with **Jetpack Compose**, following **Clean Architecture** and **MVVM** principles.

---

## 🚀 How to Run
Follow these steps to get the project running on your local machine:

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/vigoou/buymed.git
    ```
2.  **Open in Android Studio:**
    Launch Android Studio and select `Open`, then navigate to the cloned folder.
3.  **Sync Gradle:**
    Wait for the "Gradle Sync" to finish. This will download all necessary dependencies.
4.  **Run the App:**
    Select your device/emulator and click the green **Run** button (or press `Shift + F10`).

---

## 🛠 Tech Stack & Architecture
This project is built using native Android development tools and modern architectural patterns:

* **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (Declarative UI)
* **Architecture:** Clean Architecture + MVVM (Model-View-ViewModel)
* **Networking:** [Retrofit](https://square.github.io/retrofit/) for REST API communication.
* **Local Database:** [Room](https://developer.android.com/training/data-storage/room) for offline data persistence.



---

## 💻 Requirements
Ensure your development environment meets the following specifications:

* **Android Studio:** Iguana | 2023.2.1 or newer.
* **JDK:** Version 17 or higher (typically bundled with Android Studio).
* **Min SDK:** API Level 24+ (Android 7.0 Nougat).

---

## ⚖️ Trade-offs & Future Improvements
Given the current scope, the following trade-offs were made:

* **UI/UX Design:** The current implementation focuses heavily on core functionality and architecture. If given more time, I would:
    * Implement a more polished Material Design 3 theme.
    * Add custom animations and transitions for a smoother user experience.
* **Testing:** Increase unit and UI test coverage for critical business logic.

---

## 📝 Notes
* **Data Source:** The product list is sourced from the National Pharmaceutical Portal of the Ministry of Health (Vietnam).
    * **Link:** [https://dichvucong.dav.gov.vn/congbothuoc/index](https://dichvucong.dav.gov.vn/congbothuoc/index)
* **Local Storage:** The application utilizes a Room database for data management. The initial product dataset (containing 200 items) is pre-loaded from the local asset file located at: `assets/buymed.db`.sử dụng data từ Room database, dữ liệu sản phẩm (200 item) nằm trong folder "assets/buymed.db".*