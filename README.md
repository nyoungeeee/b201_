# B201 프로젝트

> Spring Boot + React 기반 웹 애플리케이션

---

## 📌 프로젝트 소개
이 프로젝트는 **Spring Boot**와 **React**를 사용한 웹 애플리케이션입니다.  
백엔드와 프론트엔드를 분리한 구조로, REST API 기반 통신을 합니다.

---

## 🌿 Branch Strategy

- `main` : 배포 가능한 안정 버전
- `dev` : 개발 통합 브랜치
- `feature/*` : 기능 단위 개발 브랜치

### Workflow
1. `dev`에서 `feature/*` 생성
2. 기능 개발 후 `dev`로 merge
3. 안정화 후 `main`으로 merge

---

## 🛠 기술 스택

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Gradle

### Frontend
- React
- JavaScript (ES6+)
- Axios
- Vite / CRA

### DevOps / Tool
- Git & GitHub
- IntelliJ IDEA
- VS Code

---

## 📂 프로젝트 구조

```text
b201
├─ backend
│  ├─ src/main/java
│  ├─ src/main/resources
│  └─ build.gradle
│
├─ frontend
│  ├─ src
│  ├─ public
│  └─ package.json
│
└─ README.md

```

## 🚀 실행 방법
1️⃣ Backend 실행
cd backend
./gradlew bootRun
또는 IntelliJ에서 B201Application 실행

2️⃣ Frontend 실행
cd frontend
npm install
npm run dev

---

## 🔑 환경 변수
.env 파일을 생성하고 아래와 같이 설정합니다.

DB_URL=jdbc:mysql://localhost:3306/b201
DB_USERNAME=root
DB_PASSWORD=1234

--- 

## 📡 API 예시
Method	URL	Description
POST	/api/users	사용자 생성
GET	/api/users	사용자 조회
✨ 주요 기능
사용자 회원가입 / 조회

REST API 제공

프론트엔드 연동

---

## 📡 API 문서 (Swagger)

본 프로젝트의 REST API 문서는 **Swagger UI**를 통해 확인할 수 있습니다.

- Swagger UI:  
  👉 http://localhost:8080/swagger-ui/index.html

- OpenAPI JSON:  
  👉 http://localhost:8080/v3/api-docs

  ---

## 👤 개발자
Hyunyoung Cho

---

## 📄 라이선스
This project is licensed under the MIT License.
