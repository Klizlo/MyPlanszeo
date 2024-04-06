package pollub.myplanszeo.interpreter;

import lombok.RequiredArgsConstructor;
import pollub.myplanszeo.model.BoardGame;

import java.util.List;

//Tydzień 1, Wzorzec Interpreter 1
//Klasa implementująca interfejs BoardGameExpression
//Klasa filtruje gry na podstawie minimalnej i maksymalnej liczby graczy
@RequiredArgsConstructor
public class NumberOfPlayersExpression implements BoardGameExpression{

    private final int minNumOfPlayers;
    private final int maxNumOfPlayers;

    @Override
    public List<BoardGame> interpret(List<BoardGame> boardGames) {
        return boardGames.stream()
                .filter(boardGame -> boardGame.getMinNumOfPlayers() >= minNumOfPlayers
                        || boardGame.getMaxNumOfPlayers() <= maxNumOfPlayers)
                .toList();
    }
}
//Koniec, Tydzień 5, Wzorzec Interpreter 1