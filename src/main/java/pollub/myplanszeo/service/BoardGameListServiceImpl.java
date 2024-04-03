package pollub.myplanszeo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pollub.myplanszeo.command.boardgamelist.BoardGameListCommand;
import pollub.myplanszeo.command.boardgamelist.BoardGameListCommandFactory;
import pollub.myplanszeo.model.BoardGameList;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardGameListServiceImpl implements BoardGameListService{

    private final BoardGameListCommandFactory commandFactory;

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
    public BoardGameList addBoardGameList(BoardGameList boardGameList) {
        return (BoardGameList) commandFactory
                .create(BoardGameListCommand.CommandType.ADD_BOARD_GAME_LIST, boardGameList)
                .execute();
    }

    @Override
    public boolean existsBoardGameListByIdAndUserId(Long boardGameListId, Long userId) {
        return (boolean) commandFactory
                .create(BoardGameListCommand.CommandType.CHECK_BOARD_GAME_LIST, boardGameListId, userId)
                .execute();
    }
}
