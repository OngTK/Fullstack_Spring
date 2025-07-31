package example.day02_250731._2_Rest;

public class TaskDto {
    // 멤버변수
    private String name;
    private int age;

    // 생성자

    public TaskDto(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public TaskDto() {
    }

    // getter·setter

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
}
