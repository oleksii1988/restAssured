package modelDB;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SportFormModel {

    private Integer id;
    private String name;
    private Integer sport_id;
    private ArrayList<String> periods;

}
