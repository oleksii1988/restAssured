package model;

import java.util.Objects;

public class CategoryMappingModel {

        private String externalId;
        private Integer mappedId;
        private String provider;
        private String name;


        public CategoryMappingModel(String externalId, Integer mappedId, String provider, String name) {
            this.externalId = externalId;
            this.mappedId = mappedId;
            this.provider = provider;
            this.name = name;



        }
        public CategoryMappingModel(){

        }

        public String getExternalId() {
            return externalId;
        }

    public String getName() {
        return name;
    }

    public Integer getMappedId() {
            return mappedId;
        }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public void setMappedId(Integer mappedId) {
        this.mappedId = mappedId;
    }


    @Override
    public String toString() {
        return "CategoryMappingModel{" +
                "externalId='" + externalId + '\'' +
                ", mappedId=" + mappedId +
                ", provider='" + provider + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryMappingModel that = (CategoryMappingModel) o;
        return Objects.equals(externalId, that.externalId) && Objects.equals(mappedId, that.mappedId) && Objects.equals(provider, that.provider) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(externalId, mappedId, provider, name);
    }
}





