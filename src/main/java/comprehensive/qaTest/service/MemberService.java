package comprehensive.qaTest.service;

import comprehensive.qaTest.model.dao.MemberDao;
import comprehensive.qaTest.model.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MemberService {
    @Autowired
    private MemberDao memberDao;

    // [1] 회원 정보 등록 memberCreate()
    public boolean memberCreate(MemberDto memberDto) {
        boolean result = memberDao.memberCreate(memberDto);
        return result;
    }//func end

    // [2] 회원 정보 전체조회 memberRead()
    public ArrayList<MemberDto> memberRead(){
        ArrayList<MemberDto> list = memberDao.memberRead();
        return list;
    } // func end

    // [3] 회원 정보 개별조회 memberView()
    public MemberDto memberView(int custNo){
        MemberDto memberDto = memberDao.memberView(custNo);
        return memberDto;
    } //func end

    // [4] 회원 정보 수정 memberUpdate()
    public boolean memberUpdate(MemberDto memberDto){
        boolean result = memberDao.memberUpdate(memberDto);
        return result;
    } // func end

    // [5] 회원 정보 삭제 memberDelete()
    public boolean memberDelete(int custNo){
        boolean result = memberDao.memberDelete(custNo);
        return result;
    } // func end

    // [6] max custNO 추출
    public int maxCustNo(){
        int result = memberDao.maxCustNo();
        return result;
    }//func end

} // class end
