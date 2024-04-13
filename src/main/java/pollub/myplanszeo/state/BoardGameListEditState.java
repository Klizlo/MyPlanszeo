package pollub.myplanszeo.state;

import pollub.myplanszeo.model.BoardGameList;

//Tydzień 6, Wzorzec State 1
//Stan, który informuje że gra jest edytowana
public class BoardGameListEditState implements BoardGameListState{

    private final static BoardGameListEditState INSTANCE = new BoardGameListEditState();

    private BoardGameListEditState(){}

    public static BoardGameListEditState instance(){
        return INSTANCE;
    }

    @Override
    public void updateState(BoardGameList boardGameList) {
        boardGameList.setState(BoardGameListActiveState.instance());
    }

    @Override
    public String getStateName() {
        return "Edit";
    }
}
//Koniec, Tydzień 6, Wzorzec State 1