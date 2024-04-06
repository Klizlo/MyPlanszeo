package pollub.myplanszeo.interpreter;

import lombok.RequiredArgsConstructor;
import pollub.myplanszeo.model.BoardGame;

import java.util.List;

//Tydzień 1, Wzorzec Interpreter 1
//Klasa implementująca interfejs BoardGameExpression
//Klasa filtruje gry na podstawie producenta gier
@RequiredArgsConstructor
public class ProducerExpression implements BoardGameExpression{

    private final String producer;

    @Override
    public List<BoardGame> interpret(List<BoardGame> boardGames) {
        return boardGames.stream()
                .filter(boardGame -> boardGame.getProducer().equals(producer))
                .toList();
    }
}
//Koniec, Tydzień 1, Wzorzec Interpreter 1