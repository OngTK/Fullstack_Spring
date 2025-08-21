package web.model.dao;

import org.springframework.stereotype.Repository;
import web.model.dto.PaymentDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class PaymentDao extends Dao {

    // [1] 결제 정보 등록하기
    // 결제 성공시 pno 반환
    public int addPaylog(PaymentDto paymentDto){
        try{
            String sql = "insert into payment(mno, pamount) values (?,?);";
            PreparedStatement ps =  conn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,paymentDto.getMno());
            ps.setInt(2,paymentDto.getPamount());
            if( ps.executeUpdate() == 1 ){
                ResultSet rs = ps.getGeneratedKeys();
                if ( rs.next() ){
                    // 성공하면 자동생성된 결제번호 반환
                    return rs.getInt( 1 );}
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("PaymentDao.addPaylog "+ e);
        }
        return false;
    } // func end
}
