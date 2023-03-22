# 블로그 검색 서비스
![kotlin-version](https://img.shields.io/badge/kotlin-1.7.10-orange) ![spring-boot-version](https://img.shields.io/badge/springboot-2.7.9-green) ![kotlin-version](https://img.shields.io/badge/gradle-7.4.2-black)

이 프로젝트는 "블로그 검색 서비스"를 제공합니다. 주요 기능은 블로그 검색과 인기 검색어 목록 제공입니다.

## 기술 스택

- Kotlin 1.7
- Spring Boot 2.7.9
- Gradle 7.4.2
- H2 in-memory database
- embedded Redis
- JPA

## 실행 환경
- Java 11
- gradle 7.4

## 실행 방법

1. 프로젝트를 빌드합니다.

    ```bash
    ./gradlew clean build
    ```

2. 생성된 jar 파일을 실행합니다.

    ```bash
    java -jar presentation/build/libs/blog-search-api.jar
    ```

3. Swagger UI

    ```
    http://localhost:8080/swagger-ui/
    ```

4. 테스트 방법

    ```
    ./gradlew test
    ```
   
## Executable Jar 파일 다운로드
- [다운로드](https://github.com/hi8735/blog-search/raw/main/executable/blog-search-api.jar)
## API 명세

### 블로그 검색 API

- Endpoint: **`GET /api/v1/search/blog`**
- Parameters:
    - **`query`** (string): 검색어
    - **`sort`** (string, optional): 정렬 방식 (accuracy: 정확도순, recency: 최신순, 기본값: accuracy)
    - **`page`** (int, optional): 페이지 번호 (기본값: 1)
    - **`size`** (int, optional): 페이지당 검색 결과 개수 (기본값: 10)

### **인기 검색어 목록 API**

- Endpoint: **`GET /api/v1/search/popular-keywords`**

## **모듈 구성**

```
┌──────────────┐
│ presentation │
└─────┬────────┘
      │
      v
┌─────────────┐        ┌──────────────┐
│ application │───────>│ external-api │
└─────┬───────┘        └──────────────┘
      │                      
      v                      
┌─────────────┐        ┌──────────────┐
│   domain    │<───────│Infrastructure│
└─────────────┘        └──────────────┘

```
### presentation
- API 호출을 받고 application 모듈을 호출하여 요청을 처리합니다.
- API 호출 결과를 반환합니다.
  - 작성된 예제에서는 Rest API를 사용하였고, 추후 다른 presentation 모듈을 추가하여 graphql, grpc 등 다른 API를 사용할 수 있습니다.
### application
- application service를 제공합니다.
- 비지니스 로직이 구현되어 있습니다.
- domain 모듈을 호출하여 요청을 처리합니다.
- 높은 트래픽에 대비해 redis를 사용한 캐싱이 구현되어있습니다.
  - 각 key 별 다른 설정을 적용할 수 있도록 구현되어있습니다.
- 블로그를 검색하면 블로그 검색 이벤트를 발생시킵니다.
  - 이벤트는 스프링 이벤트 핸들러를 사용하였지만 운영 환경에서는 kafka, SQS 등의 메시지 큐를 활용하여 높은 트래픽에도 안정적인 서비스를 제공할 수 있습니다.
- 기본적으로 카카오 블로그 검색 API를 사용하고, 카카오 검색 블로그가 응답하지 않을 경우 네이버 블로그 검색 API를 사용합니다.

### external-api
- 외부 api를 호출하는 모듈입니다.
- KakaoBlogSearchClient 와 NaverBlogSearchClient가 BlogSearchClient 인터페이스를 구현하고 있습니다.
- BlogSearchClient 인터페이스를 구현한 다른 클라이언트를 추가하여 다른 외부 api를 사용할 수 있습니다.
- 외부 api 호출에는 OpenFeign을 사용하였습니다.

### domain
- 도메인 모델 및 도메인 서비스를 제공합니다. 
- 도메인 모델은 순수한 객체로 이뤄져 있고, repository interface만 존재해 DB 기술로부터 자유롭습니다.

### infrastructure
- 현재 프로젝트에서는 Jpa와 in-memory H2를 사용하여 구현되어있습니다. 
- Jpa repository, Jpa Entity 등이 구현되어있고, domain 객체로 변환할 수 있습니다.
- infrastructure 관련기술을 변경하고 싶을 때 domain 모듈에 영향을 주지 않고 변경할 수 있습니다.

## **사용한 오픈 소스**

- OpenFeign: 외부 API 호출을 위해 사용했습니다.
- embedded-redis: 로컬환경에서 내부 캐싱을 사용하기 위해 추가하였습니다. 
- springfox-swagger: API 문서 제공을 위해 사용했습니다.

## **추가 기능**

- 캐싱: 블로그 검색 결과를 캐싱하여 API 응답 시간을 줄임
- 이벤트 발행: 블로그 검색 시 이벤트를 발행하여 인기 검색어를 업데이트
