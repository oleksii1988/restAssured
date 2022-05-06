package modelDB;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TournamentModel {

private Integer id;
private Integer category_id;
private String name;
private Map<String, String> translations;
private Integer sport_form_id;

    public TournamentModel(Integer id, Integer category_id, String name, Integer sport_form_id) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.sport_form_id = sport_form_id;
    }



}
