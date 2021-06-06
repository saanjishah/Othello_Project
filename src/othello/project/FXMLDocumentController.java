/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.project;
import javafx.scene.image.Image;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Saanji Shah
 */
public class FXMLDocumentController implements Initializable {
    Button[][] btn = new Button[8][8];
    ImageView[][] img = new ImageView[8][8];
    private int [][] board = new int[8][8];
    private int [][] moves = new int[8][8];
    private int x, y, turn = 0,compRow, compCol;
    private boolean valid=false, win = false, show=false;
    private Search searchClass;
    private Computer compClass;
    @FXML
    private Label bCount, wCount;
    @FXML
    private GridPane gPane;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        searchClass = new Search(board);
        start();
        possMoves();
        compClass = new Computer();
        bCount.setText((Integer.toString(searchClass.getBlack())));
        wCount.setText((Integer.toString(searchClass.getWhite()))); 
        EventHandler z = new EventHandler<ActionEvent>() 
        {
            
            @Override
            public void handle(ActionEvent t) 
            {
                valid = false;
                x = GridPane.getRowIndex(((Button) t.getSource()));
                y = GridPane.getColumnIndex(((Button) t.getSource()));
                if(moves[x][y]==(turn%2)){
                    valid=true;
                    btn[x][y].setDisable(true);
                }
                if(valid==true){
                    searchClass.Directions(x, y, turn, true);
                    board = searchClass.getGrid();
                    if(turn%2==0){
                        board[x][y]=0;//0 means user's piece
                        turn++;
                    }else if(turn%2!=0){
                        board[x][y]=1;
                        turn++;
                    }
                    
                    
                }else{
                    System.out.println("INVALID");
                }
                
                System.out.println("");
                   
//                turn++;
                compClass.makeMove(moves);
                compRow = compClass.getRow();
                compCol = compClass.getCol();
                searchClass.Directions(compRow, compCol, turn, true);
                board[compRow][compCol]=1;
                turn++;
                setPics();
                bCount.setText((Integer.toString(searchClass.getBlack())));
                wCount.setText((Integer.toString(searchClass.getWhite())));  
                win = checkWin();
                for(int k=0;k< moves.length;k++){
                    for(int h=0; h<moves.length;h++){
                        moves[k][h]=2;//2 means empty
                    }
                }
                possMoves();
                System.out.println("***********************BOARD***********************");
                printGrid(board);
            }
        };
        if(win==false){
            for(int i=0; i<btn.length; i++){
                for(int j=0; j<btn.length;j++){
                    btn[i][j].setOnAction(z);
                }
            }
        }
        //FIX-----------------------------------------------------------------------------
//           while(turn%2!=0){
//                int compX = (int) Math.floor(Math.random()*7)+1;
//                int compY = (int) Math.floor(Math.random()*7)+1;
//                if(board[compX][compY]==2){
//                    board[compX][compY] =1;
//                    System.out.println("X: " + compX + " Y: " + compY);
//                    setPics();
//                    turn++;
//                }
//            } 
    }
    @FXML
    private void showTut(){
        show=true;
        possMoves();
    }
    private void printGrid(int[][] grid){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println("");
        }
        System.out.println("--------------------------------------------------------");
    }
    private void possMoves(){
        for(int i=0;i<moves.length;i++){
            for(int j = 0; j<moves.length;j++){
                if(searchClass.Directions(i, j, 0, false)==true && board[i][j]==2){
                    moves[i][j]=0;
                }else if(searchClass.Directions(i, j, 1, false)==true && board[i][j]==2){
                    moves[i][j]=1;
                }else{
                    moves[i][j]=2;
                }
                if(show==true){
                    btn[i][j].setText(Integer.toString(moves[i][j]));
                }else{
                    btn[i][j].setText("2");
                }
            }
        }
        System.out.println("***********************MOVES***********************");
        printGrid(moves);
    }
    @FXML
    private void start(){
        gPane.setGridLinesVisible(false);
        gPane.setVisible(false);
        turn = 0;
        win=false;
        for(int k=0;k< board.length;k++){
            for(int h=0; h<board.length;h++){
                board[k][h]=2;//2 means empty
            }
        }
        for(int i=0; i<btn.length; i++){
            for(int j=0; j<btn.length;j++){
                img[i][j] = new ImageView();
                btn[i][j] = new Button(Integer.toString(moves[i][j]));//a new object of a button is created---button objects that take the form of a button but don't do anything here
                btn[i][j].setPrefSize(60, 60);
                btn[i][j].setStyle("-fx-background-color: #00cc00");
                if((i==3 && j==4)||(i==4 && j==3)){
                    board[i][j]=1;//1 is computer's piece
                    btn[x][y].setDisable(true);
                }else if((i==3 && j==3)||(i==4 && j==4)){
                    board[i][j]=0;
                    btn[x][y].setDisable(true);
                }
                gPane.add(btn[i][j], j, i);
                gPane.add(img[i][j], j, i);
            }
        }
        setPics();
        gPane.setGridLinesVisible(true);
        gPane.setVisible(true);
        printGrid(board);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    private boolean checkWin(){
        for(int i = 0; i<board.length;i++){
            for(int j = 0; j<board.length;j++){
                if(board[i][j]==2){
                    return false;
                }
            }
        }
        if((searchClass.getBlack())< (searchClass.getWhite()) || (searchClass.getBlack()) > (searchClass.getWhite())){
            System.out.println("GAME OVER!");
        }
        return true;
    }
    private void setPics(){
        for(int i = 0; i<board.length;i++){
           for(int j = 0; j<board.length;j++){
               if(board[i][j]==0){
                   img[i][j].setImage(new Image("resources/Black-Circle.png"));
                   img[i][j].setFitHeight(45);
                   img[i][j].setFitWidth(60);
               }else if(board[i][j]==1){
                   img[i][j].setImage(new Image("resources/White-Circle.png"));
                   img[i][j].setFitHeight(45);
                   img[i][j].setFitWidth(60);
               }
           } 
        }
    }
}
