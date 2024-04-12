package pollub.myplanszeo.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pollub.myplanszeo.dto.FullBoardGameListDto;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.service.BoardGameListService;
import pollub.myplanszeo.service.FileService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardGameListFacadeImpl implements BoardGameListFacade {

    private final BoardGameListService boardGameListService;
    private final FileService fileService;

    @Override
    public List<BoardGameList> getAllBoardGameListsByUserId(Long userId) {
        return boardGameListService.getAllBoardGameListByUserId(userId);
    }

    @Override
    public BoardGameList getBoardGameListByIdAndUserId(Long boardGameListId, Long userId) {
        return boardGameListService.getBoardGameListByIdAndUserId(boardGameListId, userId);
    }

    @Override
    public BoardGameList addBoardGameList(BoardGameList boardGameList, Long userId) {
        return boardGameListService.addBoardGameList(boardGameList, userId);
    }

    @Override
    public void modifyBoardGameInBoardGameLists(Long gameId, List<Long> selected, Long userId) {
        boardGameListService.modifyBoardGameInBoardGameLists(gameId, selected, userId);
    }

    @Override
    public BoardGameList editBoardGameList(BoardGameList boardGameListToEdit, FullBoardGameListDto boardGameList) {
        return boardGameListService.editBoardGameList(boardGameListToEdit, boardGameList);
    }

    @Override
    public byte[] getBoardGameListAsFile(Long boardGameListId, Long userId, FileService.FileType fileType) throws JsonProcessingException {
        BoardGameList boardGameList = getBoardGameListByIdAndUserId(boardGameListId, userId);
        return fileService.getBoardGameList(boardGameList, fileType);
    }
}
