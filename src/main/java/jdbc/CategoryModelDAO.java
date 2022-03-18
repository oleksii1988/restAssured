package jdbc;
import config.DatabaseConfig;
import model.CategoryModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoryModelDAO implements DAO<CategoryModel, Integer> {


    DatabaseConfig databaseConfig = new DatabaseConfig();

    public CategoryModelDAO() throws SQLException {

    }

    @Override
    public boolean delete(CategoryModel key) {

        boolean result = false;

        try (PreparedStatement statement = databaseConfig.getConnection().prepareStatement(CategoryQuerySQL.DELETE.QUERY)) {

            statement.setInt(1, key.getId());

            result = statement.executeQuery().next();
        } catch (SQLException e) {

        }


        return result;
    }

    enum CategoryQuerySQL {
        DELETE("DELETE FROM category WHERE id = (?)");

        String QUERY;

        CategoryQuerySQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }

}
