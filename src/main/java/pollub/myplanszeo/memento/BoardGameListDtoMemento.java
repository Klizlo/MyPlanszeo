package pollub.myplanszeo.memento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pollub.myplanszeo.model.BoardGame;

import java.util.ArrayList;
import java.util.List;

//Tydzień 5, Wzorzec Memento 1
//Klasa ta działa jak migawka, przechowuje stan listy gier przed modyfikacją lub kolejny stan w trakcje edycji
@Getter
@Setter
@NoArgsConstructor
public class BoardGameListDtoMemento {

    private Long id;
    private String name;
    private String description;
    private List<BoardGame> boardGames;

    public BoardGameListDtoMemento(Long id, String name, String description, List<BoardGame> boardGames) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.boardGames = new ArrayList<>();
        for (BoardGame boardGame : boardGames) {
            this.boardGames.add(boardGame);
        }
    }
}
//Koniec, Tydzień 5, Wzorzec Memento 1
