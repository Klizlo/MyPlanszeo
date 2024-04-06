package pollub.myplanszeo.interpreter;

import lombok.RequiredArgsConstructor;
import pollub.myplanszeo.model.BoardGame;

import java.util.List;

//Tydzień 1, Wzorzec Interpreter 1
//Klasa implementująca interfejs BoardGameExpression
//Klasa filtruje gry na podstawie kategorii gry
@RequiredArgsConstructor
public class CategoryExpression implements BoardGameExpression{

    private final String categoryName;

    @Override
    public List<BoardGame> interpret(List<BoardGame> boardGames) {
        return boardGames.stream()
                .filter(boardGame -> boardGame
                        .getCategory()
                        .getName().equals(categoryName))
                .toList();
    }
}
//Koniec, Tydzień 5, Wzorzec Interpreter 1