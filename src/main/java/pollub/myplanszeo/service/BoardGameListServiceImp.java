package pollub.myplanszeo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.repository.BoardGameListRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardGameListServiceImp implements BoardGameListService{

    private final BoardGameListRepository boardGameListRepository;

    @Override
    public List<BoardGameList> getAllBoardGameListByUserId(Long userId) {
        return boardGameListRepository.findAllByUser_Id(userId);
    }

    @Override
    public BoardGameList getBoardGameListById(Long boardGameListId) {
        return boardGameListRepository.findById(boardGameListId)
                .orElseThrow();
    }

    @Override
    public BoardGameList addBoardGameList(BoardGameList boardGameList) {
        return null;
    }

    @Override
    public boolean existsBoardGameListByIdAndUserId(Long boardGameListId, Long userId) {
        return boardGameListRepository.existsByIdAndUser_Id(boardGameListId, userId);
    }
}
