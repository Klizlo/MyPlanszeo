package pollub.myplanszeo.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public abstract class BoardGameListDto {

    private Long id;
    private String name;
    private String description;

    protected BoardGameListDto(Long id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
