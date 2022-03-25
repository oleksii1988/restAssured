package config;


import jdbc.CategoryDAO;
import model.CategoryModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConfige {


    public static void main(String[] args) throws SQLException {

        CategoryDAO categoryDAO = new CategoryDAO();
        ConnectionFactory connectionFactory = new ConnectionFactory();
        CategoryModel categoryModel = new CategoryModel();

       /* {
            try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement("SELECT * FROM category WHERE id = 326 and " +
                    "sport_id = 1")) {

                //statement.setInt(1, 626);

                final ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String nameCategory = "Name Category" + resultSet.getString("name");
                    categoryModel.setSport_id(resultSet.getInt("sport_id"));
                    int categoryId = resultSet.getInt("id");
                    System.out.println("SportId " + categoryModel.getSport_id());
                    System.out.println(nameCategory);
                    System.out.println("CategoryId " + categoryId);
                }


            } finally {
                connectionFactory.getConnection().close();
            }
        }*/



        categoryDAO.getMapCategory(329);







    }






}