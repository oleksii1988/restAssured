package requestDto.category;

import com.atlas.common.enums.Language;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequest {

    private Category category;


    @Data
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

    public static class Category {

        private String name;
        private Integer sportId;
        private Map<Language, String> translations;

    }

public interface CreateCategoryBuilder {

    CreateCategoryBuilder setName(String name);
    CreateCategoryBuilder setSportId (Integer sportId);
    CreateCategoryBuilder setTranslations (Map<Language, String> translations);
    CreateCategoryRequest build();


}


public static class CreateTournamentBuilderImpl implements CreateCategoryBuilder{

        Category category = new Category();

        @Override
    public CreateCategoryBuilder setName(String name) {
        category.name = name;
            return this;
    }

    @Override
    public CreateCategoryBuilder setSportId(Integer sportId) {
        category.sportId = sportId;
            return this;
    }

    @Override
    public CreateCategoryBuilder setTranslations(Map<Language, String> translations) {
        category.translations = translations;
            return this;
    }

    @Override
    public CreateCategoryRequest build() {
        return new CreateCategoryRequest(category);
    }
}



}
