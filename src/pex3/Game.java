/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pex3;

import java.security.SecureRandom;

/**
 *
 * @author Jared
 */
public class Game {

    //Game function that contains a board and a score.
    private Board gameBoard;
    private int score;
    private SecureRandom rd;

    public Game() {
        gameBoard = new Board();
        score = 0;
        rd = new SecureRandom();
    }

    public Game(Board gameBoard) {
        this.gameBoard = gameBoard;

    }

    //The check win condition, checks every tile to see if one is 2048 equivalent.
    public boolean checkWin() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameBoard.currentBoard.get(i).get(j).getValue() == 11) {
                    return true;
                }
            }
        }
        return false;
    }

    //Check lose condition using the up,down,left,right variables.
    public boolean checkLose(int a, int b, int c, int d) {
        return a + b + c + d == 4;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int additional) {
        score += additional;
    }


    //Puts a random tile in an empty spot, returns null if there are no empty spots.
    public Tile randTile() {

        int loopNum = 0;
        while (true) {
            outLoop:
            if (loopNum > 50) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (gameBoard.getCurrentBoard().get(i).get(j).getValue() == 0) {
                            loopNum = 0;
                            break outLoop;
                        }
                        return null;
                    }
                }
            }
            int x = rd.nextInt(4);

            int y = rd.nextInt(4);

            if (gameBoard.getCurrentBoard().get(x).get(y).getValue() == 0) {
                gameBoard.getCurrentBoard().get(x).get(y).setValue(rd.nextInt(2) + 1);

                return gameBoard.getCurrentBoard().get(x).get(y);
            }
            loopNum++;

        }
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

}
