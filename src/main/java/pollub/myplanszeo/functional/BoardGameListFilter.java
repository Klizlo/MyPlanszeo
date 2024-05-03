package pollub.myplanszeo.functional;

import pollub.myplanszeo.model.BoardGameList;

//Tydzień 10, programowanie funkcyjne 2
//Interfejs funkcyjny, który pełni rolę filtru dla list gier planszowych
@FunctionalInterface
public interface BoardGameListFilter {
    boolean filter(BoardGameList boardGameList);
}
//Koniec, Tydzień 10, programowanie funkcyjne 2