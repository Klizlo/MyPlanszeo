package pollub.myplanszeo.dto;

import pollub.myplanszeo.model.BoardGame;

import java.util.Set;

public class FullBoardGameListDto extends BoardGameListDto {

    private Set<BoardGame> boardGames;

    private FullBoardGameListDto(Builder builder) {
        super(builder.id, builder.name, builder.description);
        this.boardGames = builder.boardGames;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private Set<BoardGame> boardGames;

        public Builder(Long id, String name, Set<BoardGame> boardGames) {
            this.id = id;
            this.name = name;
            this.boardGames = boardGames;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public FullBoardGameListDto build() {
            return new FullBoardGameListDto(this);
        }
    }

}
