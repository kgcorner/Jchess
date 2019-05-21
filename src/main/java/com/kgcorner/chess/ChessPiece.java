package com.kgcorner.chess;

/*
Description : Represents a chess piece
Author: kumar
Created on : 21/5/19
*/

import java.util.List;

public class ChessPiece {
    private final ChessPieceType type;
    private final Movement movement;
    private Position position;
    private final PieceColor color;


    private ChessPiece(ChessPieceType type, Movement movement, PieceColor color) {
        this.type = type;
        this.movement = movement;
        this.color = color;
        position = new Position(0, 0);
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public ChessPieceType getType() {
        return type;
    }

    public Movement getMovement() {
        return movement;
    }

    public PieceColor getColor() {
        return color;
    }

    /**
     * Creates a king
     * @return
     */
    public static ChessPiece createKing(PieceColor color) {
        return new ChessPiece(ChessPieceType.KING, Movement.ALL_ONCE, color);
    }

    /**
     * Creates a queen
     * @return
     */
    public static  ChessPiece createQueen(PieceColor color) {
        return new ChessPiece(ChessPieceType.QUEEN, Movement.ALL_THROUGH, color);
    }
    /**
     * Creates a rook
     * @return
     */
    public static  ChessPiece createRook(PieceColor color) {
        return new ChessPiece(ChessPieceType.ROOK, Movement.STRAIGHT_THROUGH, color);
    }
    /**
     * Creates a bishop
     * @return
     */
    public static  ChessPiece createBishop(PieceColor color) {
        return new ChessPiece(ChessPieceType.BISHOP, Movement.DIA_THROUGH, color);
    }
    /**
     * Creates a knight
     * @return
     */
    public static  ChessPiece createKnight(PieceColor color) {
        return new ChessPiece(ChessPieceType.KNIGHT, Movement.L, color);
    }
    /**
     * Creates a pawn
     * @return
     */
    public static  ChessPiece createPawn(PieceColor color) {
        return new ChessPiece(ChessPieceType.PAWN, Movement.STRAIGHT_ONCE_KILL_DIA, color);
    }
}