package pollub.myplanszeo.unit.designPattern.creational;

import org.junit.jupiter.api.Test;
import pollub.myplanszeo.dto.boardgame.SimpleBoardGameDto;
import pollub.myplanszeo.dto.boardgamelist.SimpleBoardGameListDto;
import pollub.myplanszeo.dto.category.CategoryDto;
import pollub.myplanszeo.model.*;
import pollub.myplanszeo.state.BoardGameListActiveState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class BuilderTest {

    @Test
    public void givenCategory_whenUseCategoryDtoBuilder_returnCategoryDto() {
        Category cardGame = new Category(1L, "Card Game", new ArrayList<>());

        CategoryDto categoryDto = new CategoryDto.Builder(cardGame.getId(), cardGame.getName()).build();

        assertInstanceOf(CategoryDto.class,     categoryDto);
        assertEquals(cardGame.getId(),          categoryDto.getId());
        assertEquals(cardGame.getName(),        categoryDto.getName());
    }

    @Test
    public void givenBoardGame_whenUseBoardGameDtoBuilder_returnBoardGameDto() {
        Category cooperative = new Category(2L, "Cooperative", new ArrayList<>());
        BoardGame mansionOfMadness = new BoardGame(2L, "Mansion of Madness", AgeRestriction.PLUS_12, "", "FFG", 1, 5, cooperative, null, null, null);

        SimpleBoardGameDto boardGameDto = new SimpleBoardGameDto.Builder(mansionOfMadness.getId(), mansionOfMadness.getName(), mansionOfMadness.getProducer(), mansionOfMadness.getCategory())
                .setAgeRestriction(mansionOfMadness.getAgeRestriction())
                .setDescription(mansionOfMadness.getDescription())
                .setMinNumOfPlayers(mansionOfMadness.getMinNumOfPlayers())
                .setMaxNumOfPlayers(mansionOfMadness.getMaxNumOfPlayers())
                .build();

        assertInstanceOf(SimpleBoardGameDto.class,                        boardGameDto);
        assertEquals(mansionOfMadness.getId(),                      boardGameDto.getId());
        assertEquals(mansionOfMadness.getName(),                    boardGameDto.getName());
        assertEquals(mansionOfMadness.getProducer(),                boardGameDto.getProducer());
        assertEquals(mansionOfMadness.getDescription(),             boardGameDto.getDescription());
        assertEquals(mansionOfMadness.getAgeRestriction().getAge(), boardGameDto.getAgeRestriction().getAge());
        assertEquals(mansionOfMadness.getMinNumOfPlayers(),         boardGameDto.getMinNumOfPlayers());
        assertEquals(mansionOfMadness.getMaxNumOfPlayers(),         boardGameDto.getMaxNumOfPlayers());
    }

    @Test
    public void givenBoardGameList_whenUseSimpleBoardGameListDto_returnSimpleBoardGameListDto() {
        Category cardGame = new Category(1L, "Card Game", new ArrayList<>());
        Category cooperative = new Category(2L, "Cooperative", new ArrayList<>());
        Set<BoardGame> boardGames = new HashSet<>();
        boardGames.add(new BoardGame(1L, "Munchkin", AgeRestriction.PLUS_7, "", "BlackMonkGames", 2, 6, cardGame, null, null, null));
        boardGames.add(new BoardGame(2L, "Mansion of Madness", AgeRestriction.PLUS_12, "", "FFG", 1, 5, cooperative, null, null, null));

        BoardGameList boardGameList = new BoardGameList(1L, "Favorite", "", BoardGameListActiveState.instance(), boardGames, new User(1L, "adam.nowak@poczta.pl", "AFabcabcbahucyba", null));

        SimpleBoardGameListDto simpleBoardGameList = new SimpleBoardGameListDto.Builder(boardGameList.getId(), boardGameList.getName(), boardGameList.getBoardGames().size())
                .setDescription(boardGameList.getDescription())
                .build();

        assertInstanceOf(SimpleBoardGameListDto.class,      simpleBoardGameList);
        assertEquals(boardGameList.getId(),                 simpleBoardGameList.getId());
        assertEquals(boardGameList.getName(),               simpleBoardGameList.getName());
        assertEquals(boardGameList.getDescription(),        simpleBoardGameList.getDescription());
        assertEquals(boardGameList.getBoardGames().size(),  simpleBoardGameList.getNumberOfGames());

    }
}
