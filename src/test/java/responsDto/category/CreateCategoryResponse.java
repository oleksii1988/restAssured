package responsDto.category;

import com.atlas.common.enums.Language;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import responsDto.tournament.CreateTournamentResponse;

import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class CreateCategoryResponse {

    private Integer mappedId;
    private Integer regionId;
    private CreateTournamentResponse.Sport sport;
    private String name;
    private Map<Language, String> translations;


}
