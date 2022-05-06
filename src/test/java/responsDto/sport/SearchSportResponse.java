package responsDto.sport;

import com.atlas.common.enums.Language;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import responsDto.category.SearchCategoryResponse;

import java.util.ArrayList;
import java.util.Map;



@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class SearchSportResponse{

    private ArrayList<DataType> data;



    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Data
    @EqualsAndHashCode
public static class DataType{

      private String name;
      private Integer mappedId;
      private Map<Language, String> translations;

    }


}
