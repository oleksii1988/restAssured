package dto;

import java.util.Objects;

public class ProviderMappings {

    private String externalId;
    private String name;
    private String provider;

    public ProviderMappings(String externalId, String name, String provider) {
        this.externalId = externalId;
        this.name = name;
        this.provider = provider;
    }

    public ProviderMappings(){

    }


    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "ProviderMapping{" +
                "externalId='" + externalId + '\'' +
                ", name='" + name + '\'' +
                ", provider='" + provider + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProviderMappings that = (ProviderMappings) o;
        return Objects.equals(externalId, that.externalId) && Objects.equals(name, that.name) && Objects.equals(provider, that.provider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(externalId, name, provider);
    }
}
