package pollub.myplanszeo.service;

import org.springframework.beans.factory.annotation.Autowired;
import pollub.myplanszeo.command.boardgamelist.BoardGameListCommand;
import pollub.myplanszeo.command.boardgamelist.BoardGameListCommandFactory;
import pollub.myplanszeo.flyweight.BoardGameCache;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.model.User;

import java.util.ArrayList;
import java.util.List;

public class BoardGameListServiceImpl implements BoardGameListService{

    @Autowired
    private BoardGameListCommandFactory commandFactory;
    @Autowired
    private UserService userService;
    @Autowired
    private BoardGameCache boardGameCache;

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
    public BoardGameList addBoardGameList(BoardGameList boardGameList, Long userId) {
        User user = userService.getUserById(userId);
        boardGameList.setUser(user);
        user.getBoardGameLists().add(boardGameList);

        return (BoardGameList) commandFactory
                .create(BoardGameListCommand.CommandType.ADD_BOARD_GAME_LIST, boardGameList)
                .execute();
    }

    @Override
    public void modifyBoardGameInBoardGameLists(Long gameId, List<Long> selected, Long userId) {
        List<BoardGameList> gameLists = getAllBoardGameListByUserId(userId);
        BoardGame boardGame = boardGameCache.getBoardGameById(gameId);

        List<BoardGameList> boardGameListsToAddGame = gameLists.stream()
                .filter(boardGameList -> selected.contains(boardGameList.getId())
                        && !boardGameList.getBoardGames().contains(boardGame))
                .toList();
        System.out.println(boardGameListsToAddGame.size());
        gameLists.forEach(boardGameList -> System.out.println(boardGameList.getBoardGames().size()));

        List<BoardGameList> boardGameListsToRemoveGame = gameLists.stream()
                .filter(boardGameList -> boardGameList.getBoardGames().contains(boardGame))
                .toList();
        System.out.println(boardGameListsToRemoveGame.size());
        commandFactory
                .create(BoardGameListCommand.CommandType.ADD_BOARD_GAME_TO_LISTS, boardGameListsToAddGame, boardGame)
                .execute();
        commandFactory.create(BoardGameListCommand.CommandType.REMOVE_BOARD_GAME_FROM_LISTS, boardGameListsToRemoveGame, boardGame)
                .execute();
    }

    @Override
    public boolean existsBoardGameListByIdAndUserId(Long boardGameListId, Long userId) {
        return (boolean) commandFactory
                .create(BoardGameListCommand.CommandType.CHECK_BOARD_GAME_LIST, boardGameListId, userId)
                .execute();
    }
}
