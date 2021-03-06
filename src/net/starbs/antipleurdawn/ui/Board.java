package net.starbs.antipleurdawn.ui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import net.starbs.antipleurdawn.Piece;
import net.starbs.antipleurdawn.events.MoveChosenEvent;
import net.starbs.antipleurdawn.events.MoveChosenEventListener;

import java.util.ArrayList;

public class Board extends GridPane
{
    private Square[][] squares = new Square[8][8];

    private Square selected = null;

    private ArrayList<MoveChosenEventListener> listeners = new ArrayList<MoveChosenEventListener>();

    public Board()
    {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square sq = new Square(row, col);
                add(sq, col, row);  // uses XY

                sq.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event){
                        onSquareClicked(sq);
                    }
                });

                squares[row][col] = sq;
            }
        }
    }

    public void addMoveChosenEventListener(MoveChosenEventListener listener)
    {
        listeners.add(listener);
    }

    private void fireMoveChosenEvent(MoveChosenEvent event)
    {
        for (MoveChosenEventListener listener : listeners) {
            listener.moveChosenEventOccurred(event);
        }
    }

    public void displayData(Piece[][] data)
    {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col].setPiece(data[row][col]);
            }
        }
    }

    public void onSquareClicked(Square sq)
    {
        if (selected == null){
            sq.select();
            selected = sq;
        } else if(selected == sq){
            sq.deselect();
            selected = null;
        } else{
            int[] from = {selected.getRow(), selected.getCol()};
            int[] to = {sq.getRow(), sq.getCol()};
            fireMoveChosenEvent(new MoveChosenEvent(this, from, to));
            selected.deselect();
            selected = null;
        }
    }
}
