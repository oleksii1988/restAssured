package modelDB;

import lombok.*;


import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CategoryModel {

    private Integer id;
    private String name;
    private Integer sport_id;
    private Integer region_id;
    private Map<String, String> translations;

    public CategoryModel(Integer id, String name, Integer sport_id, Integer region_id) {
        this.id = id;
        this.name = name;
        this.sport_id = sport_id;
        this.region_id = region_id;
    }
}
