package pollub.myplanszeo.visitor;

import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.BoardGameList;

//Tydzień 6, Wzorzec Visitor 1
//Iterfejs Visitor, który odwiedza listy gier planszowych i same gry planszowe
public interface Visitor {

    long visit(BoardGame boardGame);
    long visit(BoardGameList boardGameList);

}
//Koniec, Tydzień 6, Wzorzec Visitor 1