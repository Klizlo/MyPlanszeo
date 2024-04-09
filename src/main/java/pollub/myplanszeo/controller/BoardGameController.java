package pollub.myplanszeo.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pollub.myplanszeo.dto.mapper.BaseBoardGameDtoMapper;
import pollub.myplanszeo.dto.mapper.BoardGameMapper;
import pollub.myplanszeo.dto.mapper.SimpleBoardGameMapper;
import pollub.myplanszeo.interpreter.BoardGameInterpreter;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.service.BoardGameService;

import java.util.List;

//Tydzień 5, Wzorzec Mediator 1
//Klasa kontrolera pełni funkcję mediatora
//Pośredniczy w komunikacji pomiędzy klientem a zasabom
@Controller
@RequiredArgsConstructor
public class BoardGameController {

    private final BoardGameService boardGameService;
    private BoardGameMapper boardGameMapper;

    @GetMapping("/boardgames")
    public String getAllBoardGames(HttpServletRequest request, Model model) {
        String params = request.getQueryString() == null ? "" : request.getQueryString();
        List<BoardGame> boardGames = boardGameService.getAllBoardGames();
        BoardGameInterpreter interpreter = new BoardGameInterpreter(boardGames);
        boardGameMapper = new SimpleBoardGameMapper();
        model.addAttribute("games", boardGameMapper.mapToDtos(interpreter.interpret(params)));
        return "boardgames";
    }

    @GetMapping("/boardgames/{id}")
    public String getBoardGameById(@PathVariable("id") Long id, Model model) {
        BoardGame boardGame = boardGameService.getBoardGameById(id);
        boardGameMapper = new BaseBoardGameDtoMapper();
        model.addAttribute("game", boardGameMapper.mapToDto(boardGame));

        return "boardgame";
    }

}
//Koniec, Tydzień 5, Wzorzec Mediator 1