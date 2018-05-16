/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pex3;

import java.util.ArrayList;

/**
 *
 * @author Jared
 */
public class Board {

    //2d array of tiles
    ArrayList<ArrayList<Tile>> currentBoard;

    public Board() {
        currentBoard = new ArrayList<>();
        initializeBoardArray();
    }

    public Board(ArrayList<ArrayList<Tile>> currentBoard) {
        this.currentBoard = currentBoard;
    }

    public ArrayList<ArrayList<Tile>> getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(ArrayList<ArrayList<Tile>> currentBoard) {
        this.currentBoard = currentBoard;
    }

    //Initializes the board with empty tiles.
    public void initializeBoardArray() {
        for (int i = 0; i < 4; i++) {
            ArrayList<Tile> emptyRow = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                Tile b = new Tile();
                b.setX(i);
                b.setY(j);
                emptyRow.add(b);
            }
            currentBoard.add(emptyRow);
        }
    }
    
    
    //Sets all tiles to not just made.
    public void oldify(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                currentBoard.get(i).get(j).setJustMade(false);
            }
            
        }
    }
}
