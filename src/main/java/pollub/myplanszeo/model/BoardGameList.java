package pollub.myplanszeo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "board_game_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardGameList implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "board_game_lists",
            joinColumns = @JoinColumn(name = "board_game_list_id"),
            inverseJoinColumns = @JoinColumn(name = "board_game_id")
    )
    private Set<BoardGame> boardGames = new HashSet<>();

    // Tydzień 2, Wzorzec Prototype 1
    // Wzorzec pozwala na kopiowanie listy gier
    // Dzięki temu można szybciej utworzyć nową listę gier na bazie już istniejącej
    @Override
    public Object clone() throws CloneNotSupportedException {
        Set<BoardGame> clonedBoardGames = new HashSet<>();
        for(BoardGame boardGame: this.getBoardGames()) {
            boardGame.getBoardGameLists().add(this);
            clonedBoardGames.add(boardGame);
        }
        return new BoardGameList(null, this.name, this.description, clonedBoardGames);
    }
    //Koniec, Tydzień 2, Wzorzec Prototype 1
}
