package requestDto.category;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class SearchRequest {

    private Filter filter;


    @Data
    @ToString
    @AllArgsConstructor
    @EqualsAndHashCode
    @NoArgsConstructor
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

    public static class Filter {

        private String name;
        private Integer sportId;
        private Integer categoryId;

    }

public interface SearchRequestBuilder {

    SearchRequestBuilder setName(String name);
    SearchRequestBuilder setSportId (Integer sportId);
    SearchRequestBuilder setCategoryId (Integer categoryId);
    SearchRequest build();

    }

public static class SearchRequestBuilderImpl implements SearchRequestBuilder{

        Filter filter = new Filter();
    @Override
    public SearchRequestBuilder setName(String name) {
        filter.name = name;
        return this;
    }

    @Override
    public SearchRequestBuilder setSportId(Integer sportId) {
        filter.sportId = sportId;
        return this;
    }

    @Override
    public SearchRequestBuilder setCategoryId(Integer categoryId) {
        filter.categoryId = categoryId;
        return this;
    }

    @Override
    public SearchRequest build() {
        return new SearchRequest(filter);
    }
}



}





