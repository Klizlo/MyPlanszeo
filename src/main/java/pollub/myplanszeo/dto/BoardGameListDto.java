package pollub.myplanszeo.dto;

import lombok.Getter;

@Getter
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
