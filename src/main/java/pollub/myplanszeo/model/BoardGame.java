package pollub.myplanszeo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "board_game")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "age_restriction")
    private AgeRestriction ageRestriction;
    private String description;
    private String producer;
    @Column(name = "min_num_of_players")
    private Integer minNumOfPlayers;
    @Column(name = "max_num_of_players")
    private Integer maxNumOfPlayers;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "boardGames")
    private Set<BoardGameList> boardGameLists = new HashSet<>();
}
