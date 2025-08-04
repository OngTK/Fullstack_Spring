// JS 콘솔 출력
// [1] Intellij Ultimate 유료 버전 : 비권장
// [2] Intellij plug-in > 모든 언어 자동 AI 추천 : 비권장
// [3] Html·CSS 등은 Vscode에서 작업

console.log("실행2")

// VSCODE에서 작성
// Vscode에서 html·JS 코드 작성 > Spring 서버는 IntelliJ 에서 실행 

// [1] JS 자료·값
console.log( 3 ); 
console.log( 3.14 );
console.log( true );
console.log( null );
console.log( undefined );
console.log( "안녕" );
console.log( '안녕2' );
console.log( `안녕3` );
console.log( [3, 3.14, true, "안녕4"] );    // 배열
console.log( function 함수명(){} );         // 함수
console.log( {"속성1" : 3 , "속성2" : "안녕5"} ); // 객체

// [2] 변수/상수 선언 키워드 : let, const
let var1 = 15;
const var2 = "배두훈";

// [3] 함수 선언
function func1( a, b ){ 
    console.log( 'func1 exe' )
    let c= a + b;
    return c;
}; //func end

// [4] 함수
// [4.1] JS : 함수명 (인수값1, 인수값2)
let result = func1(10, 20);
console.log(result);

// [4.2] html _ onClick="함수명"

// [5] 익명 함수 선언
// 주로 변수 / 상수에 저장
const func2 = function(a,b){
    console.log('func2 exe')
} //func end
func2(30,20)

// [6] 람다식·화살표 함수 
// 주로 일회성 함수에서 사용, 변수·상수에 저장
const func3 = ( a , b ) => {
    console.log('func3 exe')
}
func3(40,50)