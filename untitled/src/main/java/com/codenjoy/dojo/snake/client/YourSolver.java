package com.codenjoy.dojo.snake.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.RandomDice;

import java.util.List;

/**
 * User: Yaroslav
 */
public class YourSolver implements Solver<Board> {

    private Dice dice;
    private Board board;

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;


        if (board.isGameOver()) {
            return Direction.RIGHT.toString();
        }

        Point apple = board.getApples().get(0);
        Point head = board.getHead();
        Point badApple = board.getStones().get(0);
        Point tail = board.getTail();

        int[][] matrix = CreateGraph.createMatrix(board);
        Vertex[][] graph = CreateGraph.createGraph(matrix, board);

        Vertex vertexHead = graph[head.getX()][head.getY()];
        Vertex vertexApple = graph[apple.getX()][apple.getY()];
        Vertex vertexBadApple = graph[badApple.getX()][badApple.getY()];
        Vertex vertexTail = graph[tail.getX()][tail.getY()];

        DijkstraAlgorithm.dijkstra(vertexHead);

        List<Vertex> shortestPathForApple = DijkstraAlgorithm.getShortestPathTo(vertexApple);
        List<Vertex> shortestPathForBadApple = DijkstraAlgorithm.getShortestPathTo(vertexBadApple);
        List<Vertex> shortestPathForTail = DijkstraAlgorithm.getShortestPathTo(vertexTail);

        Point nextPosForApple = DijkstraAlgorithm.getNextPositionOnPath(head, shortestPathForApple);
        Point nextPosForBadApple = DijkstraAlgorithm.getNextPositionOnPath(head, shortestPathForBadApple);
        Point nextPosForTail = DijkstraAlgorithm.getNextPositionOnPath(head, shortestPathForTail);

        System.out.println(board.toString());

        Point right = head;
        right.change(Direction.RIGHT);
        Point up = head;
        up.change(Direction.UP);
        Point left = head;
        left.change(Direction.LEFT);
        Point down = head;
        down.change(Direction.DOWN);


        if (NextTurn.nextTurn(matrix, shortestPathForApple, board) && board.getSnake().size() >= 30
                && Board.direction(head, nextPosForBadApple) != null) {
            return Board.direction(head, nextPosForBadApple).toString();
        }

        if (Board.direction(head, nextPosForApple) == null && Board.direction(head, nextPosForBadApple)
                == null && Board.direction(head, nextPosForTail) == null) {
            Point newHead = head;
            newHead.change(board.getSnakeDirection());
            if(!board.getWalls().contains(newHead)){
               return board.getSnakeDirection().toString();
            }
            if(board.getWalls().contains(newHead) && board.getWalls().contains(right)){
                return Direction.LEFT.toString();
            }
            if(board.getWalls().contains(newHead) && board.getWalls().contains(up)){
                return Direction.DOWN.toString();
            }
            if(board.getWalls().contains(newHead) && board.getWalls().contains(left)){
                return Direction.RIGHT.toString();
            }
            if(board.getWalls().contains(newHead) && board.getWalls().contains(down)){
                return Direction.UP.toString();
            }
            if(board.getWalls().contains(newHead) && !board.getWalls().contains(left) && !board.getWalls().contains(right)){
                return Direction.RIGHT.toString();
            }
            if(board.getWalls().contains(newHead) && !board.getWalls().contains(up) && !board.getWalls().contains(down)){
                return Direction.UP.toString();
            }
        }

        if(Board.direction(head, nextPosForApple) == null && Board.direction(head, nextPosForTail) == null){
            Board.direction(head, nextPosForBadApple).toString();
        }

        if (Board.direction(head, nextPosForApple) == null) {
            return Board.direction(head, nextPosForTail).toString();
        }

        return Board.direction(head, nextPosForApple).toString();


//        if (Board.direction(head, nextPosForApple) == null && Board.direction(head, nextPosForBadApple)
//                == null && Board.direction(head, nextPosForTail) == null) {
//            return board.getSnakeDirection().toString();
//        }
//
//        if (Board.direction(head, nextPosForApple) == null && board.getSnake().size() > 20
//                && Board.direction(head, nextPosForTail) == null) {
//            return Board.direction(head, nextPosForBadApple).toString();
//        }
//
//        if (Board.direction(head, nextPosForApple) == null) {
//            return Board.direction(head, nextPosForTail).toString();
//        }

    }

    public static void main(String[] args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
                "http://165.22.23.49/codenjoy-contest/board/player/w9mmzve7rsflatf66xmv?code=7190855158362434947",
                new YourSolver(new RandomDice()),
                new Board());
    }

}
