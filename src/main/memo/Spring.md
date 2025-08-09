# Spring_01_Spring 이란?
---
## 1. Spring
---
### 1) 정의
- Spring은 Java 기반의 엔터프라이즈 급 애플리케이션 개발을 위한 프레임 워크
### 2) 활용
- 전자정부 프레임워크 (대한민국 개발 표준)
### 3) 특징
- IOC/DI : 객체 의존성
- AOP : 로직 분리 모듈화
- MVC2 3 tier 강제 지원
- 외부 라이브러리 등과의 확장성이 좋음

## 2. Spring Boot
---
### 1) 정의
- 스프링 프레임워크를 빠르게 사용할 수 있도록 하는 자동설정기반의 스타터 프레임워크
### 2) 활용
- 복잡한 설정을 간편하게 사용할 수 있는 기능을 제공
### 3) 특징
- 스프링부트 안에 스프링이 포함되어 있음
### 4) 설정
내장 TomCat을 지원
 - TomCat : Java에서 제공하는 Web 관련 라이브러리를 포함한 SW

## 3. Gradle
---
### 1) 정의
- Java 기반의 빌드 자동화 SW
### 2) 특징
- 의존성 관리 = 배포·패키징을 보다 쉽게 자동 처리해줌
----

# Spring_02_Annotation 기초
---
### 1) 정의
Java·Spring에서 코드에 meta date를 추가하는 문법
### 2) 목적
- 메타데이터 제공
- 코드 간소화
- 가독성 향상
### 3) 종류
#### (1) 표준 어노테이션
Java에서 기본적으로 제공하는 어노테이션
###### ① @Override : 상위클래스의 메소드를 재정의
###### ② @Deprecated : 더이상 사용하지 않는 코드

```java
class SuperClass {  // 상위 클래스
    public void method1() {}
} // class end

class SubClass extends SuperClass { // 하위 클래스 - 상속
    @Override       // 재정의 어노테이션
    public void method1() {}

    @Deprecated     // 더 이상 사용하지 않음 = 비권장을 의미
    public void method2() {}
} // class end
```
#### (2) 메타 어노테이션
코드를 정의하거나 동작을 제어할 때 사용하는 어노테이션
###### ① 서블릿(Java web class)
###### ② 스프링

### 4) 생성방법
#### (1) @interface 어노테이션명{}
실행문 안에 추상 메소드 선언

#### (2) @interface 위에 다양한 @·어노테이션을 설정
###### ① @Retention() : 어노테이션 생성주기(유지기간)을 설정

@Retention( RetentionPolicy.RUNTIME ) : 런타임까지 유지
@Retention( RetentionPolicy.CLASS )   : class 파일에 포함 = 런타임이 아닐때도 유지
@Retention( RetentionPolicy.RESOURCE) : 컴파일 이후 삭제

###### ② @Target()    : 어노테이션 적용 대상을 설정
@Target( ElementType.METHOD )       :  메소드에서 사용
@Target( ElementType.TYPE )         :  클래스, 인터페이슷에서 사용
@Target( ElementType.FIELD )        :  멤버변수에서 사용
@Target( ElementType.PARAMETER )    :  매개변수에서 사용
@Target( ElementType.CONSTRUCTOR )  :  생성자에서 사용

```java
// 어노테이션 생성
@Retention(RetentionPolicy.RUNTIME) // Annotation1 어노테이션을 런타임까지 유지
@Target(ElementType.METHOD) // Annotation1 어노테이션을 메소드에 적용
@interface Annotation1{
    // 추상메소드
    String value1();
}
```

```java
// 어노테이션 사용
class TestClass1{
    @Annotation1( value1 = "어노테이션 주입") //생성한 어노테이션을 아래의 코드 런타임 시 실행· value 주입
    public void method3(){
        // method3 실행 시 , @Annotation 실행 > Annotation 내의 추상메소드 value1() 도 실행
    } // func end
} // class end

public class Example1 {
    public static void main(String[] args) {
        // [1] 표준 Annotation
        SubClass subClass = new SubClass();
        subClass.method1();                     // @Override : 오버라이딩
        subClass.method2();                     // @Deprecated : ctrl+space bar(자동완성)에서 취소선으로 표시, 실행은 가능

    } // main end
} // class end
```

