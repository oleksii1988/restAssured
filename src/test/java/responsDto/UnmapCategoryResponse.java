package responsDto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dto.ProviderMappings;


import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class UnmapCategoryResponse {

    private Integer mappedId;
    private Integer regionId;
    private SearchCategoryResponse.Sport sport;
    private String name;
    private SearchCategoryResponse.Translations translations;
    private List<ProviderMappings> providerMappings;

    public UnmapCategoryResponse(Integer mappedId, Integer regionId, SearchCategoryResponse.Sport sport, String name, SearchCategoryResponse.Translations translations, List<ProviderMappings> providerMappings) {
        this.mappedId = mappedId;
        this.regionId = regionId;
        this.sport = sport;
        this.name = name;
        this.translations = translations;
        this.providerMappings = providerMappings;
    }

    public UnmapCategoryResponse(){

    }

    public Integer getMappedId() {
        return mappedId;
    }

    public void setMappedId(Integer mappedId) {
        this.mappedId = mappedId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public SearchCategoryResponse.Sport getSport() {
        return sport;
    }

    public void setSport(SearchCategoryResponse.Sport sport) {
        this.sport = sport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SearchCategoryResponse.Translations getTranslations() {
        return translations;
    }

    public void setTranslations(SearchCategoryResponse.Translations translations) {
        this.translations = translations;
    }

    public List<ProviderMappings> getProviderMappings() {
        return providerMappings;
    }

    public void setProviderMappings(List<ProviderMappings> providerMappings) {
        this.providerMappings = providerMappings;
    }

    @Override
    public String toString() {
        return "UnmapCategoryResponse{" +
                "mappedId=" + mappedId +
                ", regionId=" + regionId +
                ", sport=" + sport +
                ", name='" + name + '\'' +
                ", translations=" + translations +
                ", providerMappings=" + providerMappings +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnmapCategoryResponse that = (UnmapCategoryResponse) o;
        return Objects.equals(mappedId, that.mappedId) && Objects.equals(regionId, that.regionId) && Objects.equals(sport, that.sport) && Objects.equals(name, that.name) && Objects.equals(translations, that.translations) && Objects.equals(providerMappings, that.providerMappings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mappedId, regionId, sport, name, translations, providerMappings);
    }
}
