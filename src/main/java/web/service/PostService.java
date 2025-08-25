package web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.PostDao;
import web.model.dto.PostDto;

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

} // class end