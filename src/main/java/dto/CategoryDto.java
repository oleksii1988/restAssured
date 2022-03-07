package dto;

import org.intellij.lang.annotations.Language;

import java.util.Map;
import java.util.Objects;

public class CategoryDto {

    private Integer mappedId;
    private String name;
    private Integer regionId;
    private SportDto sport;
    private Map<Language, String> translations;

    public CategoryDto(Integer mappedId, String name, Integer regionId, SportDto sport, Map<Language, String> translations) {
        this.mappedId = mappedId;
        this.name = name;
        this.regionId = regionId;
        this.sport = sport;
        this.translations = translations;
    }


    public CategoryDto(){}

    public Integer getMappedId() {
        return mappedId;
    }

    public CategoryDto setMappedId(Integer mappedId) {
        this.mappedId = mappedId;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryDto setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public CategoryDto setRegionId(Integer regionId) {
        this.regionId = regionId;
        return this;
    }

    public SportDto getSport() {
        return sport;
    }

    public CategoryDto setSport(SportDto sport) {
        this.sport = sport;
        return this;
    }

    public Map<Language, String> getTranslations() {
        return translations;
    }

    public CategoryDto setTranslations(Map<Language, String> translations) {
        this.translations = translations;
        return this;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "mappedId=" + mappedId +
                ", name='" + name + '\'' +
                ", regionId=" + regionId +
                ", sport=" + sport +
                ", translations=" + translations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDto that = (CategoryDto) o;
        return Objects.equals(mappedId, that.mappedId) && Objects.equals(name, that.name) && Objects.equals(regionId, that.regionId) && Objects.equals(sport, that.sport) && Objects.equals(translations, that.translations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mappedId, name, regionId, sport, translations);
    }



}
