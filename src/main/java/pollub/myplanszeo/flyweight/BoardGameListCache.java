package pollub.myplanszeo.flyweight;

import lombok.RequiredArgsConstructor;
import pollub.myplanszeo.dto.boardgamelist.FullBoardGameListDto;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.service.boardgamelist.BoardGameListService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Tydzień 4, Wzorzec Flyweight 2
//Klasa ta jest cachem dla listy gier planszowych dostępnym w serwisie
//Pozwala to na zmniejszenie zapytań do bazy danych oraz na skrócenie czasu w jakim klient otrzyma żądanie
@RequiredArgsConstructor
public class BoardGameListCache implements BoardGameListService {

    private final BoardGameListService boardGameListService;
    private final static Map<Long, BoardGameList> boardGameListCache = new HashMap<>();

    @Override
    public List<BoardGameList> getAllBoardGameListByUserId(Long userId) {
        if (boardGameListCache.isEmpty()) {
            List<BoardGameList> gameLists = boardGameListService.getAllBoardGameListByUserId(userId);
            for (BoardGameList gameList : gameLists) {
                boardGameListCache.put(gameList.getId(), gameList);
            }
        }
        return boardGameListCache.values().stream().toList();
    }

    @Override
    public BoardGameList getBoardGameListByIdAndUserId(Long boardGameListId, Long userId) {
        BoardGameList gameList = boardGameListCache.get(boardGameListId);
        if (gameList == null) {
            gameList = boardGameListService.getBoardGameListByIdAndUserId(boardGameListId, userId);
            boardGameListCache.put(gameList.getId(), gameList);
        }
        return gameList;
    }

    @Override
    public BoardGameList addBoardGameList(BoardGameList boardGameList, Long userId) {
        BoardGameList addedBoardGameList = boardGameListService.addBoardGameList(boardGameList, userId);
        boardGameListCache.put(addedBoardGameList.getId(), addedBoardGameList);
        return addedBoardGameList;
    }

    @Override
    public List<BoardGameList> modifyBoardGameInBoardGameLists(Long gameId, List<Long> selectedLists, Long userId) {
        List<BoardGameList> boardGameLists = boardGameListService.modifyBoardGameInBoardGameLists(gameId, selectedLists, userId);
        for (BoardGameList boardGameList : boardGameLists) {
            boardGameListCache.put(boardGameList.getId(), boardGameList);
        }
        return boardGameLists;
    }

    @Override
    public BoardGameList editBoardGameList(BoardGameList boardGameListToEdit, FullBoardGameListDto boardGameList) {
        BoardGameList boardGameListAfterEdit = boardGameListService.editBoardGameList(boardGameListToEdit, boardGameList);
        boardGameListCache.put(boardGameListAfterEdit.getId(), boardGameListAfterEdit);
        boardGameListAfterEdit.update();
        return boardGameListAfterEdit;
    }

    @Override
    public void removeBoardGameList(Long boardGameListId, Long userId) {
        boardGameListService.removeBoardGameList(boardGameListId, userId);
        boardGameListCache.remove(boardGameListId);
    }

    @Override
    public void changeBoardGameListState(Long boardGameListId, Long userId) {
        BoardGameList boardGameList = boardGameListCache.get(boardGameListId);
        boardGameList.update();
    }

    @Override
    public boolean existsBoardGameListByIdAndUserId(Long boardGameListId, Long userId) {
        return boardGameListService.existsBoardGameListByIdAndUserId(boardGameListId, userId);
    }
}
//Koniec, Tydzień 4, Wzorzec Flyweight 2
