package pollub.myplanszeo.flyweight;

import org.springframework.stereotype.Component;
import pollub.myplanszeo.model.BoardGame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//Tydzień 4, Wzorzec Flyweight 1
//Klasa ta jest cachem dla gier planszowych dostępnym w serwisie
//Pozwala to na zmniejszenie zapytań do bazy danych oraz na skrócenie czasu w jakim klient otrzyma żądanie
@Component
public class BoardGameCache extends AbstractBoardGameCache {

    private static final Map<Long, BoardGame> longBoardGames = new HashMap<>();

    @Override
    public List<BoardGame> getAllBoardGames() {
        if (longBoardGames.isEmpty()) {
            List<BoardGame> boardGames = getBoardGameService().getAllBoardGames();
            for (BoardGame boardGame : boardGames) {
                longBoardGames.put(boardGame.getId(), boardGame);
            }
        }
        return longBoardGames.values().stream().toList();
    }

    @Override
    public BoardGame getBoardGameById(Long id) {
        BoardGame boardGame = longBoardGames.get(id);
        if (boardGame == null) {
            boardGame = getBoardGameService().getBoardGameById(id);
            longBoardGames.put(id, boardGame);
        }
        return boardGame;
    }

}
//Koniec, Tydzień 4, Wzorzec Flyweight 1
