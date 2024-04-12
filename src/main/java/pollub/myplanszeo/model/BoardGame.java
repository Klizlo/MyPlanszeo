package pollub.myplanszeo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "board_game")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"boardGameLists", "baseGame", "dlcs"})
@EqualsAndHashCode(exclude = {"category", "boardGameLists", "baseGame", "dlcs"})
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

    @JsonIgnore
    @ManyToMany(mappedBy = "boardGames", fetch = FetchType.EAGER)
    private Set<BoardGameList> boardGameLists = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "base_game_id")
    private BoardGame baseGame;

    @JsonIgnore
    @OneToMany(mappedBy = "baseGame", fetch = FetchType.EAGER)
    private List<BoardGame> dlcs;
}
