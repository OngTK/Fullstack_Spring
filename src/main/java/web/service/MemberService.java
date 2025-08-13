package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;

@Service
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    // [1] 회원가입
    public int signup(MemberDto memberDto) {
        // [1.1] dao에 전달
        int result = memberDao.signup(memberDto);
        // [1.2] 결과 반환
        return result;
    } // func end

    // [2] 로그인
    public int login(MemberDto memberDto){
        // [2.1] dao 메소드 실행
        int result = memberDao.login(memberDto);
        // [2.2] 결과 반환
        return result;
    } // func end

} // class end
