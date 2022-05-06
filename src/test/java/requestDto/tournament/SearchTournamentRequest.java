package requestDto.tournament;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SearchTournamentRequest {


    private Filter filter;

    @Data
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
public static class Filter{

    private Integer categoryId;
    private Integer sportId;
    private String name;
}

public interface FilterBuilder{

    FilterBuilder setCategoryId(Integer categoryId);
    FilterBuilder setSportId(Integer sportId);
    FilterBuilder setName(String name);
    SearchTournamentRequest build();
}

public static class FilterBuilderImpl implements FilterBuilder{

        Filter filter = new Filter();



    @Override
    public FilterBuilder setCategoryId(Integer categoryId) {
        filter.categoryId = categoryId;
        return this;
    }

    @Override
    public FilterBuilder setSportId(Integer sportId) {
        filter.sportId = sportId;
        return this;
    }

    @Override
    public FilterBuilder setName(String name) {
        filter.name = name;
        return this;
    }

    @Override
    public SearchTournamentRequest build() {
        return new SearchTournamentRequest(filter);
    }

}

}
