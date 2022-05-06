package modelDB;

import lombok.*;

import java.util.Map;
@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor


public  class SportModel {
    private Integer id;
    private String name;
    private Map<String, String> translations;

    public SportModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}













