package com.d9nich.com;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;

import java.io.IOException;


public class Main {
    private static final GeoApiContext geoApiContext = new GeoApiContext.Builder().apiKey("your_key").build();

    public static void main(String[] args) throws InterruptedException, ApiException, IOException {
        String[] origins =
                new String[]{
                        "Perth, Australia", "Sydney, Australia", "Melbourne, Australia",
                        "Adelaide, Australia", "Brisbane, Australia", "Darwin, Australia",
                        "Hobart, Australia", "Canberra, Australia"
                };
        String[] destinations =
                new String[]{
                        "Uluru, Australia",
                        "Kakadu, Australia",
                        "Blue Mountains, Australia",
                        "Bungle Bungles, Australia",
                        "The Pinnacles, Australia"
                };
        DistanceMatrix matrix =
                DistanceMatrixApi.getDistanceMatrix(geoApiContext, origins, destinations).await();

        System.out.println(matrix.rows[1].elements[1].distance.inMeters);
        System.out.println();
        geoApiContext.shutdown();

    }
}
