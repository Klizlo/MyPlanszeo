package pollub.myplanszeo.command.boardgamelist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.repository.BoardGameListRepository;

import java.util.List;


//Tydzień 2, Wzorzec Factory 2
// Wzorzec Factory służy do wybrania, który rodzaj klasy Command należy zwrócić
// Klasa została utworzona w celu czytelniejszego wywoływania odpowiedniej klasy Command
@Component
@RequiredArgsConstructor
public class BoardGameListCommandFactory {

    private final BoardGameListRepository boardGameListRepository;

    public BoardGameListCommand create(BoardGameListCommand.CommandType commandCode, Object... params) {
        switch (commandCode) {
            case FIND_BOARD_GAME_LISTS -> {
                return new FindBoardGameListsCommand(boardGameListRepository, (Long) params[0]);
            }
            case FIND_BOARD_GAME_LIST -> {
                return new FindBoardGameListCommand(boardGameListRepository, (Long) params[0]);
            }
            case ADD_BOARD_GAME_LIST -> {
                return new AddBoardGameListCommand(boardGameListRepository, (BoardGameList) params[0]);
            }
            case ADD_BOARD_GAME_TO_LISTS -> {
                return new AddBoardGameToBoardGameLists(boardGameListRepository, (List<BoardGameList>) params[0], (BoardGame) params[1]);
            }
            case REMOVE_BOARD_GAME_FROM_LISTS -> {
                return new RemoveBoardGameFromBoardGameLists(boardGameListRepository, (List<BoardGameList>) params[0], (BoardGame) params[1]);
            }
            case CHECK_BOARD_GAME_LIST -> {
                return new CheckBoardGameListCommand(boardGameListRepository, (Long) params[0], (Long) params[1]);
            }
            default -> {
                return null;
            }
        }
    }

}
// Koniec, Tydzień 2, Wzorzec Factory 2