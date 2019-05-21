package com.kgcorner.chess;

/*
Description : <Write is class Description>
Author: kumar
Created on : 21/5/19
*/

import javafx.geometry.Pos;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final ChessPiece[][] board = new ChessPiece[8][8];
    private final List<ChessPiece> killedPieces = new ArrayList<ChessPiece>();

    public void initialize() {
        setPieces(PieceColor.WHITE);
        setPieces(PieceColor.BLACK);
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[j][i] != null) {
                    ChessPiece piece = board[j][i];
                    System.out.print(piece.getColor().toString()+"_"+piece.getType().toString().substring(0,1)+"    ");
                } else {
                    System.out.print("     *     ");
                }
            }
            System.out.println("");
        }

        System.out.println("************************** Killed Pieces ************************** ");
        for (ChessPiece piece : killedPieces) {
            System.out.print(piece.getColor().toString()+"_"+piece.getType().toString().substring(0,1)+"    ");
        }
        System.out.println("");
    }

    private void setPieces(PieceColor color) {
        ChessPiece rook1 = ChessPiece.createRook(color);
        ChessPiece rook2 = ChessPiece.createRook(color);

        ChessPiece knight1 = ChessPiece.createKnight(color);
        ChessPiece knight2 = ChessPiece.createKnight(color);

        ChessPiece bishop1 = ChessPiece.createBishop(color);
        ChessPiece bishop2 = ChessPiece.createBishop(color);

        ChessPiece king = ChessPiece.createKing(color);
        ChessPiece queen = ChessPiece.createQueen(color);
        if(color == PieceColor.WHITE) {
            board[0][0] = rook1;
            rook1.setPosition(new Position(0,0));
            board[0][7] = rook2;
            rook2.setPosition(new Position(0,7));

            board[0][1] = knight1;
            knight1.setPosition(new Position(0,1));
            board[0][6] = knight2;
            knight2.setPosition(new Position(0,6));

            board[0][2] = bishop1;
            bishop1.setPosition(new Position(0,2));
            board[0][5] = bishop2;
            bishop2.setPosition(new Position(0,5));

            board[0][3] = king;
            king.setPosition(new Position(0,3));
            board[0][4] = queen;
            queen.setPosition(new Position(0,4));
        } else {
            board[7][0] = rook1;
            rook1.setPosition(new Position(7,0));
            board[7][7] = rook2;
            rook2.setPosition(new Position(7,7));

            board[7][1] = knight1;
            knight1.setPosition(new Position(7,1));
            board[7][6] = knight2;
            knight2.setPosition(new Position(7,6));

            board[7][2] = bishop1;
            bishop1.setPosition(new Position(7,2));
            board[7][5] = bishop2;
            bishop2.setPosition(new Position(7,5));

            board[7][4] = king;
            king.setPosition(new Position(7,4));
            board[7][3] = queen;
            queen.setPosition(new Position(7,3));
        }

        for (int i = 0; i < 8; i++) {
            ChessPiece pawn = ChessPiece.createPawn(color);
            if(color == PieceColor.WHITE) {
                board[1][i] = pawn;
                pawn.setPosition(new Position(1, i));
            } else {
                board[6][i] = pawn;
                pawn.setPosition(new Position(6, i));
            }
        }
    }

    public ChessPiece getPieceAt(Position position) {
        return board[position.getX()][position.getY()];
    }

    public void movePiece(ChessPiece piece, Position position) {
        board[piece.getPosition().getX()][piece.getPosition().getY()] = null;
        board[position.getX()][position.getY()] = piece;
        piece.setPosition(position);
    }

    public void addToKilledPieces(ChessPiece piece) {
        killedPieces.add(piece);
    }

}