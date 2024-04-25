package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {
    public static void dijkstra(Vertex source) {
        source.minDistance = 0;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.offer(source);

        while (!vertexQueue.isEmpty()) {
            Vertex current = vertexQueue.poll();
            // Проходим по всем исходящим дугам
            for (Edge adjEdge : current.edges) {
                // У каждой дуги берём вершину с которой дуга связывает
                Vertex neighbour = adjEdge.target();
                // Берём вес дуги для вычисления расстояния от источника
                double edgeWeight = adjEdge.weight();
                // Вычисляем расстояние от источника до текущей вершины
                double distanceThroughCurrent = current.minDistance + edgeWeight;
                // Проверяем надо ли менять пометку
                // Проверяем меньше ли расстояние от источника до текущей вершины чем текущая пометка
                if (distanceThroughCurrent < neighbour.minDistance) {
                    // Меняем пометку для смежной вершины
                    // Ставим новую пометку
                    neighbour.minDistance = distanceThroughCurrent;
                    // Проставляем каждой смежной вершине ссылку на текущую, для определения пути
                    neighbour.prev = current;
                    // Помещаем смежную вершину в очередь с обновлённой пометкой
                    vertexQueue.offer(neighbour);
                }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.prev) {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }

    static Point getNextPositionOnPath(Point current, List<Vertex> shortestPath) {
        if (shortestPath == null || shortestPath.size() <= 1) {
            return current;
        }
        Vertex nextVertex = shortestPath.get(1);
        return new PointImpl(nextVertex.getX(), nextVertex.getY());
    }



}
