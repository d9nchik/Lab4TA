package com.d9nich.com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpainCountryFromJson {
    private SpainCountryFromJson() {
    }

    public static Country[] getCities() {
        Gson gson = new GsonBuilder().create();
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner input = new Scanner(new BufferedInputStream(new FileInputStream(
                new File("src/main/java/com/d9nich/com/es.json"))))) {
            while (input.hasNext()) {
                stringBuilder.append(input.nextLine().replaceAll(" ", ""));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        return gson.fromJson(stringBuilder.toString(), Country[].class);
    }
}
