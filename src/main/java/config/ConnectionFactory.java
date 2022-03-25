package config;


import lombok.SneakyThrows;

import java.sql.*;

public class ConnectionFactory {


        private final String url = "jdbc:postgresql://postgres.feed-test.svc.cluster.local:5432/mapping_db";
        private final String user = "postgres";
        private final String password = "lQAeumW3LkXRfZ4TNYioek1RES";

        private final Connection connection = DriverManager.getConnection(url.trim(), user, password);



    public ConnectionFactory() throws SQLException {
    }

    @SneakyThrows
    public PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement ps = connection.prepareStatement(sql);

        try {
            ps.getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
        }

        public void closePrepareStatement(PreparedStatement ps){

            try{
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }




       /* {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM category WHERE id = 326 and " +
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
        }*/



}
