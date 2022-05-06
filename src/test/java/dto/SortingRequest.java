package dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SortingRequest {
   
    private List<Sorting> sorting;



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

    public static class Sorting{
        private String direction;
        private String fieldName;
        private Integer order;


    }

    public interface SortingBuilder{
        SortingBuilder setDirection(String direction);
        SortingBuilder setFieldName(String fieldName);
        SortingBuilder setOrder(Integer order);
        SortingRequest build();

    }

    public static class SortingBuilderImpl implements SortingBuilder{

        Sorting sorting = new Sorting();
        @Override
        public SortingBuilder setDirection(String direction) {
            sorting.direction = direction;
            return this;
        }

        @Override
        public SortingBuilder setFieldName(String fieldName) {
            sorting.fieldName = fieldName;
            return this;
        }

        @Override
        public SortingBuilder setOrder(Integer order) {
            sorting.order = order;
            return this;
        }

        @Override
        public SortingRequest build() {
            return  new SortingRequest(List.of(sorting));
        }
    }

    

}
