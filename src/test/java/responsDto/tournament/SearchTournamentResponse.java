package responsDto.tournament;

import com.atlas.common.enums.Language;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.ArrayList;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SearchTournamentResponse {

    private ArrayList<DataType> data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class DataType {
        private Integer mappedId;
        private String name;
        private Map<Language, String> translations;
        private CreateTournamentResponse.Category category;
        private CreateTournamentResponse.SportFormBoDto sportFormBoDto;


    }


    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class Sport {

        private Integer mappedId;
        private String name;
        private Map<Language, String> translations;


    }


    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class SportFormBoDto {

        private Integer id;
        private String name;
        private Integer sportId;
        private ArrayList<CreateTournamentResponse.Periods> periods;
    }



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class Periods {

        private Integer periodId;
        private Integer order;
        private Integer length;

    }

}