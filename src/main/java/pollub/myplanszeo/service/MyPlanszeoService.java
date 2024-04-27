package pollub.myplanszeo.service;

import org.springframework.http.HttpHeaders;
import pollub.myplanszeo.dto.boardgamelist.FullBoardGameListDto;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.model.User;
import pollub.myplanszeo.service.file.FileService;

import java.util.List;

public interface MyPlanszeoService {

    User getUserById(Long userId);
    User addUser(User user);

    byte[] getBoardGameList(BoardGameList boardGameList, FileService.FileType fileType);
    void prepareFileType(FileService.FileType type, HttpHeaders headers, BoardGameList boardGameList);

    List<BoardGameList> getAllBoardGameListByUserId(Long userId);
    BoardGameList getBoardGameListByIdAndUserId(Long boardGameListId, Long userId);
    BoardGameList addBoardGameList(BoardGameList boardGameList, Long userId);
    List<BoardGameList> modifyBoardGameInBoardGameLists(Long gameId, List<Long> selected, Long userId);
    BoardGameList editBoardGameList(BoardGameList boardGameListToEdit, FullBoardGameListDto boardGameList);
    void removeBoardGameList(Long boardGameListId, Long userId);
    void changeBoardGameListState(Long boardGameListId, Long userId);
    boolean existsBoardGameListByIdAndUserId(Long boardGameListId, Long userId);

    List<BoardGame> getAllBoardGames();
    BoardGame getBoardGameById(Long id);

}
