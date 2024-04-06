package pollub.myplanszeo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pollub.myplanszeo.dto.BaseBoardGameDto;
import pollub.myplanszeo.dto.BoardGameDto;
import pollub.myplanszeo.dto.SimpleBoardGameDto;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.BoardGameIterator;
import pollub.myplanszeo.service.BoardGameService;

import java.util.ArrayList;
import java.util.List;

//Tydzień 5, Wzorzec Mediator 1
//Klasa kontrolera pełni funkcję mediatora
//Pośredniczy w komunikacji pomiędzy klientem a zasabom
@Controller
@RequiredArgsConstructor
public class BoardGameController {

    private final BoardGameService boardGameService;

    @GetMapping("/boardgames")
    public String getAllBoardGames(Model model) {
        List<BoardGameDto> boardGames = new ArrayList<>();
        BoardGameIterator iterator = new BoardGameIterator(boardGameService.getAllBoardGames());
        while (iterator.hasNext()) {
            BoardGame game = iterator.next();
            boardGames.add(new SimpleBoardGameDto.Builder(game.getId(), game.getName(), game.getProducer(), game.getCategory())
                    .setAgeRestriction(game.getAgeRestriction())
                    .setDescription(game.getDescription())
                    .setMinNumOfPlayers(game.getMinNumOfPlayers())
                    .setMaxNumOfPlayers(game.getMaxNumOfPlayers())
                    .build());
        }
        model.addAttribute("games", boardGames);
        return "boardgames";
    }

    @GetMapping("/boardgames/{id}")
    public String getBoardGameById(@PathVariable("id") Long id, Model model) {
        BoardGame boardGame = boardGameService.getBoardGameById(id);
        BaseBoardGameDto baseBoardGameDto = new BaseBoardGameDto.Builder(boardGame.getId(), boardGame.getName(), boardGame.getProducer(), boardGame.getCategory(), boardGame.getDlcs())
                .setAgeRestriction(boardGame.getAgeRestriction())
                .setDescription(boardGame.getDescription())
                .setMinNumOfPlayers(boardGame.getMinNumOfPlayers())
                .setMaxNumOfPlayers(boardGame.getMaxNumOfPlayers())
                .build();
        model.addAttribute("game", baseBoardGameDto);

        return "boardgame";
    }

}
//Koniec, Tydzień 5, Wzorzec Mediator 1