package modelDB;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ParticipantModel {

    private Integer id;
    private String alternative_name;
    private String full_name;
    private String country;
    private Integer sport_id;
    private Integer category_id;

    public ParticipantModel(Integer id, String full_name, String country, Integer sport_id, Integer category_id) {
        this.id = id;
        this.full_name = full_name;
        this.country = country;
        this.sport_id = sport_id;
        this.category_id = category_id;
    }


}
