# JS_11_Fetch 라이브러리, 동기·비동기

## 1. 동기·비동기
### 1) 동기
- 여러 작업이 실행될 때, 작업 간의 실행 순서를 제어
- 순차적 처리, 무결성 중시, 비교적 느림
### 2) 비동기
- 여러 작업이 실행될 때, 작업이 독립적으로 실행
- 동시처리, 무결성 보장불가, 비교적 빠름


## 2. fetch 라이브러리
### 1) 정의
JS에서 제공하며, 비동기 통신을 지원하는 함수

### 2) 역할
HTTP로부터 요청·응답을 비동기적으로 처리

### 3) 비동기 방식 사용법
 then을 이용한 체인 형식
```js
fetch( URL , Option )
   .then( 응답매개변수 => 응답매개변수.타입 )
   .then( data => { 실행문; } )
   .catch( error => { 예외처리; } )
```
#### (1) URL 주소
- Spring @OOMapping에 할당된 URL 주소
#### (2) Option
##### ① DELETE·GET 메소드 사용
`{ method : "http메소드" }` 
 headers / body 생략 가능
##### ② POST, PUT 메소드 사용
```js
{ method : "http메소드" ,
headers : { "Content-Type" : "application/json" } ,
body : JSON.stringify( { 전달객체 } ) }
```
- 주로 body·headers를 사용하는 POST, PUT 메소드에서 사용
- header : http body의 타입 설정으로 `{ "Content-Type" : "application/json" }` 을 항상 입력
- body : body로 전달할 객체 작성,
JSON 타입변환 필요( JSON.stringify() )
#### (3) .then( 응답객체 => 응답객체.json() )
- Java로 부터의 결과, 응답 자료·결과를 Json 타입으로 변환
- 응답객체는 일반적으로 'response / r'
- 응답객체.text : 응답객체를 문자 타입으로 반환
#### (4) .then( data => { 실행문; } )
- 타입변환된 자료를 매개변수로 받아 실행문 처리
#### (5) .catch( error => { 실행문; } )
- 만약에 fetch 통신 간에 오류가 발생할 경우, 실행문 처리

### 4) 동기 방식 사용법
   - async · await 을 이용한 동기 형식
   - 실행 순서를 보장
```js
const func명 = async () => {
    try{
        const obj = { ... }
        const option = { method : "POST/PUT/GET/DELETE" ,
            headers : { "Content-Type" : "application/json" },
            body : JSON.stringfy(obj) 
            }
        const response = await fetch( URL , Option )
        const data = await response.json()
    } catch ( error ) {
        console.log(error)
    }
} // func end
```

### 5) 예시
```js
// 매개변수 X, 반환값 X =============================================================
// [1] GET
const func1 = () => {

    // fetch를 이용한 스프링 controller와 통신하기
    fetch("/day04/exam", { method: "GET" })
    // GET의 경우 생략 가능 

}

// [2] POST
const func2 = () => {
    fetch("/day04/exam2", { method: "POST" })
}

// [3] PUT
const func3 = () => {
    // bassUrl은 생략가능
    fetch("/day04/exam3", { method: "PUT" });
}

// [4] DELETE
const func4 = () => {
    fetch("/day04/exam4", { method: "DELETE" });
}


// 매개변수 O, 반환값 O =============================================================
// [5] GET
const func5 = () => {
    // 임의 값을 대입한 매개변수
    const name = "배두훈"
    const age = 39;

    // 쿼리스트링 방식
    fetch(`/day04/exam5?name=${name}&age=${age}`, { method: "GET" })
        .then(response => response.json())         // 1. 응답 자료를 매개변수로 하여, JSON 타입으로 변환
        .then(data => { console.log(data) })      // 2. JSON으로 변환된 데이터를 매개변수로 받아, 실행문 처리
        .catch(error => { console.log(error) })   // 3. Error 발생시, 실행문 처리

}

// [6] POST
const func6 = () => {
    // 임의 값을 대입한 매개변수
    const name = "강형호"
    const age = 37;

    // body 방식
    const option = {
        method: "POST",
        headers: { "Content-Type": "application/json" },  // http body의 타입 설정
        body: JSON.stringify({ "name": name, "age": age })                // Body로 작성, JSON 타입변환 필요
        // JSON.stringify() : JS객체를 JSON 문자열 타입으로 변환
        // JSON.parse() : JSON 문자열 타입을 JS 객체로 변환
    } // option end

    fetch(`/day04/exam6`, option)
        .then(response => response.json())         // 1. 응답 자료를 매개변수로 하여, JSON 타입으로 변환
        .then(data => { console.log(data) })      // 2. JSON으로 변환된 데이터를 매개변수로 받아, 실행문 처리
        .catch(error => { console.log(error) })   // 3. Error 발생시, 실행문 처리
} // func end

// [7] PUT
const func7 = () => {
    // 임의 값을 대입한 매개변수
    let sample = { name: "조민규", age: 35 };
    let option = {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(sample)
    } // option end

    fetch("/day04/exam7", option)
        .then(r => r.json())
        .then(data => console.log(data))
        .catch(error => console.log(error))

} // func end

// [8] Delete
const func8 = () => {
    const name = "고우림"
    const age = 30

    fetch(`/day04/exam8?name=${name}&age=${age}`, {method : "DELETE"})
    .then( r => r.json() )
    .then( d => console.log(d) )
    .catch( e => console.log(e) )
}
```
