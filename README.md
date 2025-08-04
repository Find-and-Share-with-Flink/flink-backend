# Flink 서비스 백엔드

`flink-backend`는 의류 쇼핑몰 상품의 최저가 비교, 통합 리뷰, 사용자 스타일 공유(피드), 가격·재입고 알림 기능을 제공하는 **Find-and-Share-with-Flink** 서비스의 백엔드 애플리케이션입니다.

---

## 주요 기능

* **상품 최저가 비교**: 여러 쇼핑몰(API·크롤링 연동)에서 최적가 검색
* **리뷰 통합**: 각 쇼핑몰 리뷰 수집 및 통계 집계
* **스타일 피드**: 사용자가 게시한 스타일 사진·상품 정보 공유
* **소셜 인터랙션**: 좋아요, 댓글, 팔로우 기능
* **알림 서비스**: 가격 하락·재입고 시점에 푸시/이메일 알림

---

## 기술 스택

| 구분      | 기술                             |
| ------- | ------------------------------ |
| 언어      | Java 17                        |
| 프레임워크   | Spring Boot 3.5.4             |
| DB      | PostgreSQL                     |
| ORM     | Spring Data JPA (Hibernate)    |
| API 문서화 | springdoc-openapi (Swagger UI) |
| 캐시 & 큐  | Redis                          |
| 빌드 도구   | Maven (`mvnw`)                 |
| 컨테이너    | Docker (예정)                    |
| CI/CD   | GitHub Actions (설정 예정)         |

---

## 아키텍처 개요

```text
+-------------+       +--------------+      +-------------+
|  Frontend   | <---> |  API Gateway | <--->| flink-backend|
+-------------+       +--------------+      +-------------+
                                   |
                           +----------------+
                           |    PostgreSQL  |
                           +----------------+
                                   |
                +------------------+------------------+
                |                                     |
         +-------------+                       +-------------+
         |   Redis     |                       | Elasticsearch|
         +-------------+                       +-------------+
```

* **API Gateway**: 요청 라우팅, 인증/인가 (추후 확장)
* **Backend**: RESTful API 제공, 서비스 로직, JPA 기반 DB 연동
* **Redis**: 피드 캐싱, 세션 관리, 알림 큐
* **Elasticsearch**: (예정) 해시태그·텍스트 검색 최적화

---

## 로컬 개발 환경

### 1. 사전 준비

* Java 17 설치
* PostgreSQL 설치 및 데이터베이스 생성
* (예정) Redis 설치

### 2. DB 설정 예시

```sql
CREATE DATABASE flink_db;
CREATE USER flink_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE flink_db TO flink_user;
```

### 3. 환경변수 또는 `application.properties` 설정

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/flink_db
spring.datasource.username=flink_user
spring.datasource.password=your_password
```

### 4. 실행

```bash
# 개발 브랜치에서
git checkout develop
git pull
# 빌드 및 실행
./mvnw clean spring-boot:run
```

### 5. API 문서 확인

* Swagger UI: `http://localhost:8080/swagger-ui.html`
* OpenAPI JSON: `http://localhost:8080/v3/api-docs`

---

## 브랜치 전략 & 커밋 규칙

* **브랜치 전략**

    * `main`: PR 승인된 프로덕션 코드
    * `develop`: 통합 개발 브랜치
    * `Hyejeong`, `Minseo`: 작업 단위 브랜치

* **커밋 메시지** (Conventional Commits)

    * `feat: 기능 추가`
    * `fix: 버그 수정`
    * `docs: 문서 변경`
    * `refactor: 코드 리팩토링`

---

## 이슈 & PR 템플릿 위치

* Issue 템플릿: `.github/ISSUE_TEMPLATE/bug_report.md`, `.github/ISSUE_TEMPLATE/feature_request.md`
* PR 템플릿: `.github/PULL_REQUEST_TEMPLATE.md`

---

## 라이선스

MIT © Find-and-Share-with-Flink 팀
