package pollub.myplanszeo.memento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pollub.myplanszeo.model.BoardGame;

import java.util.Set;

//Tydzień 5, Wzorzec Memento 1
//Klasa ta działa jak migawka, przechowuje stan listy gier przed modyfikacją lub kolejny stan w trakcje edycji
@Getter
@AllArgsConstructor
public class BoardGameListDtoMemento {

    private Long id;
    private String name;
    private String description;
    private Set<BoardGame> boardGames;

}
//Koniec, Tydzień 5, Wzorzec Memento 1
