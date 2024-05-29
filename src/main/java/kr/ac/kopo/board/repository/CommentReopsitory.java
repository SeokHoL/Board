package kr.ac.kopo.board.repository;

import kr.ac.kopo.board.entity.BoardEntity;
import kr.ac.kopo.board.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentReopsitory extends JpaRepository<CommentEntity, Long> {

    //select * from comment_table where board_id=? order by id desc;
    List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);

}




