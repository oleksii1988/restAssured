package requestDto.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SearchEventRequest {


    private Filter filter;


    @Data
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Filter{
        private Boolean mapped;
        private Integer sportId;
        private Integer categoryId;
        private Integer tournamentId;
        private String provider;
        private String status;
        private String name;
        private Boolean outright;
        private Integer mappedId;
        private Boolean multiProvider;

    }

    public interface EventBuilder {
        EventBuilder setMapped(Boolean mapped);

        EventBuilder setSportId(Integer sportId);

        EventBuilder setCategoryId(Integer categoryId);

        EventBuilder setTournamentId(Integer tournamentId);

        EventBuilder setProvider(String provider);

        EventBuilder setStatus(String status);

        EventBuilder setName(String name);

        EventBuilder setOutright(Boolean outright);

        EventBuilder setMappedId(Integer mappedId);

        EventBuilder setMultiProvider(Boolean multiProvider);

        SearchEventRequest build();

    }


    public static class EventBuilderImpl implements EventBuilder{

        Filter filter = new Filter();
        @Override
        public EventBuilder setMapped(Boolean mapped) {
            filter.mapped = mapped;
            return this;
        }

        @Override
        public EventBuilder setSportId(Integer sportId) {
            filter.sportId = sportId;
            return this;
        }

        @Override
        public EventBuilder setCategoryId(Integer categoryId) {
            filter.categoryId = categoryId;
            return this;
        }

        @Override
        public EventBuilder setTournamentId(Integer tournamentId) {
            filter.tournamentId = tournamentId;
            return this;
        }

        @Override
        public EventBuilder setProvider(String provider) {
            filter.provider = provider;
            return this;
        }

        @Override
        public EventBuilder setStatus(String status) {
            filter.status = status;
            return this;
        }

        @Override
        public EventBuilder setName(String name) {
            filter.name = name;
            return this;
        }

        @Override
        public EventBuilder setOutright(Boolean outright) {
            filter.outright = outright;
            return this;
        }

        @Override
        public EventBuilder setMappedId(Integer mappedId) {
            filter.mappedId = mappedId;
            return this;
        }

        @Override
        public EventBuilder setMultiProvider(Boolean multiProvider) {
            filter.multiProvider = multiProvider;
            return this;
        }

        @Override
        public SearchEventRequest build() {
            return new SearchEventRequest(filter);
        }
    }

}
