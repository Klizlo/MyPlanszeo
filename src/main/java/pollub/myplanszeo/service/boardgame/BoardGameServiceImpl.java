package pollub.myplanszeo.service.boardgame;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pollub.myplanszeo.exception.BoardGameNotFoundException;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.repository.BoardGameRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardGameServiceImpl implements BoardGameService {

    private final BoardGameRepository boardGameRepository;

    @Override
    public List<BoardGame> getAllBoardGames() {
        return boardGameRepository.findAll();
    }

    @Override
    public BoardGame getBoardGameById(Long id) {
        return boardGameRepository.findById(id)
                .orElseThrow(BoardGameNotFoundException::new);
    }
}
