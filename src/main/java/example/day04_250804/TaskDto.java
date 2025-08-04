package example.day04_250804;

public class TaskDto {
    // 1.멤버변수
    private String name;
    private int age;

    // 2. 생성자
    public TaskDto() {
    }
    public TaskDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
    // 3. 메소드

    // 3.1 getter ·setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    // 3.2 toString()
    @Override
    public String toString() {
        return "TaskDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
} // class end
