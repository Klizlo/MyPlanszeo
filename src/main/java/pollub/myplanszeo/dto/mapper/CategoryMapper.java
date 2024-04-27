package pollub.myplanszeo.dto.mapper;

import pollub.myplanszeo.dto.category.CategoryDto;
import pollub.myplanszeo.model.Category;

import java.util.List;

public interface CategoryMapper {

    List<CategoryDto> mapToCategoryDtos(List<Category> categories);
    CategoryDto mapToCategoryDto(Category category);
}
