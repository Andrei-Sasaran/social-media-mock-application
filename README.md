# 📱 Social Media Mock Application

**Moderated social platform with role-based content approval and email notifications**  
*Backend: Java Spring Boot | Frontend: Vue.js | Admin: C# WPF | Database: MySQL*

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Vue.js](https://img.shields.io/badge/Vue.js-4FC08D?style=flat&logo=vuedotjs&logoColor=white)
![.NET](https://img.shields.io/badge/.NET-512BD4?style=flat&logo=dotnet&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white)
![Email](https://img.shields.io/badge/Email_Service-D14836?style=flat&logo=gmail&logoColor=white)

[![GitHub last commit](https://img.shields.io/github/last-commit/Andrei-Sasaran/social-media-mock-application)](https://github.com/Andrei-Sasaran/social-media-mock-application)

---

## 🌟 Key Features

### User Experience (Vue.js)
- 📝 Create posts (pending admin approval)
- 🔍 View approved posts in feed
- ❤️ Like/comment functionality
- ✉️ Receive email notifications

### Admin Dashboard (C# WPF)
- ✅ Approve/reject pending posts
- 🗑️ Delete inappropriate content
- 📧 Send bulk/individual emails to users
- 📊 View user statistics with filtering

### Email Notification Workflow
1. Admin selects users in WPF interface
2. System composes message using MailKit
3. Backend processes request via JavaMail
4. Users receive notifications in their inbox
5. Unread count appears in Vue.js frontend

---

## 🛠️ Tech Stack

| Component       | Technologies |
|-----------------|--------------|
| **Frontend**    | Vue 3, Vuex, Vuetify, Axios |
| **Backend**     | Java Spring Boot, JPA, JavaMail |
| **Admin Client**| C# WPF (.NET 6), MahApps.Metro, MailKit |
| **Database**    | MySQL 8.0 |
| **Email**       | SMTP protocol, Gmail/Outlook integration |
