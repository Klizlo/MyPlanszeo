package pollub.myplanszeo.service.boardgame;

import pollub.myplanszeo.model.BoardGame;

import java.util.List;

public interface BoardGameService {

    List<BoardGame> getAllBoardGames();

    BoardGame getBoardGameById(Long id);

}
