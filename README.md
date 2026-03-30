# Akakçe Mobile Test Automation Project 📱🚀

[TR] Akakçe Android uygulaması için geliştirilmiş, Cucumber BDD ve Appium tabanlı mobil otomasyon projesi.
[EN] Mobile automation project for Akakçe Android app using Cucumber BDD and Appium.

---

## 🇹🇷 Proje Hakkında (Turkish)

Bu proje, Akakçe mobil uygulaması üzerinde uçtan uca (E2E) test senaryolarını otomatize etmek amacıyla geliştirilmiştir. Proje, sürdürülebilirlik ve okunabilirlik için **Page Object Model (POM)** tasarım desenini kullanmaktadır.

### 🛠 Kullanılan Teknolojiler
* **Dil:** Java (JDK 21)
* **Otomasyon Aracı:** Appium (UiAutomator2)
* **Test Çerçevesi:** Cucumber BDD (Gherkin)
* **Assertions & Runner:** TestNG
* **Build Aracı:** Maven

### 📋 Test Senaryosu Akışı
1. Uygulama açılır ve varsa giriş ekranı atlanır.
2. "Laptop" araması yapılır.
3. Filtreleme menüsünden "Bluetooth Versiyonu: 5.3" seçilir.
4. Ürünler "En Düşük Fiyat"a göre sıralanır.
5. Listelenen ürünlerden (reklamlar hariç) belirlenen sıradaki ürüne tıklanır.
6. Ürün detay sayfasında fiyat doğrulaması ve "Ürüne Git" butonu kontrolü yapılır.

---

## 🇺🇸 About The Project (English)

This project automates end-to-end (E2E) test scenarios for the Akakçe mobile application. It follows the **Page Object Model (POM)** design pattern to ensure high maintainability and clean code structure.

### 🛠 Tech Stack
* **Language:** Java (JDK 21)
* **Automation Tool:** Appium (UiAutomator2)
* **Test Framework:** Cucumber BDD (Gherkin)
* **Assertions & Runner:** TestNG
* **Build Tool:** Maven

### 📋 Test Scenario Flow
1. Launch the app and skip login if requested.
2. Search for "Laptop".
3. Filter results by "Bluetooth Version: 5.3".
4. Sort products by "Lowest Price".
5. Click on the Nth real product (excluding sponsored ads).
6. Verify the product price and the "Go to Product" button on the detail view.

---

## 🚀 Setup & Execution (Kurulum ve Çalıştırma)

1. **Prerequisites:**
   - Appium Server v2.x
   - Android Emulator (Pixel 7 / Android 14 recommended)
   - Java 21

2. **Run the tests:**
   ```bash
   mvn test
