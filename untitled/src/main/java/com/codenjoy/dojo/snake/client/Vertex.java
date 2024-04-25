package com.codenjoy.dojo.snake.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Vertex implements Comparable<Vertex> {
    public int value;
    public Set<Vertex> neighbors;
    public List<Edge> edges = new ArrayList<>();
    public boolean visited;
    public Vertex prev;
    double minDistance = Double.POSITIVE_INFINITY;

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Vertex(int value) {
        this.value = value;
        this.neighbors = new HashSet<>();
    }

    public boolean isVisited() {
        return visited;
    }

    @Override
    public int compareTo(Vertex o) {
        return Integer.compare(value, o.value);
    }
}
