package responsDto.category;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)


public class SearchCategoryResponse {

    private ArrayList<Data> data;

    public SearchCategoryResponse(ArrayList<Data> data) {
        this.data = data;
    }

    public SearchCategoryResponse() {
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public SearchCategoryResponse setData(ArrayList<Data> data) {
        this.data = data;
        return this;
    }

    public static class Data {

        private Integer mappedId;
        private Integer regionId;
        private Sport sport;
        private String name;
        private Translations translations;


        public Data(Integer mappedId, Integer regionId, Sport sport, String name, Translations translations) {
            this.mappedId = mappedId;
            this.regionId = regionId;
            this.sport = sport;
            this.name = name;
            this.translations = translations;

        }

        public Data() {
        }

        public Integer getMappedId() {
            return mappedId;
        }

        public Integer getRegionId() {
            return regionId;
        }


        public Sport getSport() {
            return sport;
        }

        public Data setSport(Sport sport) {
            this.sport = sport;
            return this;
        }

        public Translations getTranslations() {
            return translations;
        }

        public Data setTranslations(Translations translations) {
            this.translations = translations;
            return this;
        }

        public String getName() {
            return name;
        }

        public Data setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "mappedId=" + mappedId +
                    ", regionId=" + regionId +
                    ", sport=" + sport +
                    ", name='" + name + '\'' +
                    ", translations=" + translations +
                    '}';
        }
    }

    public static class Sport {
        private Integer mappedId;
        private String name;
        private Translations translations;

        public Sport(Integer mappedId, String name, Translations translations) {
            this.mappedId = mappedId;
            this.name = name;
            this.translations = translations;
        }

        public Sport() {
        }


        public Integer getMappedId() {
            return mappedId;
        }

        public Sport setMappedId(Integer mappedId) {
            this.mappedId = mappedId;
            return this;
        }

        public String getName() {
            return name;
        }

        public Sport setName(String name) {
            this.name = name;
            return this;
        }

        public Translations getTranslations() {
            return translations;
        }

        public Sport setTranslations(Translations translations) {
            this.translations = translations;
            return this;
        }

        @Override
        public String toString() {
            return "Sport{" +
                    "mappedId=" + mappedId +
                    ", name='" + name + '\'' +
                    ", translations=" + translations +
                    '}';
        }
    }
    @JsonIgnoreProperties(ignoreUnknown = true)

    public static class Translations {

        private String name;
        private String code;

        public Translations(String name, String code) {
            this.name = name;
            this.code = code;
        }

        public Translations() {
        }


        public String getName() {
            return name;
        }

        public Translations setName(String name) {
            this.name = name;
            return this;
        }

        public String getCode() {
            return code;
        }

        public Translations setCode(String code) {
            this.code = code;
            return this;
        }

    }

    @Override
    public String toString() {
        return "ResponsGetCategory{" +
                "data=" + data +
                '}';
    }
}
