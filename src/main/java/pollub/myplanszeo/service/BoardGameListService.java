package pollub.myplanszeo.service;

import pollub.myplanszeo.model.BoardGameList;

import java.util.List;

public interface BoardGameListService {

    List<BoardGameList> getAllBoardGameListByUserId(Long userId);
    BoardGameList getBoardGameListByIdAndUserId(Long boardGameListId, Long userId);
    BoardGameList addBoardGameList(BoardGameList boardGameList, Long userId);
    void modifyBoardGameInBoardGameLists(Long gameId, List<Long> selected, Long userId);

    boolean existsBoardGameListByIdAndUserId(Long boardGameListId, Long userId);
}
