package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;

import java.util.Map;

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
    public int login(MemberDto memberDto) {
        // [2.1] dao 메소드 실행
        int result = memberDao.login(memberDto);
        // [2.2] 결과 반환
        return result;
    } // func end

    // [3] 로그아웃 - Service X

    // [4] 내 정보 조회
    public MemberDto info(int mno) {
        // [4.1] dao 메소드 실행
        MemberDto memberDto = memberDao.info(mno);
        // [4.2] 결과 반환
        return memberDto;
    } // func end

    // [5] 중복검사
    public boolean check(String type, String data) {
        // [5.1] dao 메소드 실행
        boolean result = memberDao.check(type, data);
        // [5.2] 결과 반환
        return result;
    } // func end

    // [6] 회원 정보 수정
    public boolean update(MemberDto memberDto) {
        // [6.1] dao 메소드 실행
        boolean result = memberDao.update(memberDto);
        // [6.2] 결과 반환
        return result;
    } //func end

    // [7] 비밀번호 수정
    public boolean updatePassword(int mno, Map<String, String> map) {
        // [7.1] dao 메소드 실행
        boolean result = memberDao.updatePassword(mno, map);
        // [7.2] 결과 반환
        return result;
    } // func end

    // [8] 회원 탈퇴
    public boolean delete(int mno, String mpwd){
        // [8.1] dao 메소드 실행
        boolean result = memberDao.delete(mno, mpwd);
        // [8.2] 결과 반환
        return result;
    } // func end

    // [9] 아이디 찾기
    public String findMid(String mname, String mphone){
        String mid = memberDao.findMid(mname, mphone);
        return mid;
    } // func end

    // [10] 비밀번호 찾기·초기화
    public int findMpwd(String mid, String mphone, int randPwd) {
        int mpwd = memberDao.findMpwd(mid,mphone,randPwd);
        return mpwd;
    }

} // class end
