package pollub.myplanszeo.dto.mapper;

import pollub.myplanszeo.dto.boardgame.BoardGameDto;
import pollub.myplanszeo.model.BoardGame;

import java.util.List;

//Tydzień 6, Wzorzec Strategy 1
//Interfejs, który pozwala na zbudowanie obiektów dto w zależności od tego jakiej klasy będziemy potrzebowali
public interface BoardGameMapper {

    List<BoardGameDto> mapToDtos(List<BoardGame> boardGames);
    BoardGameDto mapToDto(BoardGame boardGame);

}
//Koniec, Tydzień 6, Wzorzec Strategy 1
