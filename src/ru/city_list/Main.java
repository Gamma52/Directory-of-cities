package ru.city_list;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		List<City> city_list;
		City_DAO city_dao = new City_DAO();
		final String PATH_FILE = "src\\ru\\city_list\\city_list.csv";	
		
		
/*		parse form .csv file to Postgres 
  
		cities = parse_from_file(PATH_FILE);
		for(City city:cities) {
			city_dao.save(city);
			System.out.println(city.getName());
		}
*/
		
		City big_city = city_dao.get_big_city();
		System.out.print("Количество городов в базе: ");
		System.out.println(city_dao.get_count_city());
		
	
		System.out.print("Самый большой город: ");
		System.out.println(city_dao.get_big_city().getName());
		
		
		city_list = city_dao.get_all_city();
		System.out.print("Cписок всех городов в лексикографическом порядке: ");
		sortCityNameByLamda(city_list); // Lexical sort
		for(City city:city_list) {			
			System.out.println(city.getName());							
		} 
		

//			System.out.print("Cписок всех городов отсортированном по округу и наименованию: ");
//			print_list(sortCityDistrictAndName(city_list));	// District and Name sort
		
//			System.out.print("Самый больщой город в формате [Index] = Population");		
//			findMaxPopulation(city_list);
		
//			System.out.print("Количество городов в каждом регионе: ");		
//			quantityCities(city_list);

	}
	
	
	public static List<City> parse_from_file(String path_file) {
		List<City> cities = new ArrayList<City>();
		try {
			Scanner scanner = new Scanner(new File(path_file));
			while (scanner.hasNext()) {
				cities.add(parse_from_line(scanner.nextLine()));				
			}
			scanner.close();
		}catch (FileNotFoundException e){
				System.out.println("File not found");
		}
		
		return cities;
	}
	
	
	public static City parse_from_line(String line) {		
        Scanner scanner = new Scanner(line);
        String[] values = scanner.nextLine().split(";", 6);
        
        if (values[5].isEmpty()) {
            values[5] = null;
        }
        scanner.close();
        return new City(values[1], values[2], values[3], Integer.parseInt(values[4]), values[5]);
    }
	
	
	// Lexic sort 
	private static void sortCityNameByLamda(List<City> cities) {
        cities.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
    }
	
	
	// Distric and Name sort
    private static List<City> sortCityDistrictAndName(List<City> cities) {
        List<City> sort_list = cities.stream()
                .sorted(Comparator.comparing(City::getDistrict)
                .thenComparing(City::getName))                
                .collect(Collectors.toList());
        return sort_list;
    }
    
    
    private static void print_list(List<City> cities) {    	
		for(City city:cities) {			
			System.out.println(city.toString());							
		}        
    }
    
    
    private static void findMaxPopulation(List<City> cities) {       
    	City[] arr = cities.toArray(new City[0]);
        int max = 0;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getPopulation() > max) {
                max = arr[i].getPopulation();
                index = i;
            }
        }
        System.out.println("["+index+"] = "+max);
    }
    
    
    private static void quantityCities(List<City> cities) {
        Map<String, Integer> regions = new HashMap<>();
        cities.forEach(city -> regions.merge(city.getRegion(), 1, Integer::sum));
        regions.forEach((k, v) -> System.out.println(k+" - "+v));
    }
	
}
