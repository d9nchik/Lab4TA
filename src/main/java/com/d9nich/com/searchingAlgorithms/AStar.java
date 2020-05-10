package com.d9nich.com.searchingAlgorithms;

import java.util.ArrayList;
import java.util.Collections;

public class AStar {
    private AStar() {
    }

    public static String search(long[][] distanceMatrix, int[][] roadMatrix, int indexOfStartCity, int indexOfFinishCity,
                                String[] cities) {
        boolean[] watched = new boolean[cities.length];
        watched[indexOfStartCity] = true;
        ArrayList<Node> closed = new ArrayList<>();
        ArrayList<Node> opened = new ArrayList<>();
        opened.add(new Node(distanceMatrix[indexOfStartCity][indexOfFinishCity], 0, indexOfStartCity, -1));
        while (opened.size() != 0) {
            Collections.sort(opened);
            closed.add(opened.get(0));
            opened.remove(0);
            if (closed.get(closed.size() - 1).getPosition() == indexOfFinishCity)
                break;
            ArrayList<Node> neighbours = findNeighbours(closed.get(closed.size() - 1), roadMatrix, distanceMatrix);
            for (Node neighbour : neighbours) {
                if (!watched[neighbour.getPosition()]) {
                    opened.add(neighbour);
                    watched[neighbour.getPosition()] = true;
                } else if (opened.contains(neighbour) &&
                        opened.get(opened.indexOf(neighbour)).getFullPath() > neighbour.getFullPath()) {
                    opened.remove(neighbour);
                    opened.add(neighbour);
                }
            }
        }
        if (closed.get(closed.size() - 1).getPosition() == indexOfFinishCity) {
            Node finish = closed.get(closed.size() - 1);
            long fullPath = finish.getPathFromStart();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(cities[finish.getPosition()]).append("->");
            for (int i = closed.size() - 2; i >= 0; i--) {
                if (closed.get(i).getPosition() == finish.getFather()) {
                    finish = closed.get(i);
                    stringBuilder.append(cities[finish.getPosition()]).append("->");
                }
            }
            return stringBuilder.append("\n Шлях: ").append(fullPath).toString();
        }
        return "";
    }

    private static ArrayList<Node> findNeighbours(Node node, int[][] roadMatrix, long[][] distanceMatrix) {
        ArrayList<Node> neighbours = new ArrayList<>();
        for (int i = 0; i < roadMatrix.length; i++)
            if (roadMatrix[node.getPosition()][i] == 1)
                neighbours.add(new Node(distanceMatrix[node.getPosition()][i],
                        node.getPathFromStart() + distanceMatrix[node.getPosition()][i], i, node.getPosition()));
        return neighbours;
    }
}

class Node implements Comparable<Node> {
    private final long heuristic;
    private final int position;
    private long pathFromStart;
    private int father;

    public Node(long heuristic, long pathFromStart, int position, int father) {
        this.heuristic = heuristic;
        this.pathFromStart = pathFromStart;
        this.position = position;
        this.father = father;
    }

    public int getFather() {
        return father;
    }

    public void setFather(int father) {
        this.father = father;
    }

    public long getHeuristic() {
        return heuristic;
    }

    public long getPathFromStart() {
        return pathFromStart;
    }

    public void setPathFromStart(long pathFromStart) {
        this.pathFromStart = pathFromStart;
    }

    public long getFullPath() {
        return pathFromStart + heuristic;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node)
            return ((Node) obj).position == position;
        return super.equals(obj);
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(getFullPath(), o.getFullPath());
    }
}