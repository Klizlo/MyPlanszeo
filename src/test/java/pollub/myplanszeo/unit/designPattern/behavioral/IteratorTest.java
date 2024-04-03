package pollub.myplanszeo.unit.designPattern.behavioral;

import org.junit.jupiter.api.Test;
import pollub.myplanszeo.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class IteratorTest {

    @Test
    public void givenBoardGameList_whenUseIterator_GetNextBoardGame() {

        BoardGameList boardGameList = getBoardGameList();
        Iterator<BoardGame> iterator = boardGameList.iterator();
        int iteratorCounter = 0;

        while(iterator.hasNext()) {
            iteratorCounter++;
            assertThat(boardGameList.getBoardGames().contains(iterator.next())).isTrue();
        }

        assertThat(iteratorCounter).isEqualTo(2);

    }

    public BoardGameList getBoardGameList() {
        Category cardGame = new Category(1L, "Card Game", new ArrayList<>());
        Category cooperative = new Category(2L, "Cooperative", new ArrayList<>());
        Set<BoardGame> boardGames = new HashSet<>();
        boardGames.add(new BoardGame(1L, "Munchkin", AgeRestriction.PLUS_7, "", "BlackMonkGames", 2, 6, cardGame, new HashSet<>(), null, null));
        boardGames.add(new BoardGame(2L, "Mansion of Madness", AgeRestriction.PLUS_12, "", "FFG", 1, 5, cooperative, new HashSet<>(), null, null));

        BoardGameList boardGameList = new BoardGameList(1L, "Favorite", "", boardGames, new User(1L, "adam.nowak@poczta.pl", "AFabcabcbahucyba", null));

        return boardGameList;
    }

}
