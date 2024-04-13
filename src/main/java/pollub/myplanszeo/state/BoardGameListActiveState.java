package pollub.myplanszeo.state;

import pollub.myplanszeo.model.BoardGameList;
//Tydzień 6, Wzorzec State 1
//Stan, który informuje że lista jest aktywna i można ją wykorzystać
public class BoardGameListActiveState implements BoardGameListState {

    private final static BoardGameListActiveState INSTANCE = new BoardGameListActiveState();

    private BoardGameListActiveState() {}

    public static BoardGameListActiveState instance() {
        return INSTANCE;
    }

    @Override
    public void updateState(BoardGameList boardGameList) {}

    @Override
    public String getStateName() {
        return "Active";
    }
}
//Koniec, Tydzień 6, Wzorzec State 1