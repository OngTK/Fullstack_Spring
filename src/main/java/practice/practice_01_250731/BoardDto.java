package practice.practice_01_250731;

public class BoardDto {

    // 멤버변수
    private int bno;
    private String btitle;

    // 생성자

    public BoardDto(int bno, String btitle) {
        this.bno = bno;
        this.btitle = btitle;
    }

    public BoardDto() {
    }

    // getter·setter

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }
}
