package requestDto.category;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MapRequest {

private String externalId;
private Integer mappedId;
private String provider;
private Boolean single;

    public MapRequest(String externalId, Integer mappedId, String provider, Boolean single) {
        this.externalId = externalId;
        this.mappedId = mappedId;
        this.provider = provider;
        this.single = single;
    }
public MapRequest(){

}

    public MapRequest(Integer mappedId, String provider, Boolean single) {
        this.mappedId = mappedId;
        this.provider = provider;
        this.single = single;
    }

    public MapRequest(String externalId, String provider, Boolean single) {
        this.externalId = externalId;
        this.provider = provider;
        this.single = single;
    }

    public MapRequest(String externalId, Integer mappedId, Boolean single) {
        this.externalId = externalId;
        this.mappedId = mappedId;
        this.single = single;
    }

    public MapRequest(String externalId, Integer mappedId, String provider) {
        this.externalId = externalId;
        this.mappedId = mappedId;
        this.provider = provider;
    }

    public String getExternalId() {
        return externalId;
    }

    public Integer getMappedId() {
        return mappedId;
    }

    public String getProvider() {
        return provider;
    }

    public Boolean getSingle() {
        return single;
    }

}