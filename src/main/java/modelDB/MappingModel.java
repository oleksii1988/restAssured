package modelDB;

import lombok.*;

import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class MappingModel {

        private String externalId;
        private Integer mappedId;
        private String provider;
        private String name;

}





