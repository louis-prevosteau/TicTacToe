package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TicTacToe extends Application {
    private char currentPlayer = 'X';
    private Cell[][] tab = new Cell[3][3];

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();

        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                tab[i][j] = new Cell();
                pane.add(tab[i][j], j, i);
            }
        }

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);

        Scene scene = new Scene(borderPane, 450, 300);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public boolean fullTab(){
        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                if (tab[i][j].getPlayer() == ' '){
                    return false;
                }
            }
        }

        return true;
    }

    public boolean hasWon(char player){
        for (int i = 0 ; i < 3 ; i++){
            if (tab[i][0].getPlayer() == player && tab[i][1].getPlayer() == player && tab[i][2].getPlayer() == player){
                return true;
            }
        }

        for (int i = 0 ; i < 3 ; i++){
            if (tab[0][i].getPlayer() == player && tab[1][i].getPlayer() == player && tab[2][i].getPlayer() == player){
                return true;
            }
        }

        if (tab[0][0].getPlayer() == player && tab[1][1].getPlayer() == player && tab[2][2].getPlayer() == player){
            return true;
        }

        if (tab[0][2].getPlayer() == player && tab[1][1].getPlayer() == player && tab[2][0].getPlayer() == player){
            return true;
        }

        return false;
    }

    public class Cell extends Pane{
        private char player = ' ';

        public Cell() {
            setStyle("fx-border-color : yellow");
            this.setPrefSize(300, 300);
            this.setOnMouseClicked(event -> handleClicked());
        }

        private void handleClicked(){
            if (player == ' ' && currentPlayer != ' '){
                setPlayer(currentPlayer);
            }
            else if (hasWon(currentPlayer)){
                Label wonMsg = new Label("you win !!!");
            }
            else if (fullTab()) {
                Label DrawMsg = new Label("Draw");
                currentPlayer = ' ';
            }
        }

        public char getPlayer() {
            return player;
        }

        public void setPlayer(char p) {
            player = p;

            if (player == 'X'){
                Line l1 = new Line(10, this.getHeight() - 10, this.getWidth() - 10, 10);
                l1.endXProperty().bind(this.widthProperty().subtract(10));
                l1.endYProperty().bind(this.heightProperty().subtract(10));

                Line l2 = new Line(10, 10, this.getWidth() - 10, this.getHeight() - 10);
                l2.endXProperty().bind(this.widthProperty().subtract(10));
                l2.startYProperty().bind(this.heightProperty().subtract(10));

                getChildren().addAll(l1,l2);
            }
            else if (player == 'O'){
                Ellipse e = new Ellipse(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2 - 10, this.getHeight() / 2 - 10);
                e.centerXProperty().bind(this.widthProperty().divide(2));
                e.centerYProperty().bind(this.heightProperty().divide(2));
                e.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
                e.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
                e.setStroke(Color.YELLOW);
                e.setFill(Color.YELLOW);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
