package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.services.Point;

import java.util.List;

public class NextTurn {

    public static boolean nextTurn(int[][] matrix, List<Vertex> path, Board board) {
        int[][] newMatrix = new int[matrix.length][matrix[0].length];
        newMatrix = matrix.clone();

        for (int i = 0; i < path.size() - 1; i++) {
            newMatrix[path.get(i).getX()][path.get(i).getY()] = 0;
        }

        Vertex[][] graph = CreateGraph.createGraph(newMatrix, board);

        Point apple = board.getApples().get(0);
        Point badApple = board.getStones().get(0);
        Vertex vertexApple = graph[apple.getX()][apple.getY()];
        Vertex vertexBadApple = graph[badApple.getX()][badApple.getY()];

        DijkstraAlgorithm.dijkstra(vertexApple);

        List<Vertex> shortestPathForBadApple = DijkstraAlgorithm.getShortestPathTo(vertexBadApple);

        Point nextPosForBadApple = DijkstraAlgorithm.getNextPositionOnPath(apple, shortestPathForBadApple);

        if(Board.direction(apple, nextPosForBadApple) == null){
            return true;
        }else {
            return false;
        }
    }

}
