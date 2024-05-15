package pollub.myplanszeo.service.boardgamelist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pollub.myplanszeo.command.boardgamelist.BoardGameListCommand;
import pollub.myplanszeo.command.boardgamelist.BoardGameListCommandFactory;
import pollub.myplanszeo.dto.boardgamelist.FullBoardGameListDto;
import pollub.myplanszeo.flyweight.AbstractBoardGameCache;
import pollub.myplanszeo.functional.BoardGameFilter;
import pollub.myplanszeo.functional.BoardGameListFilter;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.model.User;
import pollub.myplanszeo.observer.Observable;
import pollub.myplanszeo.service.notification.NotificationService;
import pollub.myplanszeo.service.user.UserService;

import java.util.List;
import java.util.stream.Stream;

//Tydzień 6, Wzorzec Observer 1
//Implementuje interfejs Observable, ma za zadnie poinformowanie obserwatorów, gdy lista gier zostanie zeedytowana
//Tydzień 7, Zasada Otwarty/Zamknięty 3
//Klasa implementująca interfejs BoardGameListService
public class BoardGameListServiceImpl implements BoardGameListService, Observable {

    @Autowired
    private BoardGameListCommandFactory commandFactory;
    @Autowired
    private UserService userService;
    @Autowired
    private AbstractBoardGameCache boardGameCache;
    @Autowired
    private List<NotificationService> notificationServices;

    @Override
    public List<BoardGameList> getAllBoardGameListByUserId(Long userId) {
        return (List<BoardGameList>) commandFactory
                .create(BoardGameListCommand.CommandType.FIND_BOARD_GAME_LISTS, userId)
                .execute();
    }

    @Override
    public BoardGameList getBoardGameListByIdAndUserId(Long boardGameListId, Long userId) {
        return (BoardGameList) commandFactory
                .create(BoardGameListCommand.CommandType.FIND_BOARD_GAME_LIST, boardGameListId)
                .execute();
    }

    @Override
    @Transactional
    public BoardGameList addBoardGameList(BoardGameList boardGameList, Long userId) {
        User user = userService.getUserById(userId);
        boardGameList.setUser(user);
        user.getBoardGameLists().add(boardGameList);

        return (BoardGameList) commandFactory
                .create(BoardGameListCommand.CommandType.ADD_BOARD_GAME_LIST, boardGameList)
                .execute();
    }

    @Override
    @Transactional
    public List<BoardGameList> modifyBoardGameInBoardGameLists(Long gameId, List<Long> selectedLists, Long userId) {
        List<BoardGameList> gameLists = getAllBoardGameListByUserId(userId);
        BoardGame boardGame = boardGameCache.getBoardGameById(gameId);

        List<BoardGameList> boardGameListsAfterAddGame = addBoardGameToBoardGameLists(gameLists, selectedLists, boardGame);
        List<BoardGameList> boardGameListsAfterRemoveGame = removeBoardGameFromBoardGameLists(gameLists, boardGame);

        return Stream.concat(boardGameListsAfterAddGame.stream(), boardGameListsAfterRemoveGame.stream())
                .toList();
    }

    private List<BoardGameList> removeBoardGameFromBoardGameLists(List<BoardGameList> gameLists, BoardGame boardGame) {
        //Tydzień 10, programowanie funkcyjne 1
        //Wykorzystanie filtru do filtracji danych w strumieniu danych
        BoardGameListFilter filter = boardGameList -> boardGameList.getBoardGames().contains(boardGame);
        List<BoardGameList> boardGameListsToRemoveGame = gameLists.stream()
                .filter(filter::filter)
                .toList();
        //Koniec, Tydzień 10, programowanie funkcyjne 2
        return (List<BoardGameList>) commandFactory
                .create(BoardGameListCommand.CommandType.REMOVE_BOARD_GAME_FROM_LISTS, boardGameListsToRemoveGame, boardGame)
                .execute();
    }

