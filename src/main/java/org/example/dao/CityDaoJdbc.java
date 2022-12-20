package org.example.dao;

import org.example.DataBaseConnection;
import org.example.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJdbc implements CityDao {
    public City findById(int id) {
        City cityWithId = null;
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select * from City where id = ?");
                ){

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()){

                while (resultSet.next()){
                    cityWithId = new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("countryCode"),
                            resultSet.getString("district"),
                            resultSet.getInt("population")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return cityWithId;
    }

    public List<City> findByCode(String code) {
        List<City> cityWithCode = new ArrayList<>();
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select * from City where countryCode = ?");
        ){

            preparedStatement.setString(1, code);

            try (ResultSet resultSet = preparedStatement.executeQuery()){

                while (resultSet.next()){
                    cityWithCode.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("countryCode"),
                            resultSet.getString("district"),
                            resultSet.getInt("population")
                    ));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return cityWithCode;
    }

    public List<City> findByName(String name) {
        List<City> cityWithName = new ArrayList<>();
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select * from City where name like ?");
        ){

            preparedStatement.setString(1, "%" + name + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()){

                while (resultSet.next()){
                    cityWithName.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("countryCode"),
                            resultSet.getString("district"),
                            resultSet.getInt("population")
                    ));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return cityWithName;
    }

    public List<City> findAll() {
        List<City> allCities = new ArrayList<>();
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select * from City");
        ){

            try (ResultSet resultSet = preparedStatement.executeQuery()){

                while (resultSet.next()){
                    allCities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("countryCode"),
                            resultSet.getString("district"),
                            resultSet.getInt("population")
                    ));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return allCities;
    }

    public City add(City city) {
        City newCity = null;
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("insert into City(name, countryCode, district, population) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ){

            preparedStatement.setString(1,city.getName());
            preparedStatement.setString(2,city.getCountryCode());
            preparedStatement.setString(3,city.getDistrict());
            preparedStatement.setInt(4,city.getPopulation());

            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){

                while (resultSet.next()){
                    System.out.println(resultSet.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return city;
    }

    public City update(City city) {
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("update City set name = ?, countryCode = ?, district = ?, population = ? where id = ?");
        ){

            preparedStatement.setString(1,city.getName());
            preparedStatement.setString(2,city.getCountryCode());
            preparedStatement.setString(3,city.getDistrict());
            preparedStatement.setInt(4,city.getPopulation());
            preparedStatement.setInt(5,city.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return city;
    }

    public int delete(City city) {
        int deletedCity = 0;
        try(
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("delete from City where id = ?");
                ){

            preparedStatement.setInt(1, city.getId());
            deletedCity = preparedStatement.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        };
        return deletedCity;
    }
}
