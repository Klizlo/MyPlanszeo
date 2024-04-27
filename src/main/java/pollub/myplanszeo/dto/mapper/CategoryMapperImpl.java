package pollub.myplanszeo.dto.mapper;

import pollub.myplanszeo.dto.category.CategoryDto;
import pollub.myplanszeo.model.Category;

import java.util.List;

//Tydzień 7, Zasada Pojedynczej Odpowiedzialności 3
//Klasa ma za zadnie zmapować obiekty klasy Category na obiekty klasy CategoryDto
public class CategoryMapperImpl {

    public static List<CategoryDto> mapToDtos(List<Category> categories) {
        return categories.stream()
                .map(CategoryMapperImpl::mapToDto)
                .toList();
    }

    public static CategoryDto mapToDto(Category category) {
        return new CategoryDto
                .Builder(category.getId(), category.getName())
                .build();
    }
}
//Koniec, Tydzień 7, Zasada Pojedynczej Odpowiedzialności 3