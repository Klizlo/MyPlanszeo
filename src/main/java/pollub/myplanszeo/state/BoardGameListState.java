package pollub.myplanszeo.state;

import pollub.myplanszeo.model.BoardGameList;

//Tydzień 6, Wzorzec State 1
//Interfejs, który implementują różne stany listy gier planszowych
public interface BoardGameListState {

    void updateState(BoardGameList boardGameList);
    String getStateName();

}
//Koniec, Tydzień 6, Wzorzec State 1