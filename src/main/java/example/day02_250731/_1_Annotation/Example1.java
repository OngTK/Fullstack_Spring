package example.day02_250731._1_Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

class SuperClass {                       // 상위 클래스

    public void method1() {

    }
} // class end

class SubClass extends SuperClass {     // 하위 클래스 - 상속

    @Override       // @ 어노테이션, 오버라이딩
    public void method1() {

    }

    @Deprecated     // @ 더 이상 사용하지 않음 = 비권장
    public void method2() {
    }
} // class end

// [2] 어노테이션 생성
@Retention(RetentionPolicy.RUNTIME) // Annotation1 어노테이션을 런타임까지 유지
@Target(ElementType.METHOD) // Annotation1 어노테이션을 메소드에 적용
@interface Annotation1{
    // 추상메소드
    String value1();
}

// [3] 어노테이션 사용
class TestClass1{
    @Annotation1( value1 = "어노테이션 주입") //생성한 어노테이션을 아래의 코드 런타임 시 실행· value 주입
    public void method3(){
        // method3 실행 시 , @Annotation 실행 > Annotation 내의 추상메소드 value1() 도 실행
    }
}

public class Example1 {
    public static void main(String[] args) {
        // [1] 표준 Annotation
        SubClass subClass = new SubClass();
        subClass.method1();                     // @Override : 오버라이딩
        subClass.method2();                     // @Deprecated : ctrl+space bar(자동완성)에서 취소선으로 표시, 실행은 가능

    } // main end
} // class end
