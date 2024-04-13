package pollub.myplanszeo.dto.category;

import lombok.Getter;

// Tydzien 2, Wzorzec Builder 2
// Wzorzec został utworzony po to aby wygodniej utworzyć obiket klasy CategoryDto
@Getter
public class CategoryDto {

    private Long id;
    private String name;

    private CategoryDto(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
    }
    public static class Builder {
        private Long id;
        private String name;

        public Builder(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public CategoryDto build(){
            return new CategoryDto(this);
        }
    }
}
//Koniec, Tydzien 2, Wzorzec Builder 1
