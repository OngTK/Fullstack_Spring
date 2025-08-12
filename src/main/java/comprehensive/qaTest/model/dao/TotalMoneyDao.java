package comprehensive.qaTest.model.dao;

import comprehensive.qaTest.model.dto.TotalMoneyDto;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository
public class TotalMoneyDao extends Dao{

    // [1] 매출 정보 전체조회 moneyRead()
    public ArrayList<TotalMoneyDto> moneyRead(){
        ArrayList<TotalMoneyDto> list = new ArrayList<>();

        try{
            String sql = "select money.custno, member.custName, member.grade, sum(money.price) as totalPrice from money inner join member on member.custNo = money.custno group by member.custNo order by totalPrice desc;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TotalMoneyDto totalMoneyDto = new TotalMoneyDto(
                        rs.getInt("custNo"),
                        rs.getString("custName"),
                        rs.getString("grade"),
                        rs.getInt("totalPrice")
                );
                list.add(totalMoneyDto);
            }
        } catch (Exception e) {
            System.out.println("TotalMoneyDao.moneyRead"+e);
        }
        return list;
    } // func end

} // class end
