package responsDto.category;
import com.atlas.common.enums.Language;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dto.ProviderMappings;
import lombok.*;
import responsDto.tournament.CreateTournamentResponse;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class UnmapCategoryResponse {

    private Integer mappedId;
    private Integer regionId;
    private CreateTournamentResponse.Sport sport;
    private String name;
    private Map<Language, String> translations;
    private List<ProviderMappings> providerMappings;


}
