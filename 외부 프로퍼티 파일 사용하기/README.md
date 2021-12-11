# 외부 프로퍼티 파일 적용하기

### 예제 실행 방법

* cmd에서 디렉토리 이동  ex) ../SpringSkillsExample/PropertiesInjection
* 기본 환경 실행 방법 java -jar app.jar
* 개발 환경 실행 방법 java -jar app.jar --spring.profiles.active=dev

### 예제 설명
서버 구성에 필요한 속성을. properties 또는. yml 파일에 작성합니다.

저 같은 경우가 때 src/main/resources 경로에 위치한 application.properties에 작성하는 편인데 서버 배포 과정 중 문제가 있었습니다.

서비스 초기 배포다 보니 스케줄링 설정, 암호화 파일의 경로나 확장 파일의 경로가 추가되는 등 properties가 자주 변경하는 일이 발생하였고, 변경된 properties를 적용하기 위해 반복적으로 서버의 build가 발생하는 상황이었습니다.

properties가 변경되었다고 해서 서버의 build가 발생해야 하는 것은 비효율적이라 생각하여 다른 방법을 찾기로 하였습니다.

## Property 읽는 과정
Property를 바인딩하는 다양한 방법이 존재하듯이 바인딩할 Property파일을 찾는 방법 또한 순서가 존재합니다. 
Spring Boot 동작 시 Property를 찾는 순서입니다. (몇 가지는 제외하였습니다.)

| **1. Terminal에서 명령어 입력**                        | --spring.properties.active=real 등                      |
| ------------------------------------------------------ | ------------------------------------------------------- |
| **2. Java 시스템 속성**                                | System.getProperties()                                  |
| **3. jar 파일과 같은 경로에 존재하는 properties 파일** | .properties, .yml profile 모두 적용 가능                |
| **4. jar 파일과 함께 패키징된 properties 파일**        | src/main/resources 경로에 위치한 application.properties |

**아래는 Spring Boot 공식 홈페이지에서 이번 주제에 대해 설명한 글입니다.**

[Spring Boot Externalized Coinfiguration](https://docs.spring.io/spring-boot/docs/1.2.3.RELEASE/reference/html/boot-features-external-config.html)



저 같은 경우 3번의 방법과 1번의 방법을 적용하기로 결정하였습니다.

우선 properties를 크게 4가지로 나누었습니다.

- 서버 구동에 필수로 존재해야 하는 모든 속성이 작성된 application.yml
- 운영 서버에 적용할 application-real.properties
- 개발 서버에 쓰일 application-dev.properties
- 테스트 과정에 쓰일 application-dev.properties
 
자주 변경될 운영, 개발, 테스트 속성 파일의 경우. properties가 한눈에 보기 편하여 사용하였습니다.
 
3번의 방법으로 분리 후 1번의 명령어 입력 방법을 통해 각 properties를 별도로 적용하였습니다.
 
### 직접 적용해보기

최종 프로젝트 구조

![image](https://user-images.githubusercontent.com/22608825/145678388-786f954e-d719-42b6-8f31-a192b89183ed.png)

우선 테스트를 위해 간단한 api를 만들어 봅시다.

```java
@RequestMapping(value = "${domain}")
@RestController
public class ExampleApi {
    @Value("${message}")
    private String message;

    @GetMapping
    public String getMessage() {
        return message;
    }
}
```

이후 프로젝트 루트 경로에 properties 파일을 작성해주세요.

**application.yml**

```yaml
domain: example
message: 안녕하세요!
server:
  port: 8080
```

**application-real.properties**

swagger try를 방지하는 겸 작성하여 내용은 없습니다. 이럴 경우 application.yml의 내용을 그대로 갖고 오게 됩니다.

**application-dev.properties**

```properties
message= 안녕하세요! 개발 환경 입니다!!
server.port= 8281
```

**application-test.properties**

```properties
message= 안녕하세요! 테스트 환경 입니다!!
server.port= 8283
```

이후 jar 파일 생성 후 프로젝트의 루트 경로에 위치한 뒤 동작하면 정상적으로 동작하는 화면을 확인할 수 있습니다.

![image](https://user-images.githubusercontent.com/22608825/145678455-d2cde187-0fb9-43eb-b149-f2a252aa57e9.png)

jar 파일과 properties를 같은 디렉토리에 위치만 시킨다면 꼭 프로젝트 루트 경로에 위치할 필요는 없습니다.

배포 환경에 맞추어 다양하게 적용하면 됩니다!
