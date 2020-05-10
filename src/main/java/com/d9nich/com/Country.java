package com.d9nich.com;

public class Country {
    private final String city;
    private final String admin;
    private final String country;
    private final String population_proper;
    private final String iso2;
    private final String capital;
    private final String lat;
    private final String lng;
    private final String population;

    public Country(String city, String admin, String country, String population_proper,
                   String iso2, String capital, String lat, String lng, String population) {
        this.city = city;
        this.admin = admin;
        this.country = country;
        this.population_proper = population_proper;
        this.iso2 = iso2;
        this.capital = capital;
        this.lat = lat;
        this.lng = lng;
        this.population = population;
    }

    public String getCity() {
        return city;
    }

    public String getAdmin() {
        return admin;
    }

    public String getCountry() {
        return country;
    }


    public String getIso2() {
        return iso2;
    }

    public String getCapital() {
        return capital;
    }

    public String getPopulation_proper() {
        return population_proper;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getPopulation() {
        return population;
    }
}
