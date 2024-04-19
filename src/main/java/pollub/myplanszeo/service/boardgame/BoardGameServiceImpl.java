package pollub.myplanszeo.service.boardgame;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pollub.myplanszeo.exception.BoardGameNotFoundException;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.repository.BoardGameRepository;

import java.util.List;

//Tydzień 7, Zasada Otwarty/Zamknięty 1
//Klasa implementująca interfejs BoardGameService
@Service
@RequiredArgsConstructor
public class BoardGameServiceImpl implements BoardGameService {

    private final BoardGameRepository boardGameRepository;

    @Override
    public List<BoardGame> getAllBoardGames() {
        return boardGameRepository.findAllWithCategory();
    }

    @Override
    public BoardGame getBoardGameById(Long id) {
        return boardGameRepository.findByIdWithCategory(id)
                .orElseThrow(BoardGameNotFoundException::new);
    }
}
//Koniec, Tydzień 7, Zasada Otwarty/Zamknięty 1