package net.starbs.antipleurdawn.ui;

import net.starbs.antipleurdawn.*;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;

import java.util.Stack;

/**
 * Created by Hickman on 02/06/2016.
 */
public class Square extends StackPane {
    public Piece piece;

    public Square(int x, int y){

        String color;

        if ((x + y) % 2 == 0) {
            color = "white";
        } else {
            color = "grey";
        }
        getStyleClass().add("square");
        setStyle("-fx-background-color: " + color + ";");
        getChildren().add(new Group());
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        getChildren().set(0, piece.getImage());
    }

    public void select(){
        getStyleClass().add("selected");
    }

    public void deselect() {
        getStyleClass().remove("selected");
    }
}
