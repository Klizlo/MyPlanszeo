package pollub.myplanszeo.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import pollub.myplanszeo.dto.boardgamelist.FullBoardGameListDto;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.service.boardgamelist.BoardGameListService;
import pollub.myplanszeo.service.file.FileService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardGameListFacadeImpl implements BoardGameListFacade {

    private final BoardGameListService boardGameListService;
    @Autowired
    @Qualifier("FileService")
//    @Qualifier("LoggingFileService")
    private FileService fileService;

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
    public void removeBoardGameList(Long boardGameListId, Long userId) {
        boardGameListService.removeBoardGameList(boardGameListId, userId);
    }

    @Override
    public byte[] getBoardGameListAsFile(Long boardGameListId, Long userId, FileService.FileType fileType) {
        BoardGameList boardGameList = getBoardGameListByIdAndUserId(boardGameListId, userId);
        return fileService.getBoardGameList(boardGameList, fileType);
    }

    @Override
    public void prepareFileType(FileService.FileType type, HttpHeaders headers, BoardGameList boardGameList) {
        fileService.prepareFileType(type, headers, boardGameList);
    }

    @Override
    public void changeBoardGameListState(Long boardGameListId, Long userId) {
        boardGameListService.changeBoardGameListState(boardGameListId, userId);
    }
}
