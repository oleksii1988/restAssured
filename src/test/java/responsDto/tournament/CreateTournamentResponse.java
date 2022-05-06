package responsDto.tournament;

import com.atlas.common.enums.Language;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTournamentResponse {

    private Integer mappedId;
    private String name;
    private Map<Language, String> translations;
    private Category category;
    private SportFormBoDto sportFormBoDto;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public static class Category {

    private Integer mappedId;
    private Integer regionId;
    private Sport sport;
    private String name;
    private Map<Language, String> translations;


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
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class SportFormBoDto {

        private Integer id;
        private String name;
        private Integer sportId;
        private List<Periods> periods;
    }



    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @AllArgsConstructor
    public static class Periods {

    private Integer periodId;
    private Integer order;
    private Integer length;

    }


}
