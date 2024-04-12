package pollub.myplanszeo.dto.mapper;

import pollub.myplanszeo.dto.BoardGameListDto;
import pollub.myplanszeo.dto.BoardGameListFactory;
import pollub.myplanszeo.model.BoardGameList;

import java.util.List;

public class BoardGameListMapper {

    public static List<BoardGameListDto> mapToDtos(List<BoardGameList> boardGameLists, BoardGameListFactory.BoardGameListType type) {
        return boardGameLists.stream()
                .map(boardGameList -> mapToDto(boardGameList, type))
                .toList();
    }

    public static BoardGameListDto mapToDto(BoardGameList boardGameList, BoardGameListFactory.BoardGameListType type) {
        return BoardGameListFactory.getBoardGameList(boardGameList, type);
    }
}
