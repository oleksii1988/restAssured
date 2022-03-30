package responsDto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class CreateCategoryResponse {

    private Integer mappedId;
    private Integer regionId;
    private SearchCategoryResponse.Sport sport;
    private String name;
    private SearchCategoryResponse.Translations translations;

    public CreateCategoryResponse(Integer mappedId, Integer regionId, SearchCategoryResponse.Sport sport, String name, SearchCategoryResponse.Translations translations) {
        this.mappedId = mappedId;
        this.regionId = regionId;
        this.sport = sport;
        this.name = name;
        this.translations = translations;
    }

    public CreateCategoryResponse(){

    }

    public Integer getMappedId() {
        return mappedId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public SearchCategoryResponse.Sport getSport() {
        return sport;
    }

    public String getName() {
        return name;
    }

    public SearchCategoryResponse.Translations getTranslations() {
        return translations;
    }

    @Override
    public String toString() {
        return "CreateCategoryResponse{" +
                "mappedId=" + mappedId +
                ", regionId=" + regionId +
                ", sport=" + sport +
                ", name='" + name + '\'' +
                ", translations=" + translations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateCategoryResponse that = (CreateCategoryResponse) o;
        return Objects.equals(mappedId, that.mappedId) && Objects.equals(regionId, that.regionId) && Objects.equals(sport, that.sport) && Objects.equals(name, that.name) && Objects.equals(translations, that.translations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mappedId, regionId, sport, name, translations);
    }
}
