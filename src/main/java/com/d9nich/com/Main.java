package com.d9nich.com;

import java.io.File;


public class Main {

    private static final boolean IS_ONLINE = false;


    public static void main(String[] args) {
        String[] cities = SpainCountryFromFile.getCities(new File("src/main/java/com/d9nich/com/es.json"));
        int[][] roadMatrix = SpainCountryFromFile.getRoads(
                new File("src/main/java/com/d9nich/com/spainCountriesConection.txt"));

        long[][] matrix;
        if (IS_ONLINE)
            matrix = WorkWithApi.getDistanceMatrix(cities);
        else
            matrix = SpainCountryFromFile.getDistanceMatrix(new File(
                    "src/main/java/com/d9nich/com/distance.txt"));
    }
}
