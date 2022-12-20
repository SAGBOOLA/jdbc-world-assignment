package org.example;

import org.example.dao.CityDao;
import org.example.dao.CityDaoJdbc;
import org.example.model.City;

public class App 
{
    public static void main( String[] args ) {
        CityDao test = new CityDaoJdbc();
        System.out.println(test.findById(5));
        System.out.println("---------------------");
        System.out.println(test.findByCode("afg"));
        System.out.println("---------------------");
        System.out.println(test.findByName("manchester"));
        System.out.println("---------------------");
        System.out.println(test.findAll());
        System.out.println("---------------------");
        City newCity = new City("Karlskrona", "SWE", "Lyckeby",34000);
        System.out.println(test.add(newCity));
        City city = new City(4080,"Karlskrona", "SWE", "Kungsplan",34000);
        System.out.println(test.update(city));
        System.out.println("---------------------");
        System.out.println(test.delete(city));
    }
}
