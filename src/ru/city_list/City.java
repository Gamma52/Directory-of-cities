package ru.city_list;

public class City {
    private String name; 
    private String region;   
    private String district;  
    private int population;   
    private String foundation;
    
    public City() {};

    public City(String name, String region, String district, int population, String foundation) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }
    
    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }
    
    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }
    
    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public String getFoundation() {
        return foundation;
    }
    
    @Override
    public String toString() {
    	String str = new String();
    	str =   "City{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population + '\'';
    	if (this.foundation != null) 
    		str += ", foundation='" + foundation + '\'' + '}';
    	
    	str += '}';
    	
    	return str;
    }
}
