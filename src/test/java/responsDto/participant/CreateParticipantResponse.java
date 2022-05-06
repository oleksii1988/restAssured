package responsDto.participant;
import com.atlas.common.enums.Country;
import com.atlas.common.enums.Language;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class CreateParticipantResponse {

    private Integer mappedId;
    private Country country;
    private String name;
    private String alternativeName;
    private Map<Language, String> translations;


}
