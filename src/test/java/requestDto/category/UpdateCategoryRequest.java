package requestDto.category;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class UpdateCategoryRequest{

    private Category category;

    public UpdateCategoryRequest(Category category) {
        this.category = category;
    }
    public UpdateCategoryRequest(){

    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

    public static class Category {

        private String name;
        private Integer regionId;

        public Category(String name, Integer regionId) {
            this.name = name;
            this.regionId = regionId;
        }
        public Category(){}



        public Category(String name) {
            this.name = name;
        }

        public Category(Integer regionId) {
            this.regionId = regionId;
        }

        public String getName() {
            return name;
        }

        public Integer getRegionId() {
            return regionId;
        }
    }


}
