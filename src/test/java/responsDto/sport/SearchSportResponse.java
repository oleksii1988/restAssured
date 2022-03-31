package responsDto.sport;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import responsDto.category.SearchCategoryResponse;

import java.util.ArrayList;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class SearchSportResponse{

    private ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }
    /*private ArrayList <Sorting> sorting;

    public ArrayList<Sorting> getSorting() {
        return sorting;
    }*/

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @lombok.Data
    @EqualsAndHashCode
public static class Data{

      private String name;
      private Integer mappedId;
      private SearchCategoryResponse.Translations translations;

    }

    /*@JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @lombok.Data
    @EqualsAndHashCode

    public static class Sorting{
        private Integer order;
        private String  fieldName;
        private String  direction;
    }*/


}
