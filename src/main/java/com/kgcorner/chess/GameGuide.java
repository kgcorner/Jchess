package com.kgcorner.chess;

/*
Description : <Write is class Description>
Author: kumar
Created on : 21/5/19
*/

import java.util.List;
import java.util.Scanner;

public abstract class GameGuide {
    private static boolean whiteToPlay = true;
    private static boolean gameFinished = false;

    public static PieceColor getNextPlayer() {
        return whiteToPlay ? PieceColor.WHITE : PieceColor.BLACK;
    }


    public static void startGame(Board board) {
        do {
            System.out.println(GameGuide.getNextPlayer()+" to play");
            System.out.println("Select piece to move (enter x,y): ");
            Scanner scanner = new Scanner(System.in);
            String positionStr = scanner.next();
            try {
                Position position = getPosition(positionStr);

                PieceColor currentPlayerColor = whiteToPlay ? PieceColor.WHITE : PieceColor.BLACK;
                ChessPiece currentPiece = validateMovePositionAndGetPiece(position, board, currentPlayerColor);
                if (currentPiece == null)
                    continue;

                List<Position> moves = getMovesForPiece(currentPiece, board);

                System.out.println("possible moves are at ");
                printMoves(board, moves);
                if(moves.isEmpty())
                    continue;
                System.out.println("Enter 1 to move suffixed by position to move ");
                System.out.println("Enter 2 for another piece");

                int operation = scanner.nextInt();
                if(operation == 2) {
                    continue;
                } else {
                    if(operation == 1) {
                        positionStr = scanner.next();
                        Position positionToMove = getPosition(positionStr);
                        movePiece(board, positionToMove, currentPiece);
                    } else {
                        System.out.println("Enter correct option");
                        continue;
                    }
                }
            } catch (IllegalArgumentException x) {
                System.out.println(x.getMessage());
                continue;
            }
        } while(!gameFinished);
        System.out.println("************************** Game Ends **************************");
    }

    /**
     * Moves given piece to given position on the given board
     * @param board instance of current board
     * @param positionToMove position to which given piece will be moved
     * @param currentPiece given piece
     */
    private static void movePiece(Board board, Position positionToMove, ChessPiece currentPiece) {
        ChessPiece encounteredPiece = board.getPieceAt(positionToMove);
        if(encounteredPiece != null) {
            if (encounteredPiece.getColor() != currentPiece.getColor()) {
                System.out.println(encounteredPiece.getType()+" is killed");
                if(encounteredPiece.getType() == ChessPieceType.KING) {
                    System.out.println(currentPiece.getColor()+" won");
                    gameFinished = true;
                }
                board.addToKilledPieces(encounteredPiece);
            } else {
                throw new IllegalArgumentException("invalid move");
            }
        }
        board.movePiece(currentPiece, positionToMove);
        whiteToPlay = whiteToPlay ? false : true;
        board.printBoard();
    }

    /**
     * Prints moves on console with information whether move kills opposition's piece
     * @param board instance of the board
     * @param moves list of moves current player can make
     */
    private static void printMoves(Board board, List<Position> moves) {
        if(moves.isEmpty()) {
            System.out.println("You can't move this piece");
            return;
        }
        for(Position move : moves) {
            System.out.print(move.getX()+ ","+ move.getY());
            ChessPiece piece = board.getPieceAt(move);
            if( piece != null) {
                if(piece.getType() == ChessPieceType.KING) {
                    System.out.print(" ----- Its Check ");
                }

            }
            System.out.println("");
        }
    }

    /**
     * Validates if the given position is movable for current player or not
     * @param position
     * @param board
     * @return @ChessPiece at given position if piece is available on position and
     * current player is allowed to play that piece
     */
    private static ChessPiece validateMovePositionAndGetPiece(Position position,
                                                              Board board, PieceColor currentPlayerColor) {
        ChessPiece currentPiece = board.getPieceAt(position);
        if(currentPiece == null) {
            System.out.println("No piece found");
            return null;
        }
        if(currentPlayerColor != currentPiece.getColor()) {
            System.out.println("You can't move opposition's piece");
            return null;
        }
        return currentPiece;
    }

    /**
     * Parses given input string to create position it refers on the board
     * @param positionStr position in string format
     * @return Position instance
     */
    private static Position getPosition(String positionStr) {
        if(!positionStr.contains(",")) {
            throw new IllegalArgumentException("Please enter position as x,y from 0-7,0-7");
        }
        String[] xy = positionStr.split(",");
        try {
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            if(x < 0 || x> 7 || y< 0 || y > 7) {
                throw new IllegalArgumentException("Position has to be on board so rang should be from 0 to 7");
            }
            return new Position(x, y);
        } catch (NumberFormatException x) {
            throw new IllegalArgumentException("Position must have numbers");
        }
    }

    /**
     * calculates what all positions a piece can move on the board
     * @param piece piece to move
     * @param board board
     * @return possible moves
     */
    private static List<Position> getMovesForPiece(ChessPiece piece, Board board) {
        return ChessPieceGuide.getNextMoves(piece, board);
    }
}