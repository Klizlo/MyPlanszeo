package pollub.myplanszeo.dto.boardgame;

import lombok.Getter;
import pollub.myplanszeo.dto.category.CategoryDto;
import pollub.myplanszeo.model.AgeRestriction;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.BoardGameIterator;
import pollub.myplanszeo.model.Category;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Tydzień 3, Wzorzec Composite 1
// Klasa rozszerzająca BoardGameDto, będąca jednocześniem kompozytem
// Opisywać pojedynczą podstawową gre, która może mieć dodatki (liście), które również są grami, przechowuje je w liście

// Tydzien 2, Wzorzec Builder 5
// Wzorzec Builder służy do łatwiejszego tworzenia obiektów, ponieważ eliminuje potrzebę zapamiętywania,
// na którym miejscu w konstruktorzse znajduje się dany argument
@Getter
public class BaseBoardGameDto extends BoardGameDto {

    private List<BoardGameDto> expansions;
    private Integer occurrence;

    private BaseBoardGameDto(Builder builder) {
        super(
                builder.id,
                builder.name,
                builder.ageRestriction,
                builder.description,
                builder.producer,
                builder.minNumOfPlayers,
                builder.maxNumOfPlayers,
                builder.category
        );
        this.expansions = builder.expansions;
        this.occurrence = builder.occurrence;
    }

    public static class Builder{
        private Long id;
        private String name;
        private AgeRestriction ageRestriction;
        private String description;
        private String producer;
        private Integer minNumOfPlayers;
        private Integer maxNumOfPlayers;
        private CategoryDto category;
        private List<BoardGameDto> expansions;
        private Integer occurrence;

        public Builder(Long id, String name, String producer, Category category, List<BoardGame> expansions) {
            this.id = id;
            this.name = name;
            this.producer = producer;
            this.category = new CategoryDto.Builder(category.getId(), category.getName())
                    .build();
            if (expansions != null) {
                this.expansions = new ArrayList<>();
                Iterator<BoardGame> iterator = new BoardGameIterator(expansions);
                while (iterator.hasNext()) {
                    BoardGame expansion = iterator.next();
                    this.expansions.add(
                            new SimpleBoardGameDto.Builder(expansion.getId(), expansion.getName(), expansion.getProducer(), expansion.getCategory())
                            .setAgeRestriction(expansion.getAgeRestriction())
                            .setDescription(expansion.getDescription())
                            .setMinNumOfPlayers(expansion.getMinNumOfPlayers())
                            .setMaxNumOfPlayers(expansion.getMaxNumOfPlayers())
                            .build());
                }
            }
        }

        public BaseBoardGameDto.Builder setAgeRestriction(AgeRestriction ageRestriction) {
            this.ageRestriction = ageRestriction;
            return this;
        }

        public BaseBoardGameDto.Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public BaseBoardGameDto.Builder setMinNumOfPlayers(Integer minNumOfPlayers) {
            this.minNumOfPlayers = minNumOfPlayers;
            return this;
        }

        public BaseBoardGameDto.Builder setMaxNumOfPlayers(Integer maxNumOfPlayers) {
            this.maxNumOfPlayers = maxNumOfPlayers;
            return this;
        }

        public BaseBoardGameDto.Builder setOccurrence(Integer occurrence) {
            this.occurrence = occurrence;
            return this;
        }

        public BaseBoardGameDto build() {
            return new BaseBoardGameDto(this);
        }
    }

}
//Koniec, Tydzien 2, Wzorzec Builder 5
//Tydzień 3, Wzorzec Composite 1