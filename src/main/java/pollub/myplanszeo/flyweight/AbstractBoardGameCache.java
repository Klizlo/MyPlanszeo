package pollub.myplanszeo.flyweight;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.service.boardgame.BoardGameService;

import java.util.List;

@Getter
public abstract class AbstractBoardGameCache {

    @Autowired
//    @Qualifier("BoardGameService")
    @Qualifier("LoggingBoardGameService")
    private BoardGameService boardGameService;

    public abstract List<BoardGame> getAllBoardGames();
    public abstract BoardGame getBoardGameById(Long id);

}
