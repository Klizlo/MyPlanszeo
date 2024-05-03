package pollub.myplanszeo.functional;

import pollub.myplanszeo.model.BoardGame;

//Tydzień 10, programowanie funkcyjne 1
//Interfejs funkcyjny, który pełni rolę filtru dla gier planszowych
@FunctionalInterface
public interface BoardGameFilter {

    boolean filter(BoardGame game);
}
//Koniec, Tydzień 10, programowanie funkcyjne 1