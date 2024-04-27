package pollub.myplanszeo.dto.mapper;

import pollub.myplanszeo.dto.boardgame.BoardGameDto;
import pollub.myplanszeo.dto.boardgamelist.BoardGameListDto;
import pollub.myplanszeo.dto.boardgamelist.BoardGameListFactory;
import pollub.myplanszeo.dto.category.CategoryDto;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.Category;

import java.util.List;

public interface Mapper {

    List<BoardGameDto> mapToBoardGameDtos(List<BoardGame> boardGames);
    BoardGameDto mapToBoardGameDto(BoardGame boardGame);

    List<BoardGameListDto> mapToBoardGameListDtos(List<BoardGame> boardGames, BoardGameListFactory.BoardGameListType type);
    BoardGameListDto mapToBoardGameListDto(BoardGame boardGame, BoardGameListFactory.BoardGameListType type);

    List<CategoryDto> mapToCategoryDtos(List<Category> categories);
    CategoryDto mapToCategoryDto(Category category);

}
