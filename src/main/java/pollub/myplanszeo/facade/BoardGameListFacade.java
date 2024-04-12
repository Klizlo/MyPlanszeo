package pollub.myplanszeo.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.service.FileService;

import java.util.List;

//Tydzień 4, Wzorzec Facade 1
//Intefejs ten służy do połączenia ze sobą działania dwóch serwisów
//1. Do obsługi list gier plaszowych, 2. Do obsługi plików
public interface BoardGameListFacade {

    List<BoardGameList> getAllBoardGameListsByUserId(Long userId);
    BoardGameList getBoardGameListByIdAndUserId(Long boardGameListId, Long userId);
    BoardGameList addBoardGameList(BoardGameList boardGameList, Long userId);
    void modifyBoardGameInBoardGameLists(Long gameId, List<Long> selected, Long id);
    byte[] getBoardGameListAsFile(Long boardGameListId, Long userId, FileService.FileType fileType) throws JsonProcessingException;
}
//Koniec, Tydzień 4, Wzorzec Facade 1
