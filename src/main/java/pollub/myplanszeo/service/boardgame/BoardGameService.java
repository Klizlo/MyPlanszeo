package pollub.myplanszeo.service.boardgame;

import pollub.myplanszeo.model.BoardGame;

import java.util.List;

//Tydzień 7, Zasada Otwarty/Zamknięty 1
//Interfejs dla klas serwisowych dla gier planszowych
public interface BoardGameService {

    List<BoardGame> getAllBoardGames();

    BoardGame getBoardGameById(Long id);

}
//Koniec, Tydzień 7, Zasada Otwarty/Zamknięty 1