package pollub.myplanszeo.service.boardgame;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pollub.myplanszeo.exception.BoardGameNotFoundException;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.repository.BoardGameRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Qualifier("LoggingBoardGameService")
public class LoggingBoardGameService implements BoardGameService {

    private final BoardGameRepository boardGameRepository;

    @Override
    public List<BoardGame> getAllBoardGames() {
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("Getting all board games {}", localDateTime);
        return boardGameRepository.findAllWithCategory();
    }

    @Override
    public BoardGame getBoardGameById(Long id) {
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("Getting board game {} at {}", id, localDateTime);
        return boardGameRepository.findByIdWithCategory(id)
                .orElseThrow(BoardGameNotFoundException::new);
    }
}

