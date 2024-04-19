package pollub.myplanszeo.service.boardgamelist;

import pollub.myplanszeo.dto.boardgamelist.FullBoardGameListDto;
import pollub.myplanszeo.model.BoardGameList;

import java.util.List;

//Tydzień 7, Zasada Otwarty/Zamknięty 3
//Interfejs dla klas serwisowych do zarządzania listami gier planszowych
public interface BoardGameListService {

    List<BoardGameList> getAllBoardGameListByUserId(Long userId);
    BoardGameList getBoardGameListByIdAndUserId(Long boardGameListId, Long userId);
    BoardGameList addBoardGameList(BoardGameList boardGameList, Long userId);
    List<BoardGameList> modifyBoardGameInBoardGameLists(Long gameId, List<Long> selected, Long userId);
    BoardGameList editBoardGameList(BoardGameList boardGameListToEdit, FullBoardGameListDto boardGameList);
    void removeBoardGameList(Long boardGameListId, Long userId);
    void changeBoardGameListState(Long boardGameListId, Long userId);

    boolean existsBoardGameListByIdAndUserId(Long boardGameListId, Long userId);
}
//Koniec, Tydzień 7, Zasada Otwarty/Zamknięty 3