package pollub.myplanszeo.dto;

import lombok.Getter;

@Getter
public class SimpleBoardGameListDto extends BoardGameListDto {

    private Integer numberOfGames;

    private SimpleBoardGameListDto(Builder builder) {
        super(builder.id, builder.name, builder.description);
        this.numberOfGames = builder.numberOfGames;
    }

    public static class Builder {

        private Long id;
        private String name;
        private String description;
        private Integer numberOfGames;

        public Builder(Long id, String name, Integer numberOfGames) {
            this.id = id;
            this.name = name;
            this.numberOfGames = numberOfGames;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public SimpleBoardGameListDto build() {
            return new SimpleBoardGameListDto(this);
        }

    }

}
