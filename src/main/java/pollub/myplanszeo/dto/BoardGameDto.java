package pollub.myplanszeo.dto;

import lombok.Getter;
import pollub.myplanszeo.model.AgeRestriction;
import pollub.myplanszeo.model.Category;

// Tydzien 2, Wzorzec Builder 1
// Wzorzec Builder służy do łatwiejszego tworzenia obiektów, ponieważ eliminuje potrzebę zapamiętywania,
// na którym miejscu w konstruktorzse znajduje się dany argument
@Getter
public class BoardGameDto {

    private Long id;
    private String name;
    private AgeRestriction ageRestriction;
    private String description;
    private String producer;
    private Integer minNumOfPlayers;
    private Integer maxNumOfPlayers;
    private CategoryDto category;

    public BoardGameDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.ageRestriction = builder.ageRestriction;
        this.description = builder.description;
        this.producer = builder.producer;
        this.minNumOfPlayers = builder.minNumOfPlayers;
        this.maxNumOfPlayers = builder.maxNumOfPlayers;
        this.category = builder.category;
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

        public BoardGameDto build() {
            return new BoardGameDto(this);
        }
    }
}
// Koniec, Tydzień 2, Wzorzec Builder 1
