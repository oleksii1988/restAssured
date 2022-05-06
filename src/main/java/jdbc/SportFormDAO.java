package jdbc;

import config.ConnectionFactory;
import modelDB.SportFormModel;
import org.postgresql.util.PGobject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SportFormDAO  {

    ConnectionFactory connectionFactory = new ConnectionFactory();

    public SportFormDAO() throws SQLException {
    }
// TODO разобраться как преобразовать список в стринг при запросе к БД
    public boolean createSportForm(SportFormModel model) throws SQLException {
        boolean result = false;
        PreparedStatement ps = connectionFactory.getPreparedStatement(SportFormDAO.SportFormQuerySQL.CREATE_SPORT_FORM.QUERY);

        Map<String, String> periods = new HashMap<>();
        periods.put("key1", "value1");
        periods.put("key2", "value2");
        periods.put("key3", "value3");

        PGobject jsonbObj = new PGobject();
        jsonbObj.setType("json");
        jsonbObj.setValue("{\"key\" : \"value\"}");

        try{
            ps.setInt(1,model.getId());
            ps.setString(2,model.getName());
            ps.setInt(3,model.getSport_id());
            ps.setObject( 4, periods );
            ps.setObject( 5, jsonbObj );



               ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                model.setId(resultSet.getInt("id"));
                model.setName(resultSet.getString("name"));
                model.setSport_id(resultSet.getInt("sport_id"));
                ((PGobject) resultSet.getObject("periods")).getValue();
                /*result.setTranslations(resultSet.getObject("translations",));*/

            }
        }
        catch (SQLException e) {

            System.out.println("Entity does not exist");
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        System.out.println(result);
        return result;

    }



    enum SportFormQuerySQL {
        DELETE_CATEGORY("DELETE FROM category WHERE id = (?)"),
        GET_MAP_CATEGORY("SELECT* FROM category_mapping WHERE mapped_id = (?)"),
        DELETE_CATEGORY_MAPPING("DELETE FROM category_mapping WHERE mapped_id = (?)"),
        CREATE_CATEGORY_MAPPING("INSERT INTO category_mapping (external_id, mapped_id, provider, name) VALUES ((?),(?),(?),(?))"),
        CREATE_SPORT_FORM("INSERT INTO sport_form (id, name, sport_id, periods) VALUES ((?),(?),(?),(?),(?))"),
        GET_CATEGORY("SELECT* FROM category WHERE id = (?)");


        String QUERY;

        SportFormQuerySQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }


}
