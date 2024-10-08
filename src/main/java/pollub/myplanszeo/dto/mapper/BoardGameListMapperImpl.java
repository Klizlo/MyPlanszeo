package pollub.myplanszeo.dto.mapper;

import pollub.myplanszeo.dto.boardgamelist.BoardGameListDto;
import pollub.myplanszeo.dto.boardgamelist.BoardGameListFactory;
import pollub.myplanszeo.model.BoardGameList;

import java.util.List;

public class BoardGameListMapperImpl {

    public static List<BoardGameListDto> mapToDtos(List<BoardGameList> boardGameLists, BoardGameListFactory.BoardGameListType type) {
        return boardGameLists.stream()
                .map(boardGameList -> mapToDto(boardGameList, type))
                .toList();
    }

    public static BoardGameListDto mapToDto(BoardGameList boardGameList, BoardGameListFactory.BoardGameListType type) {
        return BoardGameListFactory.getBoardGameList(boardGameList, type);
    }
}
