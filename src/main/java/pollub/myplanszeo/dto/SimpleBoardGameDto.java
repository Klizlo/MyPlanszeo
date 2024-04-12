package pollub.myplanszeo.dto;

import lombok.*;
import pollub.myplanszeo.model.AgeRestriction;
import pollub.myplanszeo.model.Category;

//Tydzień 3, Wzorzec Composite 1
// Klasa rozszerzająca BoardGameDto, będąca jednocześniem liściem w kompozycie
// Może opisywać pojedynczą gre jak i grę jako dodatek do podstawowej gry

// Tydzien 2, Wzorzec Builder 1
// Wzorzec Builder służy do łatwiejszego tworzenia obiektów, ponieważ eliminuje potrzebę zapamiętywania,
// na którym miejscu w konstruktorzse znajduje się dany argument
@Getter
@Setter
@AllArgsConstructor
public class SimpleBoardGameDto extends BoardGameDto {

    private SimpleBoardGameDto(Builder builder) {
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
    }

    @Override
    public String toString() {
        return "SimpleBoardGameDto{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", ageRestriction=" + getAgeRestriction() +
                ", description='" + getDescription() + '\'' +
                ", producer='" + getProducer() + '\'' +
                ", minNumOfPlayers=" + getMinNumOfPlayers() +
                ", maxNumOfPlayers=" + getMaxNumOfPlayers() +
                '}';
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

        public Builder(Long id, String name, String producer, Category category) {
            this.id = id;
            this.name = name;
            this.producer = producer;
            this.category = new CategoryDto.Builder(category.getId(), category.getName())
                    .build();
        }

        public Builder setAgeRestriction(AgeRestriction ageRestriction) {
            this.ageRestriction = ageRestriction;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setMinNumOfPlayers(Integer minNumOfPlayers) {
            this.minNumOfPlayers = minNumOfPlayers;
            return this;
        }

        public Builder setMaxNumOfPlayers(Integer maxNumOfPlayers) {
            this.maxNumOfPlayers = maxNumOfPlayers;
            return this;
        }

        public SimpleBoardGameDto build() {
            return new SimpleBoardGameDto(this);
        }
    }
}
// Koniec, Tydzień 2, Wzorzec Builder 1

//Koniec, Tydzień 3, Wzorzec Composite 1