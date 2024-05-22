package kr.ac.kopo.board.controller;

import kr.ac.kopo.board.dto.BoardDTO;
import kr.ac.kopo.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor // final로 선언된 필드를 자동으로 초기화 -> 생성자를 만들어줌
@RequestMapping("/board") // /board를 대표적으로 묶어줌. 예를들면  밑에 /board/save를 해야되는데 여기서 /board정의해버려서 밑에는 save만 써주면됨
public class BoardController {
    private final BoardService boardService;  // ->public BoardController(BoardService boardService) {this.boardService = boardService;}이 되어 BoardController객체는 boardService 필드를 주입받아 사용할수 있게된다.

    @GetMapping("/save")
    public String saveForm(){
        return "save";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO){ //@ModelAttribute를 이용해 BoardDTO객체 필드를 다가져옴
        System.out.println("boardDTO= " +boardDTO);
        boardService.save(boardDTO);
        return  "index";
    }
    @GetMapping("/")
    public String findAll(Model model){ //Model 객체는 컨트롤러에서 뷰로 데이터를 전달하기 위해 사용
        //DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
        List<BoardDTO> boardDTOList =boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){ //Model은 데이터를 담아가야하니깐 쓰는 객체
        /*
            해당 게시글의 조회수를 하나 올리고
            게시글 데이터를 가져와서 detail.html에 출력
        */
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "detail";
    }

    @GetMapping("/update/{id}")
    public  String updateForm(@PathVariable Long id, Model model){
       BoardDTO boardDTO = boardService.findById(id);
       model.addAttribute("boardUpdate", boardDTO);
       return  "update";

    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model){
       BoardDTO board =boardService.update(boardDTO);
       model.addAttribute("board",board);
       return "detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        boardService.delete(id);
        return "redirect:/board/";
    }
}