# Spring_03_AppStart 관련 Annotation

# AppStart 관련 Annotation
## 1. @SpringBootApplication : 스프링 부트 환경 설정

### 1) @SpringBootConfiguration
- 프로젝트 내의 @Configuration 어노테이션을 찾아서 bean(객체)를 설정·등록할 수 있도록 함

### 2) @EnableAutoConfiguration
- Spring Boot가 자동으로 필요한 bean을 설정·등록
- 실행과 동시에 web server에 해당하는 내장형 TomCat을 설정함

### 3) @ComponentScan
- 현재 클래스를 기준, 현재 패키지 및 하위 패키지 내의 컴포넌트(클래스) 등을 자동으로 스캔하여 등록

#### (1) 스캔 대상 : @Component / @RestController / @Controller /
@Service / @Repository / @Mapper etc : 주로 MVC 어노테이션
####  (2) 주의점    : 현 클래스(AppStart)를 기준으로 상위 패키지는 스캔 불가,
따라서 AppStart는 항상 최상위 패키지에 위치해야 한다.

```java
@SpringBootApplication      // [1] 스프링 실행 어노테이션
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);  //[2] AppStart class를 매개변수로하는 스프링 실행 메소드
    }
}
```

# Spring_04_Annotation Controller 관련 Annotation

## 1. Controller class 관련 어노테이션
---

### 1) @Controller : 컨트롤러 어노테이션

#### (1) @Component
Spring 컨테이너(메모리)에 bean을 등록·생성
☆★싱글톤 역할을 대체☆★
#### (2) Spring Controller : 기본적으로 HTTP 통신을 지원

### 2) @RestController
기존 Controller에 @ResponseBody를 내장하고 있음

```java
@Controller 
// [1] 
// @Controller 어노테이션의 경우, ResponseBody 어노테이션을 내장하고 있지 않음
// 필요에 따라 REST 관련 메소드 상단에 @ResponseBody 선언해야함.
public class controller1 {}

@RestController 
// [2]
// @RestController 어노테이션은 ResponseBody 어노테이션을 내장하고 있으므로
// 메소드 별로 @ResponseBody를 선언해야 하는 번거로움이 적음
public class controller2 {}

```

## 2. Controller Class 내 메소드 어노테이션
---
### 1) @ResponseBody
      : Java가 사용하는 타입을 HTTP 언어인 JSON 타입으로 자동 변환·반환하는 어노테이션
      : Java에서 Method 실행 후 결과를 JSON으로 반환한다면, 해당 함수 위에 어노테이션 표기

```java
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class controller1 {
    @ResponseBody
    public void method1() { }
}
```
### 2) Mapping 관련 : REST
| No  | Annotation | HTTP method | CRUD   |
|-----|------------|------------|--------|
| (1) |@PostMapping|POST| Create |
| (2) |@GetMapping|GET| Read   |
| (3) |@PutMapping|PUT| Put    |
| (4) |@DeleteMapping|DELETE| Delete |

