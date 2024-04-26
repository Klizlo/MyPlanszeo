package pollub.myplanszeo.flyweight;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.service.boardgame.BoardGameService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//Tydzień 4, Wzorzec Flyweight 1
//Klasa ta jest cachem dla gier planszowych dostępnym w serwisie
//Pozwala to na zmniejszenie zapytań do bazy danych oraz na skrócenie czasu w jakim klient otrzyma żądanie
@Component
@RequiredArgsConstructor
public class BoardGameCache {

    @Autowired
//    @Qualifier("BoardGameService")
    @Qualifier("LoggingBoardGameService")
    private BoardGameService boardGameService;
    private static final Map<Long, BoardGame> longBoardGames = new HashMap<>();

    public List<BoardGame> getAllBoardGames() {
        if (longBoardGames.isEmpty()) {
            List<BoardGame> boardGames = boardGameService.getAllBoardGames();
            for (BoardGame boardGame : boardGames) {
                longBoardGames.put(boardGame.getId(), boardGame);
            }
        }
        return longBoardGames.values().stream().toList();
    }

    public BoardGame getBoardGameById(Long id) {
        BoardGame boardGame = longBoardGames.get(id);
        if (boardGame == null) {
            boardGame = boardGameService.getBoardGameById(id);
            longBoardGames.put(id, boardGame);
        }
        return boardGame;
    }

}
//Koniec, Tydzień 4, Wzorzec Flyweight 1
