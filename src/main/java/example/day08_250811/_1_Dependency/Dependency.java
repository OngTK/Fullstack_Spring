package example.day08_250811._1_Dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

// [방법 1]  다른 클래스의 메소드 호출 =================================================
class SampleDao1{
    // [1.1] 메소드 선언
    void method1(){}
}

class SampleController1{
    // [1.2] 인스턴스 생성
    SampleDao1 sampleDao1 = new SampleDao1();
    public void method2(){
        // [1.3] 인스턴스의 메소드 실행
        sampleDao1.method1();
    }
}

// [방법 2] 해당 인스턴스를 미리 생성한 후 메소드 호출 => 싱글톤 ==========================
class SampleDao2{
    // [2.1] 싱글톤으로 인스턴스
    private static final SampleDao2 instance = new SampleDao2();
    private SampleDao2(){}
    public static SampleDao2 getInstance(){ return instance; }

    // [2.2] 메소드 생성
    public void method1(){}
}

class SampleController2{
    // [2.3] 다른 클래스의 싱글톤을 호출
    private final SampleDao2 sampleDao2 = SampleDao2.getInstance();
    // [2.4] 메소드 실행
    public void method2(){
        sampleDao2.method1();
    }
}

// [방법 3] IOC · DI를 통한 의존성 주입 ☆★☆★☆★☆★ ================================
@Component                         // [3.0] Spring container에 bean 등록 >> 싱글톤 대체 Annotation
class SampleDao3{
    void method1(){}
}

// [3.1] DI 방법 1
class SampleController3{
    @Autowired                              // [3.1.1] 싱글톤 호출 대체 Annotation
    private SampleDao3 sampleDao3;          // 멤버변수로 SampleDao를 생성하고 이에 대해 위의 Annotation 발동
    void method2(){
        sampleDao3.method1();
    }
}

// [3.2] DI 방법 2            ※ Spring 권장 방법
class SampleController4{
    private final SampleDao3 sampleDao3;                // [3.2.1] final로 멤버변수에 대한 불변성 보장
    
    @Autowired                                          // [3.2.2] 생성자를 bean에 주입
    public SampleController4(SampleDao3 sampleDao3){    // [3.2.3] 생성자를 선언
        this.sampleDao3 = sampleDao3;
    }
    void method2(){
        sampleDao3.method1();                           // [3.2.4] 메소드 실행
    }
}

public class Dependency {

}
