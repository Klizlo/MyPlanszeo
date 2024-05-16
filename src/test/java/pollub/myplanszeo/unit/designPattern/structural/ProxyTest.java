package pollub.myplanszeo.unit.designPattern.structural;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pollub.myplanszeo.dto.boardgamelist.BoardGameListFactory;
import pollub.myplanszeo.dto.boardgamelist.FullBoardGameListDto;
import pollub.myplanszeo.exception.UnauthorizedException;
import pollub.myplanszeo.model.*;
import pollub.myplanszeo.proxy.BoardGameListServiceProxy;
import pollub.myplanszeo.service.boardgamelist.BoardGameListServiceImpl;
import pollub.myplanszeo.state.BoardGameListActiveState;
import pollub.myplanszeo.state.BoardGameListEditState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ProxyTest {

    @Mock
    private BoardGameListServiceImpl boardGameListService;

    @InjectMocks
    private BoardGameListServiceProxy boardGameListProxy;

    @Test
    public void givenUserId_whenGetAllBoardGameListsByUserId_returnBoardGameLists() {
        User user = new User(1L, "adam.nowak@poczta.pl", "Adqu28qtyubahhj1!?q", new ArrayList<>());
        List<BoardGameList> boardGameLists = Lists.newArrayList(
                new BoardGameList(1L, "Favorite", "", BoardGameListActiveState.instance(), null, user),
                new BoardGameList(2L, "To Play", "", BoardGameListActiveState.instance(), null, user)
        );
        user.setBoardGameLists(boardGameLists);

        when(boardGameListService.getAllBoardGameListByUserId(user.getId()))
                .thenReturn(boardGameLists);

        List<BoardGameList> boardGameListsFromFacade = boardGameListProxy.getAllBoardGameListByUserId(1L);

        assertThat(boardGameListsFromFacade.size()).isEqualTo(2);
    }

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

    @Test
    void givenBoardGameListIdAndUserId_whenGetBoardGameListByIdAndUserId_throwUnauthorizedException() {
        given(boardGameListService.existsBoardGameListByIdAndUserId(any(), any()))
                .willReturn(false);

        assertThatThrownBy(() -> boardGameListProxy.getBoardGameListByIdAndUserId(1L, 1L))
                .isInstanceOf(UnauthorizedException.class);
    }

    @Test
    public void givenBoardGameListAndUserId_whenAddBoardGameList_returnBoardGameList() {
        User user = new User(1L, "adam.nowak@poczta.pl", "Adqu28qtyubahhj1!?q", new ArrayList<>());
        BoardGameList boardGameList = new BoardGameList(1L, "Favorite", "",BoardGameListActiveState.instance(),  null, user);
        user.getBoardGameLists().add(boardGameList);

        when(boardGameListService.addBoardGameList(boardGameList, user.getId()))
                .thenReturn(boardGameList);

        BoardGameList addedBoardGameList = boardGameListProxy.addBoardGameList(boardGameList, user.getId());
        assertThat(addedBoardGameList).isEqualTo(boardGameList);
    }

    @Test
    public void givenBoardGameListAndFullBoardGameListDto_whenEditBoardGameList_thenReturnBoardGameList() {
        BoardGameList boardGameList = new BoardGameList(1L, "Favorite", "", BoardGameListActiveState.instance(), new HashSet<>(), null);
        FullBoardGameListDto fullBoardGameListDto = (FullBoardGameListDto) BoardGameListFactory.getBoardGameList(boardGameList, BoardGameListFactory.BoardGameListType.Full);
        fullBoardGameListDto.setName("Favorite 2");

        BoardGameList editedBoardGameList = new BoardGameList(boardGameList.getId(), fullBoardGameListDto.getName(), boardGameList.getDescription(), BoardGameListActiveState.instance(), null, null);

        when(boardGameListService.editBoardGameList(any(), any()))
                .thenReturn(editedBoardGameList);

        BoardGameList boardGameListAfterEdit = boardGameListProxy.editBoardGameList(boardGameList, fullBoardGameListDto);
        assertThat(boardGameListAfterEdit.getName()).isEqualTo(fullBoardGameListDto.getName());
    }

}
