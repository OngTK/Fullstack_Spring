package example.day08_250811._2_MVC.model.dao;

import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository                             // [1] Repository bean 등록
public class MvcDao extends Dao {       // [3] DB 연동을 정의한 상위 클래스 상속

    // [2] Dao 에서는 Autowired 가 없음

    // [3] 기능처리
    public void method(){
        System.out.println("MvcDao.method");
        try{
            String sql = "select * from mvc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("var1"));
            }
        }catch (Exception e){
            System.out.println("MvcDao.method" + e);
        }
    }
}
