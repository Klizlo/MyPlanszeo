package pollub.myplanszeo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pollub.myplanszeo.model.AgeRestriction;

//Tydzień 3, Wzorzec Composite 1
//Klasa abstrakcyjna, która jest komponentem podstawowym dla wszystkich obiektów w kompozycie
//Zawiera wszytskie niezbędne informacje o grze
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class BoardGameDto {

    private Long id;
    private String name;
    private AgeRestriction ageRestriction;
    private String description;
    private String producer;
    private Integer minNumOfPlayers;
    private Integer maxNumOfPlayers;
    private CategoryDto category;

}
//Koniec, Tydzień 3, Wzorzec Composite 1