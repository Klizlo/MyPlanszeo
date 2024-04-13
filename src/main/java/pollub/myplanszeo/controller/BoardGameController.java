package pollub.myplanszeo.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pollub.myplanszeo.config.security.CustomUserDetails;
import pollub.myplanszeo.dto.boardgamelist.BoardGameListFactory;
import pollub.myplanszeo.dto.mapper.*;
import pollub.myplanszeo.facade.BoardGameListFacade;
import pollub.myplanszeo.flyweight.BoardGameCache;
import pollub.myplanszeo.interpreter.BoardGameInterpreter;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.BoardGameList;

import java.util.List;

//Tydzień 5, Wzorzec Mediator 1
//Klasa kontrolera pełni funkcję mediatora
//Pośredniczy w komunikacji pomiędzy klientem a zasabom
@Controller
@RequiredArgsConstructor
public class BoardGameController {

    private final BoardGameCache boardGameCache;
    private BoardGameMapper boardGameMapper;
    private final BoardGameListFacade boardGameListFacade;

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
        return "boardgame/boardgames";
    }

    @GetMapping("/boardgames/{id}")
    public String getBoardGameById(@PathVariable("id") Long id, Model model, Authentication authentication) {
        BoardGame boardGame = boardGameCache.getBoardGameById(id);
        boardGameMapper = new BaseBoardGameDtoMapper();
        model.addAttribute("game", boardGameMapper.mapToDto(boardGame));

        if (authentication != null && authentication.isAuthenticated()) {
            getUserBoardGameListsWhenIsAuthenticated(id, model, authentication);
        }

        return "boardgame/boardgame";
    }

    private void getUserBoardGameListsWhenIsAuthenticated(Long id, Model model, Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        List<BoardGameList> boardGameLists = boardGameListFacade.getAllBoardGameListsByUserId(principal.getId());
        model.addAttribute("lists", BoardGameListMapper.mapToDtos(boardGameLists, BoardGameListFactory.BoardGameListType.Simple));
        List<Long> listsWithGivenGame = boardGameLists.stream()
                .filter(boardGameList -> boardGameList.getBoardGames()
                        .stream()
                        .map(BoardGame::getId)
                        .anyMatch(gameId -> gameId.equals(id)))
                .map(BoardGameList::getId)
                .toList();
        model.addAttribute("listsWithGivenGame", listsWithGivenGame);

    }

}
//Koniec, Tydzień 5, Wzorzec Mediator 1