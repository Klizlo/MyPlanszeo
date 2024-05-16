package pollub.myplanszeo.unit.designPattern.behavioral;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pollub.myplanszeo.interpreter.BoardGameInterpreter;
import pollub.myplanszeo.model.AgeRestriction;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.Category;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InterpreterTest {

    private static BoardGameInterpreter interpreter;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        Category cardGame = new Category(1L, "Card Game", new ArrayList<>());
        Category cooperative = new Category(2L, "Cooperative", new ArrayList<>());
        List<BoardGame> boardGames = new ArrayList<>();
        boardGames.add(new BoardGame(1L, "Munchkin", AgeRestriction.PLUS_7, "", "BlackMonkGames", 2, 6, cardGame, null, null, null));
        boardGames.add(new BoardGame(2L, "Mansion of Madness", AgeRestriction.PLUS_12, "", "FFG", 1, 5, cooperative, null, null, null));
        boardGames.add(new BoardGame(3L, "Descent: Legends of the dark", AgeRestriction.PLUS_12, "", "FFG", 1, 4, cooperative, null, null, null));
        interpreter = new BoardGameInterpreter(boardGames);
    }

    @Test
    public void givenEmptyParams_whenInterpret_thenReturnAllBoardGames() {
        String params = "";
        List<BoardGame> boardGames = interpreter.interpret(params);
        assertThat(boardGames.size()).isEqualTo(3);
    }

    @Test
    public void givenProducerParam_whenInterpret_thenReturnBoardGames() {
        String params = "producer=FFG";
        List<BoardGame> boardGames = interpreter.interpret(params);
        assertThat(boardGames.size()).isEqualTo(2);
    }

    @Test
    public void givenNumberOfPlayersParams_whenInterpret_thenReturnBoardGames() {
        String params = "min=2&max=5";
        List<BoardGame> boardGames = interpreter.interpret(params);
        assertThat(boardGames.size()).isEqualTo(2);
    }

    @Test
    public void givenCategoryParam_whenInterpret_thenReturnBoardGames() {
        String params = "category=Cooperative";
        List<BoardGame> boardGames = interpreter.interpret(params);
        assertThat(boardGames.size()).isEqualTo(2);
    }

    @Test
    public void givenDifferentParams_whenInterpret_thenReturnBoardGames() {
        String params = "category=Cooperative&max=4";
        List<BoardGame> boardGames = interpreter.interpret(params);
        assertThat(boardGames.size()).isEqualTo(2);
    }

}
