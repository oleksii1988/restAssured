package requestDto.sport;

import com.atlas.common.enums.Language;
import lombok.*;

import java.util.Map;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class UpdateSportRequest {


    private Sport sport;


    @Data
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Sport {

        private String name;
        private Map<Language, String> translations;

    }


    public interface SportRequestBuilder {

        SportRequestBuilder setName(String name);

        SportRequestBuilder setTranslations(Map<Language, String> translations);

        UpdateSportRequest build();


    }

    public static class SportRequestImpl implements SportRequestBuilder {

        Sport sport = new Sport();


        @Override
        public SportRequestBuilder setName(String name) {
            sport.name = name;
            return this;
        }

        @Override
        public SportRequestBuilder setTranslations(Map<Language, String> translations) {
            sport.translations = translations;
            return this;
        }

        @Override
        public UpdateSportRequest build() {
            return new UpdateSportRequest(sport);
        }
    }


}
