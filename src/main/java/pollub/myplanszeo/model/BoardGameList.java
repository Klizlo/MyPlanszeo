package pollub.myplanszeo.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "board_game_list")
@Getter
public class BoardGameList {

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
}
