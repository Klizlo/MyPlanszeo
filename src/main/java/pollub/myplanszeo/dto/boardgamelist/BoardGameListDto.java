package pollub.myplanszeo.dto.boardgamelist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BoardGameListDto {

    private Long id;
    private String name;
    private String description;
    private String state;

    protected BoardGameListDto(Long id, String name, String description, String state){
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
    }
}
