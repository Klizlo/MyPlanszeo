package pollub.myplanszeo.unit.designPattern.structural;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pollub.myplanszeo.exception.BoardGameNotFoundException;
import pollub.myplanszeo.flyweight.BoardGameCache;
import pollub.myplanszeo.model.AgeRestriction;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.Category;
import pollub.myplanszeo.service.boardgame.BoardGameService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlyweightTest {

    @Mock
    private BoardGameService boardGameService;

    @InjectMocks
    private BoardGameCache boardGameCache;

    @Test
    public void whenGetAllBoardGames_returnBoardGames() {
        Category cardGame = new Category(1L, "Card Game", new ArrayList<>());
        Category cooperative = new Category(2L, "Cooperative", new ArrayList<>());
        List<BoardGame> boardGames = new ArrayList<>();
        boardGames.add(new BoardGame(1L, "Munchkin", AgeRestriction.PLUS_7, "", "BlackMonkGames", 2, 6, cardGame, new HashSet<>(), null, null));
        boardGames.add(new BoardGame(2L, "Mansion of Madness", AgeRestriction.PLUS_12, "", "FFG", 1, 5, cooperative, new HashSet<>(), null, null));

        when(boardGameService.getAllBoardGames())
                .thenReturn(boardGames);

        for (int i = 0; i < 100; i++) {
            List<BoardGame> boardGamesFromCache = boardGameCache.getAllBoardGames();
            assertThat(boardGamesFromCache.size()).isEqualTo(2);
            assertThat(boardGamesFromCache.containsAll(boardGames)).isTrue();
        }
    }

    @Test
    public void givenBoardGameID_whenGetBoardGameById_returnBoardGame() {
        Category adventure = new Category(3L, "Cooperative", new ArrayList<>());
        BoardGame witcher = new BoardGame(3L, "The witcher: old world", AgeRestriction.PLUS_12, "", "GoOnBoard", 1, 4, adventure, new HashSet<>(), null, null);

        when(boardGameService.getBoardGameById(3L))
                .thenReturn(witcher);

        for (int i = 0; i < 100; i++) {
            BoardGame boardGameFromCache = boardGameCache.getBoardGameById(3L);
            assertThat(boardGameFromCache).isEqualTo(witcher);
        }
    }

    @Test
    public void givenId_whenGetBoardGameById_throwsBoardGameNotFoundException() {

        when(boardGameService.getBoardGameById(10L))
                .thenThrow(new BoardGameNotFoundException());

        assertThatThrownBy(() -> boardGameCache.getBoardGameById(10L))
                .isInstanceOf(BoardGameNotFoundException.class);

    }

}