    private List<BoardGameList> addBoardGameToBoardGameLists(List<BoardGameList> gameLists, List<Long> selected, BoardGame boardGame) {
        //Tydzień 10, programowanie funkcyjne 2
        //Wykorzystanie filtru do filtracji danych w strumieniu danych
        BoardGameListFilter filter = boardGameList -> selected.contains(boardGameList.getId())
                && !boardGameList.getBoardGames().contains(boardGame);

        List<BoardGameList> boardGameListsToAddGame = gameLists.stream()
                .filter(filter::filter)
                .toList();
        //Koniec, Tydzień 10, programowanie funkcyjne 2
        return (List<BoardGameList>) commandFactory
                .create(BoardGameListCommand.CommandType.ADD_BOARD_GAME_TO_LISTS, boardGameListsToAddGame, boardGame)
                .execute();
    }

    @Override
    @Transactional
    public BoardGameList editBoardGameList(BoardGameList boardGameListToEdit, FullBoardGameListDto boardGameList) {
        if (boardGameList.getBoardGames() != null && boardGameList.getBoardGames().size() > boardGameListToEdit.getBoardGames().size()) {
            addBoardGamesToBoardGameList(boardGameListToEdit, boardGameList);
        } else if(boardGameList.getBoardGames() != null && boardGameList.getBoardGames().size() < boardGameListToEdit.getBoardGames().size()) {
            removeBoardGamesFromBoardGameList(boardGameListToEdit, boardGameList);
        }
        BoardGameList editedBoarGameList = (BoardGameList) commandFactory
                .create(BoardGameListCommand.CommandType.EDIT_BOARD_GAME_LIST, boardGameListToEdit, boardGameList)
                .execute();
        notifyAllObservers(editedBoarGameList);
        return editedBoarGameList;
    }

    private void removeBoardGamesFromBoardGameList(BoardGameList boardGameListToEdit, FullBoardGameListDto boardGameList) {
        //Tydzień 10, programowanie funkcyjne 1
        //Wykorzystanie filtru do filtracji danych w strumieniu danych
        BoardGameFilter filter = boardGame -> boardGameList.getBoardGames().stream()
                .map(BoardGame::getId)
                .noneMatch(id -> boardGame.getId().equals(id));
        List<BoardGame> boardGames = boardGameListToEdit.getBoardGames().stream()
                .filter(filter::filter)
                .toList();
        //Koniec, Tydzień 10, programowanie funkcyjne 1
        for (BoardGame boardGame: boardGames) {
            boardGame.getBoardGameLists().remove(boardGameListToEdit);
            boardGameListToEdit.getBoardGames().remove(boardGame);
        }
    }

    private void addBoardGamesToBoardGameList(BoardGameList boardGameListToEdit, FullBoardGameListDto boardGameList) {
        //Tydzień 10, programowanie funkcyjne 1
        //Wykorzystanie filtru do filtracji danych w strumieniu danych
        BoardGameFilter filter = boardGame -> !boardGameListToEdit.getBoardGames().contains(boardGame);
        List<BoardGame> boardGames = boardGameList.getBoardGames().stream()
                .filter(filter::filter)
                .map(boardGame -> boardGameCache.getBoardGameById(boardGame.getId()))
                .toList();
        //Koniec, Tydzień 10, programowanie funkcyjne 1
        for (BoardGame boardGame: boardGames) {
            boardGame.getBoardGameLists().add(boardGameListToEdit);
            boardGameListToEdit.getBoardGames().add(boardGame);
        }
    }

    @Override
    @Transactional
    public void removeBoardGameList(Long boardGameListId, Long userId) {
        commandFactory
                .create(BoardGameListCommand.CommandType.REMOVE_BOARD_GAME_LIST, boardGameListId)
                .execute();
    }

    @Override
    public boolean existsBoardGameListByIdAndUserId(Long boardGameListId, Long userId) {
        return (boolean) commandFactory
                .create(BoardGameListCommand.CommandType.CHECK_BOARD_GAME_LIST, boardGameListId, userId)
                .execute();
    }

    @Override
    public void notifyAllObservers(BoardGameList boardGameList) {
        for (NotificationService notificationService: notificationServices) {
            notificationService.update(boardGameList);
        }
    }
}
//Koniec, Tydzień 7, Zasada Otwarty/Zamknięty 3
//Koniec, Tydzień 6, Wzorzec Observer 1