package requestDto.tournament;
import com.atlas.common.enums.Language;
import lombok.*;

import java.util.Map;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CreateTournamentRequest {

    private Tournament tournament;


    @Data
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor

    public static class Tournament {
        private Integer categoryId;
        private String name;
        private Integer sportFormId;
        private Map<Language, String> translations;

    }


    public interface TournamentBuilder {
        TournamentBuilder setCategoryId(Integer categoryId);
        TournamentBuilder setName(String name);
        TournamentBuilder setSportFormId(Integer SportFormId);
        TournamentBuilder setTranslations(Map<Language, String> translations);
        CreateTournamentRequest build();

    }

    public static class TournamentBuilderImpl implements TournamentBuilder{

        Tournament tournament = new Tournament();


        @Override
        public TournamentBuilder setCategoryId(Integer categoryId) {
            tournament.categoryId = categoryId;
            return this;
        }

        @Override
        public TournamentBuilder setName(String name) {
            tournament.name = name;
            return this;
        }

        @Override
        public TournamentBuilder setSportFormId(Integer sportFormId) {
            tournament.sportFormId = sportFormId;
            return this;
        }

        @Override
        public TournamentBuilder setTranslations(Map<Language, String> translations) {
            tournament.translations = translations;
            return this;
        }

        @Override
        public CreateTournamentRequest build() {
            return new CreateTournamentRequest(tournament);
        }
    }





}
