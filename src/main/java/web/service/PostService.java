package web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.PostDao;
import web.model.dto.PageDto;
import web.model.dto.PostDto;

import java.util.ArrayList;
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
    public PageDto findAllPost(int cno, int page, int count, String key, String keyword) {

        // [2.1] 페이지 당 조회할 시작 인덱스 번호 계산
        int startRow = (page - 1) * count;

        int totalCount = 0 ;
        List<PostDto> postList = new ArrayList<>();
        // ☆★ 250826 / 조건문을 통해, 검색이 있는 경우와 없는 경우를 분류
        // 위치변경... [2.6] Dao에 자료 요청
        // 매개변수 : cno, startRow(시작 인덱스), count(페이지 당 게시물 수)
        if( key != null && !key.isEmpty() && keyword!=null && !keyword.isEmpty() ){
            // .(도트/접근 연산자)는 변수가 null 일 때, 함수를 사용한다는 것은 역설!
            // nullPointException 발생
            // ∴ 조건에 문을 부정으로 바꿈!!

            // ☆★☆★☆★ 검색을 할 때
            totalCount = postDao.getTotalCountSearch(cno, key, keyword);
            postList = postDao.findAllSearch(cno, page, count, key, keyword);
        } else {
            // 검색 null == 검색을 하지 않을 때
            // [2.2] 총 자료 수 확인
            totalCount = postDao.getTotalCount(cno);
            // [2.3]] dao를 통해서 자료의 개수를 반환
            postList = postDao.findAll(cno, startRow, count);

        }
        
        // [2.3] 전체 페이지 수 구하기
        int totalPage = totalCount % count == 0 ? totalCount / count : totalCount / count + 1;
        
        int btnCount = 5 ; // 한 화면에 보여지는 page 최대 버튼 수 ( 1~5 / 6~10 )
        // [2.4] 시작 버튼 구하기
        int startBtn = ( (page -1 ) / btnCount) * btnCount + 1 ;
        
        // [2.5] 끝 버튼 구하기
        int endBtn = startBtn + btnCount -1 ;
        if( endBtn > totalPage ) endBtn = totalPage; //if endBtn이 totalpage보다 크면 endBtn을 그대로 사용

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