#### (5) @____Mapping("/URL정의")
URL 주소 정의하며 베이스 주소인 [http://localhost:8080] 뒤로 유도할 URL을 Mapping 뒤에 기재
한 URL 내에서 CRUD가 중복될 수 없음

#### (6) @RequestMapping("/URL정의")
class 위에 선언하며, 해당 클라스의 Mapping 어노테이션 메소드들의 공통 URL을 정의
만약 아래의 @RequestMapping에서 URL이 정의되어 있다면,
URL은 "/RequestMappingURL/@____MappingURL"으로 연결됨

```java

@RestController
public class RestController1 {
      
    // [1]
    @GetMapping("/day03")
      // HTTP를 지원하며, Http Get Method 실행시 연결됨을 주석
      // URL : http://localhost:8080/day03
      // Http 요청 > Java method 실행 및 결과 반환 > 결과를 JSON 타입으로 변환
      // Class에 @RestController를 선언하였으므로, @ResponseBody 생략 가능!!
      public String method1(){
            System.out.println("method1 실행");
            return "Hello world!";
      }// func end

      // [2]
      @GetMapping("/day03")
      public boolean method2( @RequestParam(name = "name", defaultValue = "홍길동", required = false) String name ){
            // QueryString을 이용한 매개변수 전달
            // http://localhost:8080//day03/param1?name=""
            // 실행시 name이 콘솔로 들어오는 것을 알 수 있음
            return true;
      } //func end

      // [3]
      @GetMapping("/day03/param2")
      public int method3( String name, int age ) {
            System.out.println("RestController1.method3");
            System.out.println("name = " + name + ", age = " + age);
            return 3;
      }

      // [4]
      @DeleteMapping("/day03/param3")
      public String method4( @RequestParam Map<String, String> map){
            // Map
            //      key와 value를 한 entry로 하며, 여러 entry를 저장하는 구조
            //☆★☆★일반적으로 정해진 규칙이 아닌, 비규칙 구조인 Map을 사용할 경우, @RequestParam 필수!!
            return "안녕";
      } //func end
    
}
```

## 참고1. REST 
### 1. 정의
웹의 자원을 관리하는 아키텍처
### 2. 특징
#### 1) 자원(Resources), 행동(Method), 표현
#### 2) HTTP 프로토콜을 이용하여 클라이언트와 서바 간의 일관성있는 통신을 구성

## 참고 2. REST API 
#### 1. REST 기반의 웹서비스 인터페이스를 구축
#### 2. HTTP 요청을 통해 자원(데이터)의 CRUD 기능을 제공

## 참고3. RESTful API 
### 1. REST API의 아키텍처를 잘 사용하는지에 대한 개념
### 2. 조건
#### 1) 일관성 유지
#### 2) 적절한 HTTP Method : Post(등록), Get(조회), Put(수정), Delete(삭제)
#### 3) 무상태성
#### 4) JSON / XML 형식으로 반환


# Spring_04_Method 매개변수 관련 Annotation

## 1. 매개변수 관련
---
### 1) 특징
- HTTP QueryString을 이용한 매개변수 전달
- `http://localhost:8080/URL정의?param=value`
- 실행시 value가 param의 값으로 들어오는 것을 알 수 있음
- 주의! value는 모두 String 으로 인식 (그래도 자동으로 타입변환 지원~~)

### 2) Annotation
#### (1) @ModelAttribute
- 복수 파라미터에 객체를 바인딩
- DTO 타입일 때 기본 적용
- DTO, VO(읽기모드) 등 만들어진 객체 타입
- 처리가능요청 : QueryString, <form : 첨부파일O >
- 지원 HTTP메소드 : Get / Post / Put / Delete

#### (2) @RequestParam( )
- 단일 파라미터에 변수 바인딩
- 함수의 매개변수명과 쿼리스트링의 매개변수명이 일치할 경우 생략가능
- 기본형 : int / String / List / Map
- 처리가능요청 : QueryString, <form : 첨부파일X >
- 지원 HTTP메소드 : Get / Post / Put / Delete

① name = "URL매개변수명"
- URL상 ? 뒤에 매핑할 매개변수명
- Java의 매개변수명과 QueryString의 매개변수명이 동일할 경우, 생략 가능

② defaultValue = "초기값"
- 매개변수 생략 시 초기값

③ required = true or false
- 매개변수가 없을 경우, 예외 발생
- true : 예외 발생
- false : 예외 발생 X

#### (3) @RequestBody
- 본문(body)를 객체로 바인딩
>    * 본문·body?
         - HTTP 본문에 매개변수를 표현
         - 매개변수가 노출되지 않음, 보안 안전성
         - Dto / List / Map tkdyd rksmd
         - ex) 비밀번호, 아이디
         - Post / Put에 대해서만 객체 지원
- DTO 타입에 사용
- Body 내에서 사용
- 지원 HTTP메소드 : Post / Put 만 사용 가능

# Spring_05_Lombok 어노테이션
