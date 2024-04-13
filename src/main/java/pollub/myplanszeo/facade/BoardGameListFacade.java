package pollub.myplanszeo.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import pollub.myplanszeo.dto.FullBoardGameListDto;
import pollub.myplanszeo.model.BoardGame;
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
    BoardGameList editBoardGameList(BoardGameList boardGameListToEdit, FullBoardGameListDto boardGameList);
    void removeBoardGameList(Long boardGameListId, Long userId);


    byte[] getBoardGameListAsFile(Long boardGameListId, Long userId, FileService.FileType fileType);
    void prepareFileType(FileService.FileType type, HttpHeaders headers, BoardGameList boardGameList);
}
//Koniec, Tydzień 4, Wzorzec Facade 1
