package pollub.myplanszeo.unit.designPattern.structural;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pollub.myplanszeo.model.*;
import pollub.myplanszeo.proxy.BoardGameListServiceProxy;
import pollub.myplanszeo.service.boardgamelist.BoardGameListServiceImpl;
import pollub.myplanszeo.state.BoardGameListActiveState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class ProxyTest {

    @Mock
    private BoardGameListServiceImpl boardGameListService;

    @InjectMocks
    private BoardGameListServiceProxy boardGameListProxy;

    @Test
    void givenBoardGameListIdAndUserId_whenGetBoardGameListByIdAndUserId_returnBoardGameList() {

        User user = new User(1L, "adam.nowak@poczta.pl", "AFabcabcbahucyba", new ArrayList<>());
        Category cardGame = new Category(1L, "Card Game", new ArrayList<>());
        Category cooperative = new Category(2L, "Cooperative", new ArrayList<>());
        Set<BoardGame> boardGames = new HashSet<>();
        boardGames.add(new BoardGame(1L, "Munchkin", AgeRestriction.PLUS_7, "", "BlackMonkGames", 2, 6, cardGame, new HashSet<>(), null, null));
        boardGames.add(new BoardGame(2L, "Mansion of Madness", AgeRestriction.PLUS_12, "", "FFG", 1, 5, cooperative, new HashSet<>(), null, null));

        BoardGameList boardGameList = new BoardGameList(1L, "Favorite", "", BoardGameListActiveState.instance(), boardGames, user);

        given(boardGameListService.existsBoardGameListByIdAndUserId(1L, 1L))
                .willReturn(true);
        given(boardGameListService.getBoardGameListByIdAndUserId(1L, 1L))
                .willReturn(boardGameList);

        BoardGameList boardGameListByIdAndUserId = boardGameListProxy.getBoardGameListByIdAndUserId(1L, 1L);

        assertThat(boardGameListByIdAndUserId.getId()).isEqualTo(1L);
        assertThat(boardGameListByIdAndUserId.getName()).isEqualTo("Favorite");
        assertThat(boardGameListByIdAndUserId.getUser()).isEqualTo(user);
    }

}
