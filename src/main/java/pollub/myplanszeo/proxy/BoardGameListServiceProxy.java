package pollub.myplanszeo.proxy;

import pollub.myplanszeo.dto.boardgamelist.FullBoardGameListDto;
import pollub.myplanszeo.exception.UnauthorizedException;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.service.boardgamelist.BoardGameListService;

import java.util.ArrayList;
import java.util.List;

//Tydzień 4, Wzorzec Proxy 1
//Klasa ta ma za zadanie kontrolowania, czy upoważniony do tego użytkonik może przeglądać,
//usuwać i manipulować odpowiednie listy gier planszowych
public class BoardGameListServiceProxy implements BoardGameListService {

    private final BoardGameListService boardGameListService;

    public BoardGameListServiceProxy(BoardGameListService boardGameListService) {
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
    public BoardGameList addBoardGameList(BoardGameList boardGameList, Long userId) {
        return boardGameListService.addBoardGameList(boardGameList, userId);
    }

    @Override
    public List<BoardGameList> modifyBoardGameInBoardGameLists(Long gameId, List<Long> selectedLists, Long userId) {
        if (selectedLists == null) {
            selectedLists = new ArrayList<Long>();
        }
        return boardGameListService.modifyBoardGameInBoardGameLists(gameId, selectedLists, userId);
    }

    @Override
    public BoardGameList editBoardGameList(BoardGameList boardGameListToEdit, FullBoardGameListDto boardGameList) {
        return boardGameListService.editBoardGameList(boardGameListToEdit, boardGameList);
    }

    @Override
    public void removeBoardGameList(Long boardGameListId, Long userId) {
        if (!existsBoardGameListByIdAndUserId(boardGameListId, userId)){
            throw new UnauthorizedException();
        }
        boardGameListService.removeBoardGameList(boardGameListId, userId);
    }

    @Override
    public void changeBoardGameListState(Long boardGameListId, Long userId) {
        if (existsBoardGameListByIdAndUserId(boardGameListId, userId)){
            boardGameListService.changeBoardGameListState(boardGameListId, userId);
        }
    }

    @Override
    public boolean existsBoardGameListByIdAndUserId(Long boardGameListId, Long userId) {
        return boardGameListService.existsBoardGameListByIdAndUserId(boardGameListId, userId);
    }
}
//Koniec, Tydzień 4, Wzorzec Proxy