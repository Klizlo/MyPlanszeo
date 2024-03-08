package pollub.myplanszeo.unit;

import org.junit.jupiter.api.Test;
import pollub.myplanszeo.dto.BoardGameListDto;
import pollub.myplanszeo.dto.BoardGameListFactory;
import pollub.myplanszeo.dto.FullBoardGameListDto;
import pollub.myplanszeo.dto.SimpleBoardGameListDto;
import pollub.myplanszeo.model.AgeRestriction;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.model.Category;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class FactoryTest {

    @Test
    public void givenBoardGameList_whenGetBoarGameListWithSimpleType_returnSimpleBoardGameListDto() {
        Category cardGame = new Category(1L, "Card Game", new ArrayList<>());
        Category cooperative = new Category(2L, "Cooperative", new ArrayList<>());
        Set<BoardGame> boardGames = new HashSet<>();
        boardGames.add(new BoardGame(1L, "Munchkin", AgeRestriction.PLUS_7, "", "BlackMonkGames", 2, 6, cardGame, null));
        boardGames.add(new BoardGame(2L, "Mansion of Madness", AgeRestriction.PLUS_12, "", "FFG", 1, 5, cooperative, null));

        BoardGameList boardGameList = new BoardGameList(1L, "Favorite", "", boardGames);

        BoardGameListDto simpleBoardGameList = BoardGameListFactory.getBoardGameList(boardGameList, BoardGameListFactory.BoardGameListType.Simple);

        assertInstanceOf(SimpleBoardGameListDto.class, simpleBoardGameList);
    }

    @Test
    public void givenBoardGameList_whenGetBoarGameListWithSimpleType_returnFullBoardGameListDto() {
        Category cardGame = new Category(1L, "Card Game", new ArrayList<>());
        Category cooperative = new Category(2L, "Cooperative", new ArrayList<>());
        Set<BoardGame> boardGames = new HashSet<>();
        boardGames.add(new BoardGame(1L, "Munchkin", AgeRestriction.PLUS_7, "", "BlackMonkGames", 2, 6, cardGame, null));
        boardGames.add(new BoardGame(2L, "Mansion of Madness", AgeRestriction.PLUS_12, "", "FFG", 1, 5, cooperative, null));

        BoardGameList boardGameList = new BoardGameList(1L, "Favorite", "", boardGames);

        BoardGameListDto fullBoardGameList = BoardGameListFactory.getBoardGameList(boardGameList, BoardGameListFactory.BoardGameListType.Full);

        assertInstanceOf(FullBoardGameListDto.class, fullBoardGameList);
    }

}
