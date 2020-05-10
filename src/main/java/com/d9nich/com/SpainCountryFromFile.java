package com.d9nich.com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpainCountryFromFile {
    private SpainCountryFromFile() {
    }

    public static String[] getCities(File file) {
        return getCities(file, 15);
    }

    public static String[] getCities(File file, int numberOfCountriesToRead) {
        Gson gson = new GsonBuilder().create();
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner input = new Scanner(new BufferedInputStream(new FileInputStream(file)))) {
            while (input.hasNext()) {
                stringBuilder.append(input.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        final Country[] countries = gson.fromJson(stringBuilder.toString(), Country[].class);
        return citiesName(countries, numberOfCountriesToRead);
    }

    private static String[] citiesName(Country[] countries, int numberOfFirst) {
        String[] citiesName = new String[numberOfFirst];
        for (int i = 0; i < numberOfFirst; i++)
            citiesName[i] = countries[i].getCity() + ", Spain";
        return citiesName;
    }

    public static int[][] getRoads(File file) {
        try (Scanner input = new Scanner(new BufferedInputStream(new FileInputStream(file)))) {
            String[] lines = input.nextLine().split("\\s");
            int[][] matrixOfRoad = new int[lines.length][lines.length];
            for (int i = 0; i < lines.length; i++)
                matrixOfRoad[0][i] = Integer.parseInt(lines[i]);
            int j = 1;
            while (input.hasNext()) {
                lines = input.nextLine().split("\\s");
                for (int i = 0; i < lines.length; i++)
                    matrixOfRoad[j][i] = Integer.parseInt(lines[i]);
                j++;
            }
            return matrixOfRoad;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        return null;
    }

    public static long[][] getDistanceMatrix(File file) {
        try (Scanner input = new Scanner(new BufferedInputStream(new FileInputStream(file)))) {
            String[] lines = input.nextLine().split("\\s");
            long[][] distanceMatrix = new long[lines.length][lines.length];
            for (int i = 0; i < lines.length; i++)
                distanceMatrix[0][i] = Integer.parseInt(lines[i]);
            int j = 1;
            while (input.hasNext()) {
                lines = input.nextLine().split("\\s");
                for (int i = 0; i < lines.length; i++)
                    distanceMatrix[j][i] = Long.parseLong(lines[i]);
                j++;
            }
            return distanceMatrix;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        return null;
    }
}
