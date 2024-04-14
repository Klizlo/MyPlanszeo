package pollub.myplanszeo.dto.boardgamelist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pollub.myplanszeo.memento.BoardGameListDtoMemento;
import pollub.myplanszeo.model.BoardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//Tydzień 2, Wzorzec Builder 3
// Wzorzec builder pozwala na wygodniejsze utworzenie obiektu klasy FullBoardGameListDto,
// ponieważ nie trzeba pamiętać wszystkich argumentów, jakie należy podać do konstruktora
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FullBoardGameListDto extends BoardGameListDto {

    private List<BoardGame> boardGames;

    private FullBoardGameListDto(Builder builder) {
        super(builder.id, builder.name, builder.description, builder.state);
        this.boardGames = builder.boardGames;
    }

    public BoardGameListDtoMemento saveToMemento() {
        return new BoardGameListDtoMemento(this.getId(), this.getName(), this.getDescription(), this.boardGames);
    }

    public void undoFromMemento(BoardGameListDtoMemento memento) {
        this.setId(memento.getId());
        this.setName(memento.getName());
        this.setDescription(memento.getDescription());
        this.boardGames = memento.getBoardGames();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private String state;
        private List<BoardGame> boardGames;

        public Builder(Long id, String name, Set<BoardGame> boardGames) {
            this.id = id;
            this.name = name;
            this.boardGames = new ArrayList<>();
            for (BoardGame boardGame : boardGames) {
                this.boardGames.add(boardGame);
            }
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setState(String state) {
            this.state = state;
            return this;
        }

        public FullBoardGameListDto build() {
            return new FullBoardGameListDto(this);
        }
    }

}
//Koniec, Tydzień 2, Wzorzec Builder 3
