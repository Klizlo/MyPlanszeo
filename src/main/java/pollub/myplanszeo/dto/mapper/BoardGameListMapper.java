package pollub.myplanszeo.dto.mapper;

import pollub.myplanszeo.dto.boardgamelist.BoardGameListDto;
import pollub.myplanszeo.dto.boardgamelist.BoardGameListFactory;
import pollub.myplanszeo.model.BoardGameList;

import java.util.List;

public interface BoardGameListMapper {

    List<BoardGameListDto> mapToBoardGameListDtos(List<BoardGameList> boardGameList, BoardGameListFactory.BoardGameListType type);
    BoardGameListDto mapToBoardGameListDto(BoardGameList boardGameList, BoardGameListFactory.BoardGameListType type);
}
