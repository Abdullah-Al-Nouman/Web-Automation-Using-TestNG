 # 📌 ✨ Web Automation Using TestNG 🚀

## 📋📝 Project Overview
This project automates the testing of 🏦 user registration, 🔑 password reset, 🔓 login, 📦 item addition, 👤 profile update, and 🛠️ admin verification for [DailyFinance](https://dailyfinance.roadtocareer.net/).

---

## 🚀 Test Scenarios Covered

### ✅ **User Registration & Email Verification**
1. 🖥️ Visit [DailyFinance](https://dailyfinance.roadtocareer.net/) and register a new user.
2. 📩 Assert that a **congratulations email** is received.

### 🔐 **Password Reset Tests**
3. 🔗 Click on the **reset password** link.
4. 🔴 Write **2 negative test cases** and assert failures.
5. ✉️ Input a valid registered email and click on **Send Reset Link**.
6. 📬 Retrieve the reset password email from Gmail and set a **new password**.
7. 🔑 Login with the new password and assert successful login.

### 📦 **Item Addition Tests**
8. 🛍️ Add **two random items**:
   -  One item with **all fields filled**.
   -  One item with **only mandatory fields**.
9. 📋 Assert that **both items are listed** correctly.

### ✏️ **User Profile Update & Login Tests**
10. 🔄 Go to the **User Profile** and update the email with a new Gmail account.
11. 🚪 Logout and try logging in with:
    -  The **new email** (Should be successful).
    -  The **old email** (Should fail).

### 🔑 **Admin Verification Tests**
12. 🔄 Logout and login with the **Admin account** (Credentials must be passed securely via terminal ⚠️).
13. 🔍 Search for the **updated Gmail** and assert that the email appears on the **Admin Dashboard**.

---

## 📸🖼️ Test Output Screenshots
📌 Below are some screenshots from the test execution:

📷 *Report Screenshot 1*
  
![1](https://github.com/user-attachments/assets/1ed01a1d-fb06-40aa-8bfd-4535396302a2)

📷  *Report Screenshot 2*
![2](https://github.com/user-attachments/assets/09666937-175b-40cf-88ec-2b53cd1ea5d5)
 
  

---

## 🔗 Test Cases Link
📌 View detailed test cases here: **[Test Cases Document](#)** (Replace with actual link)

---

## 🎥 Test Execution Video
📌 Watch the test execution here: **[Output Video](#)**

https://github.com/user-attachments/assets/987d08db-b463-4a5f-9bab-2304a32eead1


---

## ⚙️🛠️ Setup & Execution
```bash
# 🏗️ Clone the repository
git clone https://github.com/yourrepo/dailyfinance-automation.git

# 📦 Install dependencies
pip install -r requirements.txt

# 🏃 Run the test suite
pytest --html=report.html --self-contained-html
```

---

## 🛠️💻 Tech Stack
- 🚀 **Selenium** for web automation
- 🧪 **PyTest** for test execution
- 📊 **Allure/HTML Reports** for test reporting
- 📧 **Gmail API** for email verification

---
