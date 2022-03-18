package config;


import jdbc.CategoryModelDAO;
import model.CategoryModel;

import java.sql.SQLException;

public class TestConfige {


    public static void main(String[] args) throws SQLException {

        CategoryModelDAO categoryModelDAO = new CategoryModelDAO();
        categoryModelDAO.delete();


    }






}