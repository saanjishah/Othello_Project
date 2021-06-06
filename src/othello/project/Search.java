/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.project;

import javafx.fxml.FXML;
import javafx.scene.image.Image;

/**
 *
 * @author shah1932
 */
public class Search {
    private int MPRX, MPCY, picx, picy, countW = 0, countB = 0;//X is row Y is Column
    private int [][] grid = new int[8][8];
    private boolean spotWorks = false;
    public Search(int[][] board){
        grid = board;
    }
    public boolean Directions(int SRX, int SCY, int pTurn, boolean change){
        spotWorks = false;
        MPRX = 8;
        MPCY = 8;
        //LEFT
        for(int i = SCY-1; i>0;i--){
            if(grid[SRX][i]==(pTurn%2) && grid[SRX][i+1]!=2){
                MPRX = SRX;
                MPCY = i;
                if(change==true){
                    for(int j = MPCY; j<SCY;j++){
                        grid[MPRX][j] = (pTurn%2);
                    }
                }
                spotWorks=true;
                break;
            }
            else if(grid[SRX][i]>1){
                MPCY = SCY;
                MPRX = SRX;
                break;
            }
        }
        MPRX = 8;
        MPCY = 8;
        //RIGHT
        for(int i = SCY+1; i< MPCY;i++){
            if(grid[SRX][i]==(pTurn%2) && grid[SRX][i-1]!=2){
                MPRX = SRX;
                MPCY = i;
                if(change==true){
                    for(int j = SCY; j<MPCY;j++){
                        grid[MPRX][j] = (pTurn%2);
                    }
                }
                spotWorks=true;
                break;
            }
            else if(grid[SRX][i]>1){
                MPCY = SCY;
                MPRX = SRX;
                break;
            }
        }
        MPRX = 8;
        MPCY = 8;
        //TOP
        for(int i = SRX-1; i>0;i--){
            if(grid[i][SCY]==(pTurn%2)&& grid[i+1][SCY]!=2){
                MPRX = i;
                MPCY = SCY;
                if(change==true){
                    for(int j = SRX; j>MPRX;j--){
                        grid[j][MPCY] = (pTurn%2);
                    }
                }
                spotWorks=true;
                break;
            }
            else if(grid[i][SCY]>1){
                MPCY = SCY;
                MPRX = SRX;
                break;
            }
        }
        MPRX = 8;
        MPCY = 8;
        //DOWN
        for(int i = SRX+1; i< MPRX;i++){
            if(grid[i][SCY]==(pTurn%2) && grid[i-1][SCY]!=2){
                MPRX = i;
                MPCY = SCY;
                if(change==true){
                    for(int j = SRX; j<MPRX;j++){
                        grid[j][MPCY] = (pTurn%2);
                    }
                }
                spotWorks=true;
                break;
            }
            else if(grid[i][SCY]>1){
                MPCY = SCY;
                MPRX = SRX;
                break;
            }
        }
        MPRX = 8;
        MPCY = 8;
        int x = SRX +1;
        int y = SCY +1;
        boolean match = false;
        //DIAGONAL INCREASE BOTH
        while(x<MPRX && y<MPCY){
            if(grid[x][y]==(pTurn%2) && grid[x-1][y-1]!=2){
                MPCY = y;
                MPRX = x;
                match = true;
                spotWorks=true;
                picx = SRX;
                picy = SCY;
                if(change==true){
                    while(picx<MPRX && picy<MPCY && match==true){
                        grid[picx][picy] = (pTurn%2);
                        picx++;
                        picy++;
                    }
                }
            }
            x++;
            y++;
        }
        
        MPRX = 8;
        MPCY = 8;
        x = SRX-1;
        y = SCY-1;
        match = false;
//        DIAGONAL DECREASE BOTH
        while(x>0 && y>0){
            if(grid[x][y]==(pTurn%2) && grid[x+1][y+1]!=2){
                MPCY = y;
                MPRX = x;
                spotWorks=true;
                match = true;
                picx = SRX;
                picy = SCY;
                if(change==true){
                    while(picx>MPRX && picy>MPCY && match==true){
                        grid[picx][picy] = (pTurn%2);
                        picx--;
                        picy--;
                    }
                }
            }
            x--;
            y--;
        }
        //Subtract C Increase R
        MPRX = 8;
        MPCY = 8;
        x = SRX +1;
        y = SCY -1;
        match = false;
        while(x<MPRX && y>0){
            if(grid[x][y]==(pTurn%2) && grid[x-1][y+1]!=2){
                MPCY = y;
                MPRX = x;
                match = true;
                spotWorks=true;
                picx = SRX;
                picy = SCY;
                if(change==true){
                    while(picx<MPRX && picy>MPCY && match==true){
                        grid[picx][picy] = (pTurn%2);
                        picx++;
                        picy--;
                    }
                }
            }
            x++;
            y--;
        }
        //Subtract R Increase C
        MPRX = 8;
        MPCY = 8;
        x = SRX -1;
        y = SCY +1;
        match = false;
        while(x>0 && y<MPCY){
            if(grid[x][y]==(pTurn%2) && grid[x+1][y-1]!=2){
                MPCY = y;
                MPRX = x;
                match = true;
                spotWorks=true;
                picx = SRX;
                picy = SCY;
                if(change==true){
                    while(picx>MPRX && picy<MPCY && match==true){
                        grid[picx][picy] = (pTurn%2);
                        picx--;
                        picy++;
                    }
                }
            }
            x--;
            y++;
        }
        return spotWorks;
    }
    
    public int[][] getGrid(){
        return grid;
    }
    public int getWhite(){
        countW = 0;
        for(int i = 0; i<grid.length;i++){
            for(int j=0;j<grid.length;j++){
                if(grid[i][j]==1){
                    countW++;
                }
            }
        }
        return countW;
    }
    public int getBlack(){
        countB = 0;
        for(int i = 0; i<grid.length;i++){
            for(int j=0;j<grid.length;j++){
                if(grid[i][j]==0){
                    countB++;
                }
            }
        }
        return countB;
    }
}
