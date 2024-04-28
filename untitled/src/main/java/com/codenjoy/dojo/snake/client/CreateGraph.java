package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.snake.model.Elements;

import java.util.Arrays;

public class CreateGraph {


    public static Vertex[][] createGraph(int[][] matrixOfAvailableVertices, Board board) {
        Vertex[][] graph = new Vertex[matrixOfAvailableVertices.length][matrixOfAvailableVertices.length];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Vertex currentVertex = new Vertex(matrixOfAvailableVertices[i][j]);
                graph[i][j] = currentVertex;
                currentVertex.setX(i);
                currentVertex.setY(j);
            }
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {

                Point right = board.getApples().get(0);
                right.change(Direction.RIGHT);
                Point up = board.getApples().get(0);
                up.change(Direction.UP);
                Point left = board.getApples().get(0);
                left.change(Direction.LEFT);
                Point down = board.getApples().get(0);
                down.change(Direction.DOWN);



                if (matrixOfAvailableVertices[i][j] == 1 && i == board.getApples().get(0).getX()
                        && j == board.getApples().get(0).getY()) {
                    if (board.getWalls().contains(left) || board.getWalls().contains(right)) {
                        if (matrixOfAvailableVertices[i - 1][j] == 1) {
                            graph[i][j].neighbors.add(graph[i - 1][j]);
                            graph[i][j].edges.addAll(Arrays.asList(
                                    new Edge(graph[i - 1][j], 1)));

                        }

                        if (matrixOfAvailableVertices[i + 1][j] == 1) {
                            graph[i][j].neighbors.add(graph[i + 1][j]);
                            graph[i][j].edges.addAll(Arrays.asList(
                                    new Edge(graph[i + 1][j], 1)));
                        }

                        if (matrixOfAvailableVertices[i][j - 1] == 1) {
                            graph[i][j].neighbors.add(graph[i][j - 1]);
                            graph[i][j].edges.addAll(Arrays.asList(
                                    new Edge(graph[i][j - 1], 1000)));
                        }

                        if (matrixOfAvailableVertices[i][j + 1] == 1) {
                            graph[i][j].neighbors.add(graph[i][j + 1]);
                            graph[i][j].edges.addAll(Arrays.asList(
                                    new Edge(graph[i][j + 1], 1000)));
                        }
                    }else if (board.getWalls().contains(up) || board.getWalls().contains(down)){
                        if (matrixOfAvailableVertices[i - 1][j] == 1) {
                            graph[i][j].neighbors.add(graph[i - 1][j]);
                            graph[i][j].edges.addAll(Arrays.asList(
                                    new Edge(graph[i - 1][j], 1000)));

                        }

                        if (matrixOfAvailableVertices[i + 1][j] == 1) {
                            graph[i][j].neighbors.add(graph[i + 1][j]);
                            graph[i][j].edges.addAll(Arrays.asList(
                                    new Edge(graph[i + 1][j], 1000)));
                        }

                        if (matrixOfAvailableVertices[i][j - 1] == 1) {
                            graph[i][j].neighbors.add(graph[i][j - 1]);
                            graph[i][j].edges.addAll(Arrays.asList(
                                    new Edge(graph[i][j - 1], 1)));
                        }

                        if (matrixOfAvailableVertices[i][j + 1] == 1) {
                            graph[i][j].neighbors.add(graph[i][j + 1]);
                            graph[i][j].edges.addAll(Arrays.asList(
                                    new Edge(graph[i][j + 1], 1)));
                        }
                    }
                } else if (matrixOfAvailableVertices[i][j] == 1 && i == board.getStones().get(0).getX()
                        && j == board.getStones().get(0).getY()) {

                    if (matrixOfAvailableVertices[i - 1][j] == 1) {
                        graph[i][j].neighbors.add(graph[i - 1][j]);
                        graph[i][j].edges.addAll(Arrays.asList(
                                new Edge(graph[i - 1][j], 1000)));

                    }

                    if (matrixOfAvailableVertices[i + 1][j] == 1) {
                        graph[i][j].neighbors.add(graph[i + 1][j]);
                        graph[i][j].edges.addAll(Arrays.asList(
                                new Edge(graph[i + 1][j], 1000)));
                    }

                    if (matrixOfAvailableVertices[i][j - 1] == 1) {
                        graph[i][j].neighbors.add(graph[i][j - 1]);
                        graph[i][j].edges.addAll(Arrays.asList(
                                new Edge(graph[i][j - 1], 1000)));
                    }

                    if (matrixOfAvailableVertices[i][j + 1] == 1) {
                        graph[i][j].neighbors.add(graph[i][j + 1]);
                        graph[i][j].edges.addAll(Arrays.asList(
                                new Edge(graph[i][j + 1], 1000)));
                    }
                } else if (matrixOfAvailableVertices[i][j] == 1) {
                    if (matrixOfAvailableVertices[i - 1][j] == 1) {
                        graph[i][j].neighbors.add(graph[i - 1][j]);
                        graph[i][j].edges.addAll(Arrays.asList(
                                new Edge(graph[i - 1][j], 1)));

                    }

                    if (matrixOfAvailableVertices[i + 1][j] == 1) {
                        graph[i][j].neighbors.add(graph[i + 1][j]);
                        graph[i][j].edges.addAll(Arrays.asList(
                                new Edge(graph[i + 1][j], 1)));
                    }

                    if (matrixOfAvailableVertices[i][j - 1] == 1) {
                        graph[i][j].neighbors.add(graph[i][j - 1]);
                        graph[i][j].edges.addAll(Arrays.asList(
                                new Edge(graph[i][j - 1], 1)));
                    }

                    if (matrixOfAvailableVertices[i][j + 1] == 1) {
                        graph[i][j].neighbors.add(graph[i][j + 1]);
                        graph[i][j].edges.addAll(Arrays.asList(
                                new Edge(graph[i][j + 1], 1)));
                    }
                }

            }
        }

        return graph;
    }

    public static int[][] createMatrix(Board board) {
        int[][] matrixOfAvailableVertices = new int[15][15];// 15 - розмір ігрового поля

        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                if (inaccessibleTopOfVertices(x, y, board)) {
                    matrixOfAvailableVertices[x][y] = 0;
                } else {
                    matrixOfAvailableVertices[x][y] = 1;
                }
            }
        }
        System.out.println(Arrays.deepToString(matrixOfAvailableVertices));
        return matrixOfAvailableVertices;
    }

    public static boolean inaccessibleTopOfVertices(int x, int y, Board board) {
        return board.isAt(x, y, Elements.BREAK, Elements.TAIL_VERTICAL, Elements.TAIL_HORIZONTAL, Elements.TAIL_LEFT_DOWN,
                Elements.TAIL_LEFT_UP, Elements.TAIL_RIGHT_UP, Elements.TAIL_RIGHT_DOWN
        );
    }
}
