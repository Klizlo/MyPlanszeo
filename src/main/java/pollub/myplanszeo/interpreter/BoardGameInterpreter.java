package pollub.myplanszeo.interpreter;

import lombok.AllArgsConstructor;
import pollub.myplanszeo.functional.BoardGameFilter;
import pollub.myplanszeo.model.BoardGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//Tydzień 5, Wzorzec Interpreter 1
//Klasa interpretująca opcjonalne parametry przekazywane wraz z url
//i na jego podstawie dpowiednio filtruje gry planszowe
@AllArgsConstructor
public class BoardGameInterpreter {

    private List<BoardGame> boardGames;

    public List<BoardGame> interpret(String params) {
        return buildExpressionTree(params);
    }

    private List<BoardGame> buildExpressionTree(String params) {
        String[] split = params.split("&");
        List<BoardGameExpression> expressions = new ArrayList<>();
        if (Arrays.stream(split).anyMatch(param -> param.contains("category"))) {
            String category = Arrays.stream(split)
                    .filter(param -> param.contains("category"))
                    .findFirst().orElseGet(() -> "").substring(9);
            expressions.add(
                    new CategoryExpression(boardGame -> boardGame
                            .getCategory()
                            .getName()
                            .equals(category)));
        }
        if (Arrays.stream(split).anyMatch(param -> param.contains("min") || param.contains("max"))) {
            int min = Integer.parseInt(Arrays.stream(split)
                    .filter(param -> param.contains("min"))
                    .findFirst().orElse("min=0").substring(4));
            int max = Integer.parseInt(Arrays.stream(split)
                    .filter(param -> param.contains("max"))
                    .findFirst().orElse("max=0").substring(4));
            expressions.add(new NumberOfPlayersExpression(boardGame -> boardGame.getMinNumOfPlayers() >= min
                    || boardGame.getMaxNumOfPlayers() <= max));
        }
        if (Arrays.stream(split).anyMatch(param -> param.contains("producer"))) {
            String producer = Arrays.stream(split)
                    .filter(param -> param.contains("producer"))
                    .findFirst().orElseGet(() -> "").substring(9);
            expressions.add(new ProducerExpression(boardGame -> boardGame
                    .getProducer()
                    .equals(producer)));
        }
        for (BoardGameExpression expression : expressions) {
            boardGames = expression.interpret(boardGames);
        }
        return boardGames;
    }

}
//Koniec, Tydzień 5, Wzorzec Interpreter 1