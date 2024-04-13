package pollub.myplanszeo.unit.designPattern.creational;

import org.junit.jupiter.api.Test;
import pollub.myplanszeo.model.*;
import pollub.myplanszeo.state.BoardGameListActiveState;

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
        boardGames.add(new BoardGame(1L, "Munchkin", AgeRestriction.PLUS_7, "", "BlackMonkGames", 2, 6, cardGame, new HashSet<>(), null, null));
        boardGames.add(new BoardGame(2L, "Mansion of Madness", AgeRestriction.PLUS_12, "", "FFG", 1, 5, cooperative, new HashSet<>(), null, null));

        BoardGameList boardGameList = new BoardGameList(1L, "Favorite", "", BoardGameListActiveState.instance(), boardGames, new User(1L, "adam.nowak@poczta.pl", "AFabcabcbahucyba", null));

        BoardGameList clonedBoardGameList = (BoardGameList) boardGameList.clone();

        assertNull(clonedBoardGameList.getId());
        assertEquals(boardGameList.getName(),               clonedBoardGameList.getName());
        assertEquals(boardGameList.getDescription(),        clonedBoardGameList.getDescription());
        assertEquals(boardGameList.getBoardGames().size(),  clonedBoardGameList.getBoardGames().size());
    }
}
