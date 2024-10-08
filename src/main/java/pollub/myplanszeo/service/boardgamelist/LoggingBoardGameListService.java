package pollub.myplanszeo.service.boardgamelist;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pollub.myplanszeo.command.boardgamelist.BoardGameListCommand;
import pollub.myplanszeo.command.boardgamelist.BoardGameListCommandFactory;
import pollub.myplanszeo.dto.boardgamelist.FullBoardGameListDto;
import pollub.myplanszeo.flyweight.AbstractBoardGameCache;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.model.User;
import pollub.myplanszeo.observer.Observable;
import pollub.myplanszeo.service.notification.NotificationService;
import pollub.myplanszeo.service.user.UserService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class LoggingBoardGameListService implements BoardGameListService, Observable {

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
        log.warn("Get all board games list by user id: {}", userId);
        return (List<BoardGameList>) commandFactory
                .create(BoardGameListCommand.CommandType.FIND_BOARD_GAME_LISTS, userId)
                .execute();
    }

    @Override
    public BoardGameList getBoardGameListByIdAndUserId(Long boardGameListId, Long userId) {
        log.warn("Get board games list by board game list id: {} and user id: {}", boardGameListId, userId);
        return (BoardGameList) commandFactory
                .create(BoardGameListCommand.CommandType.FIND_BOARD_GAME_LIST, boardGameListId)
                .execute();
    }

    @Override
    @Transactional
    public BoardGameList addBoardGameList(BoardGameList boardGameList, Long userId) {

        log.warn("Add board games list: {}", boardGameList.getName());

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

        log.warn("Modify board games lists: {}", selectedLists.stream().map(id -> Long.toString(id)).collect(Collectors.joining(", ")));

        List<BoardGameList> gameLists = getAllBoardGameListByUserId(userId);
        BoardGame boardGame = boardGameCache.getBoardGameById(gameId);

        List<BoardGameList> boardGameListsToAddGame = gameLists.stream()
                .filter(boardGameList -> selectedLists.contains(boardGameList.getId())
                        && !boardGameList.getBoardGames().contains(boardGame))
                .toList();

        List<BoardGameList> boardGameListsToRemoveGame = gameLists.stream()
                .filter(boardGameList -> boardGameList.getBoardGames().contains(boardGame))
                .toList();

        List<BoardGameList> boardGameListsAfterAddGame = (List<BoardGameList>) commandFactory
                .create(BoardGameListCommand.CommandType.ADD_BOARD_GAME_TO_LISTS, boardGameListsToAddGame, boardGame)
                .execute();
        List<BoardGameList> boardGameListsAfterRemoveGame = (List<BoardGameList>) commandFactory.create(BoardGameListCommand.CommandType.REMOVE_BOARD_GAME_FROM_LISTS, boardGameListsToRemoveGame, boardGame)
                .execute();

        return Stream.concat(boardGameListsAfterAddGame.stream(), boardGameListsAfterRemoveGame.stream())
                .toList();
    }

    @Override
    @Transactional
    public BoardGameList editBoardGameList(BoardGameList boardGameListToEdit, FullBoardGameListDto boardGameList) {
        log.warn("Editing board game list: {}", boardGameListToEdit.getId());

        if (boardGameList.getBoardGames() != null && boardGameList.getBoardGames().size() > boardGameListToEdit.getBoardGames().size()) {
            List<BoardGame> boardGames = boardGameList.getBoardGames().stream()
                    .filter(boardGame -> !boardGameListToEdit.getBoardGames().contains(boardGame))
                    .map(boardGame -> boardGameCache.getBoardGameById(boardGame.getId()))
                    .toList();
            for (BoardGame boardGame: boardGames) {
                boardGame.getBoardGameLists().add(boardGameListToEdit);
                boardGameListToEdit.getBoardGames().add(boardGame);
            }
        } else if(boardGameList.getBoardGames() != null && boardGameList.getBoardGames().size() < boardGameListToEdit.getBoardGames().size()) {
            List<BoardGame> boardGames = boardGameListToEdit.getBoardGames().stream()
                    .filter(boardGame -> boardGameList.getBoardGames().stream().map(BoardGame::getId).noneMatch(id -> boardGame.getId().equals(id)))
                    .toList();
            for (BoardGame boardGame: boardGames) {
                boardGame.getBoardGameLists().remove(boardGameListToEdit);
                boardGameListToEdit.getBoardGames().remove(boardGame);
            }
        }
        BoardGameList editedBoarGameList = (BoardGameList) commandFactory
                .create(BoardGameListCommand.CommandType.EDIT_BOARD_GAME_LIST, boardGameListToEdit, boardGameList)
                .execute();
        notifyAllObservers(editedBoarGameList);
        return editedBoarGameList;
    }

    @Override
    @Transactional
    public void removeBoardGameList(Long boardGameListId, Long userId) {
        log.warn("Removing board game list: {}", boardGameListId);
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
