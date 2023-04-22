package ru.city_list;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class City_DAO {
	public static final String URL = "jdbc:postgresql://localhost:5432/city_bd";
	public static final String USERNAME = "postgres";	
	public static final String PASSWORD = "admin";
	
	private static int CITY_COUNT = 0;
	
	private static Connection connection;
	private static Statement statement;
	
	
	static {
		try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();           
        }		
		
		try{
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
		}catch (SQLException e) {
			e.printStackTrace();			
		}
	}
	
	
	public List<City> get_all_city() {
		List<City> cities = new ArrayList<City>();
		try {			
			String SQL = "SELECT * FROM City";
			ResultSet resultSet = statement.executeQuery(SQL);
			
			while (resultSet.next()) {
				City city = new City();				
				city.setName(resultSet.getString("name"));
				city.setRegion(resultSet.getString("region"));
				city.setDistrict(resultSet.getString("district"));
				city.setPopulation(resultSet.getInt("population"));
				city.setFoundation(resultSet.getString("foundation"));
				cities.add(city);
			}				
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cities;
	}	
	
	
	public void save(City city) {
      try {
    	  CITY_COUNT++;
          String SQL = "INSERT INTO City VALUES(" + CITY_COUNT + ",'" + city.getName() +
                  "','"+city.getRegion()+"','"+city.getDistrict()+"',"+city.getPopulation()+",'"+city.getFoundation()+"')";

          statement.executeUpdate(SQL);
      } catch (SQLException throwables) {
          throwables.printStackTrace();
      }   
  }
	
	
	public City get_big_city() {	
		City city = new City();
		try {			
			String SQL = "SELECT * FROM City ORDER BY population DESC LIMIT 1";
			ResultSet resultSet = statement.executeQuery(SQL);
			if (resultSet.next()) {
				city.setName(resultSet.getString("name"));
				city.setRegion(resultSet.getString("region"));
				city.setDistrict(resultSet.getString("district"));
				city.setPopulation(resultSet.getInt("population"));
				city.setFoundation(resultSet.getString("foundation"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return city;
	}
	
	
	public int get_count_city() {			
		int count = 0;
		try {			
			String SQL = "SELECT COUNT(id) as count FROM City";
			ResultSet resultSet = statement.executeQuery(SQL);
			if (resultSet.next()) {
				count = resultSet.getInt("count");				
			}			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
}
