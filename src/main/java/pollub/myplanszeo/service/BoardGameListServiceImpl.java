package pollub.myplanszeo.service;

import org.springframework.beans.factory.annotation.Autowired;
import pollub.myplanszeo.command.boardgamelist.BoardGameListCommand;
import pollub.myplanszeo.command.boardgamelist.BoardGameListCommandFactory;
import pollub.myplanszeo.dto.BoardGameDto;
import pollub.myplanszeo.dto.FullBoardGameListDto;
import pollub.myplanszeo.flyweight.BoardGameCache;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.model.User;

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

        List<BoardGameList> boardGameListsToRemoveGame = gameLists.stream()
                .filter(boardGameList -> boardGameList.getBoardGames().contains(boardGame))
                .toList();

        commandFactory
                .create(BoardGameListCommand.CommandType.ADD_BOARD_GAME_TO_LISTS, boardGameListsToAddGame, boardGame)
                .execute();
        commandFactory.create(BoardGameListCommand.CommandType.REMOVE_BOARD_GAME_FROM_LISTS, boardGameListsToRemoveGame, boardGame)
                .execute();
    }

    @Override
    public BoardGameList editBoardGameList(BoardGameList boardGameListToEdit, FullBoardGameListDto boardGameList) {
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
        return (BoardGameList) commandFactory
                .create(BoardGameListCommand.CommandType.EDIT_BOARD_GAME_LIST, boardGameListToEdit, boardGameList)
                .execute();
    }

    @Override
    public boolean existsBoardGameListByIdAndUserId(Long boardGameListId, Long userId) {
        return (boolean) commandFactory
                .create(BoardGameListCommand.CommandType.CHECK_BOARD_GAME_LIST, boardGameListId, userId)
                .execute();
    }
}
