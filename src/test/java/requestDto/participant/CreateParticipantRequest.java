package requestDto.participant;
import com.atlas.common.enums.Country;
import com.atlas.common.enums.Language;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import java.util.Map;


@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CreateParticipantRequest {

private Participant participant;



    @Data
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public static class Participant {

    private Integer categoryId;
    private String name;
    private String alternativeName;
    private Country country;
    private Map<Language, String> translations;

}

public interface ParticipantBuilder {

    ParticipantBuilder setCategoryId(Integer categoryId);

    ParticipantBuilder setName(String name);

    ParticipantBuilder setAlternativeName(String alternativeName);

    ParticipantBuilder setCountry(Country country);

    ParticipantBuilder setTranslations(Map<Language, String> translations);

    CreateParticipantRequest build();


    }

    public static class ParticipantBuilderImpl implements ParticipantBuilder{

        Participant participant = new Participant();
        @Override
        public ParticipantBuilder setCategoryId(Integer categoryId) {
            participant.categoryId = categoryId;
            return this;
        }

        @Override
        public ParticipantBuilder setName(String name) {
            participant.name = name;
            return this;
        }

        @Override
        public ParticipantBuilder setAlternativeName(String alternativeName) {
            participant.alternativeName = alternativeName;
            return this;
        }

        @Override
        public ParticipantBuilder setCountry(Country country) {
            participant.country = country;
            return this;
        }



        @Override
        public ParticipantBuilder setTranslations(Map<Language, String> translations) {
            participant.translations = translations;
            return this;
        }

        @Override
        public CreateParticipantRequest build() {
            return new CreateParticipantRequest(participant);
        }
    }





}
