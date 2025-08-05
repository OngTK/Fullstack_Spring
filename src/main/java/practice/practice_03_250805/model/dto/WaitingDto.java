package practice.practice_03_250805.model.dto;

public class WaitingDto {

    // 멤버변수 ================================
    private int wno;
    private String phone;
    private int count;

    // 생성자 ==================================
    // 빈생성자
    public WaitingDto() {
    }

    // full 생성자
    public WaitingDto(int wno, String phone, int count) {
        this.wno = wno;
        this.phone = phone;
        this.count = count;
    }

    // 메소드 ==================================
    // getter·setter
    public int getWno() {
        return wno;
    }

    public void setWno(int wno) {
        this.wno = wno;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // toString
    @Override
    public String toString() {
        return "WaitingDto{" +
                "wno=" + wno +
                ", phone='" + phone + '\'' +
                ", count=" + count +
                '}';
    }
}
