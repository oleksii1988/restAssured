package config;


import jdbc.*;
import modelDB.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestConfig {


    public static void main(String[] args) throws SQLException, ParseException {

        CategoryDAO categoryDAO = new CategoryDAO();
        ConnectionFactory connectionFactory = new ConnectionFactory();
        CategoryModel categoryModel = new CategoryModel();
        //MappingModel mappingModel = new MappingModel("absolute:1:Ethiopia",171,"absolute","Ethiopia");
        SportDAO sportDAO = new SportDAO();
        SportFormDAO sportFormDAO = new SportFormDAO();
        TournamentDAO tournamentDAO = new TournamentDAO();

        //SportFormModel sportFormModel = new SportFormModel(888888,"Test",1, Map.of("order:1","length:120"));
        /*SportModel sportModel = new SportModel.SportModelBuilderImpl()
                .setId(999)
                .setName("Football Test")
                .setTranslations(Map.of("ENG", "Football Test", "UKR", "Футбол Тест","FRA","Teste de Futebol")).build();*/

        SportModel sportModel = new SportModel();
        EventDAO eventDAO = new EventDAO();


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



        //categoryDAO.getMapCategory(329);

        //categoryDAO.create(categoryMappingModel);
        //categoryDAO.createCategory(categoryModel);
        //sportDAO.getSportById(33);
        //sportFormDAO.createSportForm(sportFormModel);
        //tournamentDAO.getTournamentByIdPresentInCategory(102243);
        //eventDAO.getEventModelInDB();

        Long time = Instant.now().toEpochMilli();
        Long updateTime = time + 90000000;

        String startDate = "2022-04-24 09:45:00.000";

        Date epoch = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss").parse("01/01/1970 01:00:00");





        System.out.println(updateTime);








    }






}