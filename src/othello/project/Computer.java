/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.project;

/**
 *
 * @author shah1932
 */
public class Computer {
    private int row, column;
    private boolean works=false;
    public void makeMove(int[][] possibleMoves){
        works = false;
        while(works==false){
            row = (int) Math.floor(Math.random()*possibleMoves.length);
            column = (int) Math.floor(Math.random()*possibleMoves.length);
            if(possibleMoves[row][column]==1){
                System.out.println("ROW: " + row);
                System.out.println("COLUMN: " + column);
                works=true;
            }
        }
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return column;
    }
}
