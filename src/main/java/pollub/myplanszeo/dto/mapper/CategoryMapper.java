package pollub.myplanszeo.dto.mapper;

import pollub.myplanszeo.dto.CategoryDto;
import pollub.myplanszeo.model.Category;

import java.util.List;

public class CategoryMapper {

    public static List<CategoryDto> mapToDtos(List<Category> categories) {
        return categories.stream()
                .map(CategoryMapper::mapToDto)
                .toList();
    }

    public static CategoryDto mapToDto(Category category) {
        return new CategoryDto
                .Builder(category.getId(), category.getName())
                .build();
    }
}
