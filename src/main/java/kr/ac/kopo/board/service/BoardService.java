package kr.ac.kopo.board.service;


import jakarta.transaction.Transactional;
import kr.ac.kopo.board.dto.BoardDTO;
import kr.ac.kopo.board.entity.BoardEntity;
import kr.ac.kopo.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//DTO ->Entity 변환   (Entity class)에서할거임                        Controller ->service ->repository
//Entity -> DTO 변환  (DTO class)에서 할거임                         repository-> service ->controller
@Service
@RequiredArgsConstructor
public class BoardService {
    private  final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);

    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList=boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(BoardEntity boardEntity: boardEntityList){
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    @Transactional //jpa에 query 같은거 작성하면 트랜잭션 어노테이션을 써줘야됨.
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
        
    }

    public BoardDTO findById(Long id) {
       Optional<BoardEntity> optionalBoardEntity =boardRepository.findById(id);
       if(optionalBoardEntity.isPresent()){
          BoardEntity boardEntity =optionalBoardEntity.get();
          BoardDTO boardDTO =BoardDTO.toBoardDTO(boardEntity);
          return  boardDTO;
       }else {
           return null;
       }
    }

    public BoardDTO update(BoardDTO boardDTO) {
       BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
