package pollub.myplanszeo.dto.mapper;

import pollub.myplanszeo.dto.boardgame.BoardGameDto;
import pollub.myplanszeo.dto.boardgame.SimpleBoardGameDto;
import pollub.myplanszeo.model.BoardGame;

import java.util.List;

//Tydzień 6, Wzorzec Strategy 1
//Klasa implementuje interfejs BoardGameMapper i ma za zadanie zmapować obiekty klasy
//BoardGame na obiekty klasy BoardGameDto
public class SimpleBoardGameMapper implements BoardGameMapper{
    @Override
    public List<BoardGameDto> mapToDtos(List<BoardGame> boardGames) {
        return boardGames.stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public BoardGameDto mapToDto(BoardGame boardGame) {
        return new SimpleBoardGameDto
                .Builder(boardGame.getId(), boardGame.getName(), boardGame.getProducer(), boardGame.getCategory())
                .setDescription(boardGame.getDescription())
                .setAgeRestriction(boardGame.getAgeRestriction())
                .setMinNumOfPlayers(boardGame.getMinNumOfPlayers())
                .setMaxNumOfPlayers(boardGame.getMaxNumOfPlayers())
                .build();
    }
}
//Koniec, Tydzień 6, Wzorzec Strategy 1