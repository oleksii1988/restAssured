package requestDto.category;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;


@Data
@ToString
@EqualsAndHashCode

public class MapRequest {

    private String externalId;
    private Integer mappedId;
    private String provider;
    private Boolean single;

    @Data
    @ToString
    @EqualsAndHashCode


  public static class MapRequestBuilderImpl {

       private MapRequest mapRequest;

       public MapRequestBuilderImpl(){
           mapRequest = new MapRequest();
       }

        public MapRequestBuilderImpl setExternalId(String externalId){
           mapRequest.externalId = externalId;
           return this;
        }

        public MapRequestBuilderImpl setMappedId(Integer mappedId){
            mapRequest.mappedId = mappedId;
            return this;
        }

        public MapRequestBuilderImpl setProvider(String provider){
            mapRequest.provider = provider;
            return this;
        }

        public MapRequestBuilderImpl setSingle(Boolean single){
            mapRequest.single = single;
            return this;
        }

        public MapRequest build(){

            return mapRequest;
        }

    }
}
