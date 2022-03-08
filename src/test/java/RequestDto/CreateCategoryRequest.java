package RequestDto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class CreateCategoryRequest {

    private Category category;

    public CreateCategoryRequest(Category category) {
        this.category = category;
    }
    public CreateCategoryRequest(){

    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

    public static class Category {

        private String name;
        private Integer sportId;

        public Category(String name, Integer sportId) {
            this.name = name;
            this.sportId = sportId;
        }
    public Category(){

    }

        public String getName() {
            return name;
        }

        public Integer getSportId() {
            return sportId;
        }
    }


}
