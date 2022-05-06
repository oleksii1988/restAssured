package responsDto.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import responsDto.tournament.CreateTournamentResponse;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SearchEventResponse {

    private ArrayList<DataType> data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class DataType{

        private Integer mappedId;
        private String externalId;
        private String name;
        private Long id;
        private String provider;
        private String status;
        private Boolean outright;
        private Long startDate;
        private Tournament tournament;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class Tournament{
        private Integer mappedId;
        private String name;
        private CreateTournamentResponse.Category category;

    }
}
