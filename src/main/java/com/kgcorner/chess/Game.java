package com.kgcorner.chess;

/*
Description : <Write is class Description>
Author: kumar
Created on : 21/5/19
*/

public class Game {
    public static void main(String[] args) {
        Board board = new Board();
        board.initialize();
        board.printBoard();
        GameGuide.startGame(board);
    }
}