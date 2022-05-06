package modelDB;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class EventModel {

    private Integer sport_id;
    private Long id;
    private Integer category_id;
    private Integer tournament_id;
    private String provider;
    private String status;
    private String name;
    private Boolean outright;
    private Integer mapped_id;
    private String external_id;
    private Boolean suspended;

}
