package config;


import model.CategoryModel;

import java.sql.*;


public class DatabaseConfig {

        private final String url = "jdbc:postgresql://postgres.feed-test.svc.cluster.local:5432/mapping_db";
        private final String user = "postgres";
        private final String password = "lQAeumW3LkXRfZ4TNYioek1RES";

        private final Connection connection = DriverManager.getConnection(url.trim(), user, password);

        public Connection getConnection() {
        return connection;
    }


    public DatabaseConfig() throws SQLException {
    }


        {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM category WHERE id = 325 and " +
                    "sport_id = 1")) {

                //statement.setInt(1, 626);

                final ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String nameCategory = "Name Category " + resultSet.getString("name");
                    int sportId = resultSet.getInt("sport_id");
                    int categoryId = resultSet.getInt("id");
                    System.out.println("SportId " + sportId);
                    System.out.println(nameCategory);
                    System.out.println("CategoryId " + categoryId);
                }


            } finally {
                connection.close();
            }
        }



}
