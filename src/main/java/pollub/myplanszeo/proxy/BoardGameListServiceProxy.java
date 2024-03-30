package pollub.myplanszeo.proxy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pollub.myplanszeo.exception.UnauthorizedException;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.service.BoardGameListService;

import java.util.List;

//Tydzień 4, Wzorzec Proxy 1
//Klasa ta ma za zadanie kontrolowania, czy upoważniony do tego użytkonik może przeglądać,
//usuwać i manipulować odpowiednie listy gier planszowych
@Component
@Qualifier("proxy")
public class BoardGameListServiceProxy implements BoardGameListService {

    private final BoardGameListService boardGameListService;

    public BoardGameListServiceProxy(@Qualifier("service")BoardGameListService boardGameListService) {
        this.boardGameListService = boardGameListService;
    }

    @Override
    public List<BoardGameList> getAllBoardGameListByUserId(Long userId) {
        return boardGameListService.getAllBoardGameListByUserId(userId);
    }

    @Override
    public BoardGameList getBoardGameListByIdAndUserId(Long boardGameListId, Long userId) {
        if (!existsBoardGameListByIdAndUserId(boardGameListId, userId)){
            throw new UnauthorizedException();
        }
        return boardGameListService.getBoardGameListByIdAndUserId(boardGameListId, userId);
    }

    @Override
    public BoardGameList addBoardGameList(BoardGameList boardGameList) {
        return null;
    }

    @Override
    public boolean existsBoardGameListByIdAndUserId(Long boardGameListId, Long userId) {
        return boardGameListService.existsBoardGameListByIdAndUserId(boardGameListId, userId);
    }
}
//Koniec, Tydzień 4, Wzorzec Proxy