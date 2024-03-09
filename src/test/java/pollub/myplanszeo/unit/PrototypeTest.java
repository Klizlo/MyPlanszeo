package pollub.myplanszeo.unit;

import org.junit.jupiter.api.Test;
import pollub.myplanszeo.model.AgeRestriction;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.model.Category;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PrototypeTest {

    @Test
    public void givenBoardGameList_whenClone_returnClonedBoardGameList() throws CloneNotSupportedException {
        Category cardGame = new Category(1L, "Card Game", new ArrayList<>());
        Category cooperative = new Category(2L, "Cooperative", new ArrayList<>());
        Set<BoardGame> boardGames = new HashSet<>();
        boardGames.add(new BoardGame(1L, "Munchkin", AgeRestriction.PLUS_7, "", "BlackMonkGames", 2, 6, cardGame, new HashSet<>()));
        boardGames.add(new BoardGame(2L, "Mansion of Madness", AgeRestriction.PLUS_12, "", "FFG", 1, 5, cooperative, new HashSet<>()));

        BoardGameList boardGameList = new BoardGameList(1L, "Favorite", "", boardGames);

        BoardGameList clonedBoardGameList = (BoardGameList) boardGameList.clone();

        assertNull(clonedBoardGameList.getId());
        assertEquals(boardGameList.getName(),               clonedBoardGameList.getName());
        assertEquals(boardGameList.getDescription(),        clonedBoardGameList.getDescription());
        assertEquals(boardGameList.getBoardGames().size(),  clonedBoardGameList.getBoardGames().size());
    }
}
