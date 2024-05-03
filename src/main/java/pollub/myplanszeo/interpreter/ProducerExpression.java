package pollub.myplanszeo.interpreter;

import lombok.RequiredArgsConstructor;
import pollub.myplanszeo.functional.BoardGameFilter;
import pollub.myplanszeo.model.BoardGame;

import java.util.List;

//Tydzień 1, Wzorzec Interpreter 1
//Klasa implementująca interfejs BoardGameExpression
//Klasa filtruje gry na podstawie producenta gier
@RequiredArgsConstructor
public class ProducerExpression implements BoardGameExpression{

    private final BoardGameFilter filter;

    //Tydzień 10, programowanie funkcyjne 1
    //Wykorzystanie filtru do filtracji danych w strumieniu danych
    @Override
    public List<BoardGame> interpret(List<BoardGame> boardGames) {
        return boardGames.stream()
                .filter(filter::filter)
                .toList();
    }
    //Koniec, Tydzień 10, programowanie funkcyjne 1
}
//Koniec, Tydzień 1, Wzorzec Interpreter 1