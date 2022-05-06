package requestDto.category;

import com.atlas.common.enums.Language;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import java.util.Map;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class UpdateCategoryRequest {

    private Category category;


    @Data
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor

    public static class Category {

        private String name;
        private Integer regionId;
        private Map<Language, String> translations;
    }


    public interface CategoryBuilder{

        CategoryBuilder setName(String name);
        CategoryBuilder setRegionId(Integer regionId);
        CategoryBuilder setTranslations(Map<Language, String> translations);
        UpdateCategoryRequest build();

    }

    public static class CategoryBuilderImpl implements CategoryBuilder{
        Category category = new Category();


        @Override
        public CategoryBuilder setName(String name) {
            category.name = name;
            return this;
        }

        @Override
        public CategoryBuilder setRegionId(Integer regionId) {
            category.regionId = regionId;
            return this;
        }

        @Override
        public CategoryBuilder setTranslations(Map<Language, String> translations) {
            category.translations = translations;
            return this;
        }

        @Override
        public UpdateCategoryRequest build() {
            return new UpdateCategoryRequest(category);
        }
    }



}
