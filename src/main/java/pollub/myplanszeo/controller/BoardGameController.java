package pollub.myplanszeo.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pollub.myplanszeo.dto.mapper.BaseBoardGameDtoMapper;
import pollub.myplanszeo.dto.mapper.BoardGameMapper;
import pollub.myplanszeo.dto.mapper.CategoryMapper;
import pollub.myplanszeo.dto.mapper.SimpleBoardGameMapper;
import pollub.myplanszeo.flyweight.BoardGameCache;
import pollub.myplanszeo.interpreter.BoardGameInterpreter;
import pollub.myplanszeo.model.BoardGame;

import java.util.List;

//Tydzień 5, Wzorzec Mediator 1
//Klasa kontrolera pełni funkcję mediatora
//Pośredniczy w komunikacji pomiędzy klientem a zasabom
@Controller
@RequiredArgsConstructor
public class BoardGameController {

    private final BoardGameCache boardGameCache;
    private BoardGameMapper boardGameMapper;

    @GetMapping("/boardgames")
    public String getAllBoardGames(HttpServletRequest request, Model model) {
        String params = request.getQueryString() == null ? "" : request.getQueryString().replaceAll("\\++", " ");
        List<BoardGame> boardGames = boardGameCache.getAllBoardGames();
        BoardGameInterpreter interpreter = new BoardGameInterpreter(boardGames);
        boardGameMapper = new SimpleBoardGameMapper();
        model.addAttribute("games", boardGameMapper.mapToDtos(interpreter.interpret(params)));
        model.addAttribute("categories", CategoryMapper.mapToDtos(boardGames
                .stream()
                .map(BoardGame::getCategory)
                .distinct()
                .toList()));
        model.addAttribute("producers", boardGames.stream()
                .map(BoardGame::getProducer)
                .distinct()
                .toList());
        return "boardgames";
    }

    @GetMapping("/boardgames/{id}")
    public String getBoardGameById(@PathVariable("id") Long id, Model model) {
        BoardGame boardGame = boardGameCache.getBoardGameById(id);
        boardGameMapper = new BaseBoardGameDtoMapper();
        model.addAttribute("game", boardGameMapper.mapToDto(boardGame));

        return "boardgame";
    }

}
//Koniec, Tydzień 5, Wzorzec Mediator 1