package pollub.myplanszeo.command.boardgamelist;

//Tydzień 5, Wzorzec Command 1
//Intefejs dla wszytskich command klas, które będą miały pojedynczą odpowiedzialność
//i będą pośredniczyły pomiędzy serwisem (Invoker) a repozytorium (Receiver)
//np dodanie listy gier planszowych do bazy danych
public interface BoardGameListCommand {

    Object execute();

    enum CommandType {
        FIND_BOARD_GAME_LISTS,
        FIND_BOARD_GAME_LIST,
        ADD_BOARD_GAME_LIST,
        CHECK_BOARD_GAME_LIST
    }

}
//Koniec, Tydzień 5, Wzorzec Command 1