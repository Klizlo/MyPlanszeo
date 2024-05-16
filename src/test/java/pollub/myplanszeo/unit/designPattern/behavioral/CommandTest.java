package pollub.myplanszeo.unit.designPattern.behavioral;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pollub.myplanszeo.command.boardgamelist.*;
import pollub.myplanszeo.model.*;
import pollub.myplanszeo.repository.BoardGameListRepository;
import pollub.myplanszeo.state.BoardGameListActiveState;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommandTest {

    @Mock
    private BoardGameListRepository boardGameListRepository;

    @InjectMocks
    private BoardGameListCommandFactory boardGameListCommandFactory;

    @Test
    public void givenCommandType_whenCreateAddBoardGameListCommand_returnAddBoardGameListCommand() {

        BoardGameListCommand.CommandType type = BoardGameListCommand.CommandType.ADD_BOARD_GAME_LIST;
        BoardGameList boardGameList = getBoardGameList();

        when(boardGameListRepository.save(any(BoardGameList.class)))
                .thenReturn(boardGameList);

        BoardGameListCommand boardGameListCommand = boardGameListCommandFactory.create(type, boardGameList);
        Object boardGameListFromCommand = boardGameListCommand.execute();

        assertThat(boardGameListCommand).isInstanceOf(AddBoardGameListCommand.class);
        assertThat(boardGameListFromCommand).isInstanceOf(BoardGameList.class);

    }

    @Test
    public void givenCommandType_whenCreateFindBoardGameListCommand_returnFindBoardGameListCommand() {

        BoardGameListCommand.CommandType type = BoardGameListCommand.CommandType.FIND_BOARD_GAME_LIST;
        BoardGameList boardGameList = getBoardGameList();

        when(boardGameListRepository.findById(any()))
                .thenReturn(Optional.of(boardGameList));

        BoardGameListCommand boardGameListCommand = boardGameListCommandFactory.create(type, 1L);
        Object boardGameListFromCommand = boardGameListCommand.execute();

        assertThat(boardGameListCommand).isInstanceOf(FindBoardGameListCommand.class);
        assertThat(boardGameListFromCommand).isInstanceOf(BoardGameList.class);

    }

    @Test
    public void givenCommandType_whenCreateFindBoardGameListsCommand_returnFindBoardGameListsCommand() {

        BoardGameListCommand.CommandType type = BoardGameListCommand.CommandType.FIND_BOARD_GAME_LISTS;
        List<BoardGameList> boardGameLists = List.of(getBoardGameList());

        when(boardGameListRepository.findAllByUser_Id(any()))
                .thenReturn(boardGameLists);

        BoardGameListCommand boardGameListCommand = boardGameListCommandFactory.create(type, 1L);
        List<BoardGameList> boardGameListsFromCommand = (List<BoardGameList>) boardGameListCommand.execute();

        assertThat(boardGameListCommand).isInstanceOf(FindBoardGameListsCommand.class);
        assertThat(boardGameListsFromCommand.size()).isEqualTo(1);

    }

    @Test
    public void givenCommandType_whenCreateCheckBoardGameListCommand_returnCheckBoardGameListCommand() {

        BoardGameListCommand.CommandType type = BoardGameListCommand.CommandType.CHECK_BOARD_GAME_LIST;

        when(boardGameListRepository.existsByIdAndUser_Id(any(), any()))
                .thenReturn(true);

        BoardGameListCommand boardGameListCommand = boardGameListCommandFactory.create(type, 1L, 1L);
        Object boardGameListFromCommand = boardGameListCommand.execute();

        assertThat(boardGameListCommand).isInstanceOf(CheckBoardGameListCommand.class);
        assertThat(boardGameListFromCommand).isInstanceOf(Boolean.class);
        assertThat((boolean) boardGameListFromCommand).isTrue();

    }

    @Test
    public void givenCommandType_whenCreateAddBoardGameToBoardGameListsCommand_returnCheckBoardGameListCommand() {
        BoardGameListCommand.CommandType type = BoardGameListCommand.CommandType.ADD_BOARD_GAME_TO_LISTS;

        List<BoardGameList> boardGameLists = List.of(getBoardGameList());
        Category cooperative = new Category(2L, "Cooperative", new ArrayList<>());
        BoardGame descent = new BoardGame(3L, "Descent: Legends of the dark", AgeRestriction.PLUS_12, "", "FFG", 1, 4, cooperative, new HashSet<>(), null, null);

        List<BoardGameList> modified = List.copyOf(boardGameLists);
        modified.forEach(boardGameList -> boardGameList.getBoardGames().add(descent));
        when(boardGameListRepository.saveAll(any()))
                .thenReturn(modified);

        BoardGameListCommand boardGameListCommand = boardGameListCommandFactory.create(type, boardGameLists, descent);
        List<BoardGameList> boardGameListFromCommand = (List<BoardGameList>) boardGameListCommand.execute();
        assertThat(boardGameListCommand).isInstanceOf(AddBoardGameToBoardGameLists.class);
        for(BoardGameList boardGameList: boardGameListFromCommand) {
            assertThat(boardGameList.getBoardGames()).contains(descent);
        }
    }

    public BoardGameList getBoardGameList() {
        Category cardGame = new Category(1L, "Card Game", new ArrayList<>());
        Category cooperative = new Category(2L, "Cooperative", new ArrayList<>());
        Set<BoardGame> boardGames = new HashSet<>();
        boardGames.add(new BoardGame(1L, "Munchkin", AgeRestriction.PLUS_7, "", "BlackMonkGames", 2, 6, cardGame, new HashSet<>(), null, null));
        boardGames.add(new BoardGame(2L, "Mansion of Madness", AgeRestriction.PLUS_12, "", "FFG", 1, 5, cooperative, new HashSet<>(), null, null));

        BoardGameList boardGameList = new BoardGameList(1L, "Favorite", "", BoardGameListActiveState.instance(), boardGames, new User(1L, "adam.nowak@poczta.pl", "AFabcabcbahucyba", null));

        return boardGameList;
    }

}
