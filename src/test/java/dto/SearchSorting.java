package dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
@ToString
@EqualsAndHashCode

public class SearchSorting {
    public SearchSorting(Sorting sorting) {
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @Data
    @ToString
    @EqualsAndHashCode

    public static class Sorting{
        private String direction;
        private String fieldName;
        private int order;

        public Sorting(String direction, String fieldName, Integer order) {
            this.direction = direction;
            this.fieldName = fieldName;
            this.order = order;
        }

        public Sorting(){}
    
    
    }

    

}
