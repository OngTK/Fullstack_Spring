package web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.PostDao;
import web.model.dto.PageDto;
import web.model.dto.PostDto;

import java.util.List;

@Service
@RequiredArgsConstructor // lombok, final 변수 생성자 자동 생성
public class PostService {

    // @RequiredArgsConstructor 사용 시,
    // @Autowried를 생략해도 됨! 별도 생성자를 안만들어도 됨!
    // 여기서 말하는 생성자는 Service(Dao dao){ dao = this.dao}
    private final PostDao postDao;

    // [1] 게시물 등록
    public int writePost(PostDto postDto) {
        return postDao.writePost(postDto);
    } // func end


    // [2] 게시물 출력
    public PageDto findAllPost(int cno, int page, int count) {

        // [2.1] 페이지 당 조회할 시작 인덱스 번호 계산
        int startRow = (page - 1) * count;

        // [2.2] 총 자료 수 확인
        int totalCount = postDao.getTotalCount(cno);

        // [2.3] 전체 페이지 수 구하기
        int totalPage = totalCount % count == 0 ? totalCount / count : totalCount / count + 1;
        
        int btnCount = 5 ; // 한 화면에 보여지는 page 최대 버튼 수 ( 1~5 / 6~10 )
        // [2.4] 시작 버튼 구하기
        int startBtn = ( (page -1 ) / btnCount) * btnCount + 1 ;
        
        // [2.5] 끝 버튼 구하기
        int endBtn = startBtn + btnCount -1 ;
        if( endBtn > totalPage ) endBtn = totalPage; //if endBtn이 totalpage보다 크면 endBtn을 그대로 사용

        // [2.6] Dao에 자료 요청
        // 매개변수 : cno, startRow(시작 인덱스), count(페이지 당 게시물 수)

        List<PostDto> postList = postDao.findAll(cno, startRow, count);

        // [2.7] pageDto를 구성
        PageDto pageDto = new PageDto();
        pageDto.setCurrentPage( page );         // 현재 페이지 번호
        pageDto.setTotalPage( totalPage );      // 전체 페이지 수
        pageDto.setPerCount( count );           // 한 페이지 당 계시물 수
        pageDto.setTotalCount(totalCount);      // 전체 게시물 수
        pageDto.setStartBtn(startBtn);          // 페이지네이션에서 시작 페이지 번호
        pageDto.setEndBtn(endBtn);              // 페이지네이션에서 끝 페이지 번호
        pageDto.setData(postList);              // 실제 dto 데이터

        // [2.8] 결과 반환
        return pageDto;
    } // func end

} // class end