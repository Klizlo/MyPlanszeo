package pollub.myplanszeo.interpreter;

import pollub.myplanszeo.model.BoardGame;

import java.util.List;

//Tydzień 5, Wzorzec Interpreter 1
//Interfejs dla wyrażeń
public interface BoardGameExpression {
    List<BoardGame> interpret(List<BoardGame> boardGames);
}
//Koniec, Tydzień 5, Wzorzec Interpreter 1