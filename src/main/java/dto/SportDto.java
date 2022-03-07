package dto;

import org.intellij.lang.annotations.Language;

import java.util.Map;
import java.util.Objects;

public class SportDto {

    private Integer mappedId;
    private Integer regionId;
    private String name;
    private Map <Language, String> translations;


    public SportDto(Integer mappedId, Integer regionId, String name, Map<Language, String> translations) {
        this.mappedId = mappedId;
        this.regionId = regionId;
        this.name = name;
        this.translations = translations;
    }

    public SportDto() {
    }



    public Integer getMappedId() {
        return mappedId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public String getName() {
        return name;
    }

    public Map<Language, String> getTranslations() {
        return translations;
    }

    public SportDto setMappedId(Integer mappedId) {
        this.mappedId = mappedId;
        return this;
    }

    public SportDto setRegionId(Integer regionId) {
        this.regionId = regionId;
        return this;
    }

    public SportDto setName(String name) {
        this.name = name;
        return this;
    }

    public SportDto setTranslations(Map<Language, String> translations) {
        this.translations = translations;
        return this;
    }

    @Override
    public String toString() {
        return "SportDto{" +
                "mappedId=" + mappedId +
                ", regionId=" + regionId +
                ", name='" + name + '\'' +
                ", translations=" + translations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportDto sportDto = (SportDto) o;
        return Objects.equals(mappedId, sportDto.mappedId) && Objects.equals(regionId, sportDto.regionId) && Objects.equals(name, sportDto.name) && Objects.equals(translations, sportDto.translations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mappedId, regionId, name, translations);
    }
}
