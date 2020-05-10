package com.d9nich.com.searchingAlgorithms;

import java.util.ArrayList;

public class HungrySearch {
    private HungrySearch() {
    }

    public static String search(long[][] distanceMatrix, int[][] roadMatrix, int indexOfStartCity, int indexOfFinishCity,
                                String[] cities) {
        ArrayList<Integer> path = new ArrayList<>();
        boolean[] walkedThrough = new boolean[roadMatrix.length];
        walkedThrough[indexOfStartCity] = true;
        path.add(indexOfStartCity);

        while (path.get(path.size() - 1) != indexOfFinishCity && !isFull(walkedThrough)) {
            int last = path.get(path.size() - 1);
            if (roadMatrix[indexOfFinishCity][last] == 1) {
                path.add(indexOfFinishCity);
                walkedThrough[indexOfFinishCity] = true;
                break;
            }
            for (int i = 0; i < roadMatrix.length; i++) {
                if (!walkedThrough[i] && roadMatrix[i][path.get(path.size() - 1)] == 1) {
                    path.add(i);
                    walkedThrough[i] = true;
                    break;
                }
            }
            if (last == path.get(path.size() - 1))
                path.remove(Integer.valueOf(last));
        }
        long fullPath = 0;
        StringBuilder stringBuilder = new StringBuilder(cities[indexOfStartCity]).append(" - ")
                .append(cities[indexOfFinishCity]).append("\n");
        int current = indexOfStartCity;
        for (Integer integer : path) {
            fullPath += distanceMatrix[current][integer];
            stringBuilder.append(cities[integer]).append("->");
            current = integer;
        }
        stringBuilder.append("\nВідстань: ").append(fullPath / 1000.0).append("Км");
        return stringBuilder.toString();
    }

    private static boolean isFull(boolean[] walkThrough) {
        for (boolean b : walkThrough)
            if (!b)
                return false;
        return true;
    }
}
