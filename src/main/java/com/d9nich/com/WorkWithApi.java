package com.d9nich.com;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;

import java.io.IOException;
import java.util.Arrays;

public class WorkWithApi {
    public static final GeoApiContext geoApiContext = new GeoApiContext.Builder().
            apiKey("your_key").build();

    public static long[] getDistanceLine(String countryName, String[] countries) {
        try {
            DistanceMatrix matrix =
                    DistanceMatrixApi.getDistanceMatrix(geoApiContext, new String[]{countryName}, countries).await();
            long[] output = new long[countries.length];
            for (int i = 0; i < output.length; i++) {
                output[i] = matrix.rows[0].elements[i].distance.inMeters;
            }
            return output;
        } catch (ApiException ex) {
            System.out.println("API exception");
        } catch (InterruptedException ex) {
            System.out.println("Interrupted exception");
        } catch (IOException ex) {
            System.out.println("Stream exception.");
        }
        return null;
    }

    public static long[][] getDistanceMatrix(String[] countries) {
        long[][] matrix = new long[countries.length][countries.length];
        for (int i = 0; i < matrix.length - 1; i++) {
            long[] array = getDistanceLine(countries[i], Arrays.copyOfRange(countries, i + 1, countries.length));
            for (int j = i + 1; j < matrix.length; j++) {
                assert array != null;
                matrix[i][j] = matrix[j][i] = array[j - i - 1];
            }
        }
        geoApiContext.shutdown();
        return matrix;
    }
}
