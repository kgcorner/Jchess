package com.kgcorner.chess;

/*
Description : Gives suggestions for movement of any ChessPiece
Author: kumar
Created on : 21/5/19
*/

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;


public abstract class ChessPieceGuide {

    /**
     * Calculates all moves a chess piece can take on given board
     * @param piece chess piece
     * @param board given board
     * @return list of positions given chess piece can move to on given board
     */
    public static List<Position> getNextMoves(ChessPiece piece, Board board) {
        List<Position> movements = null;
        switch (piece.getMovement()) {

            case STRAIGHT_THROUGH:
                movements = getStraightThroughMoves(piece, board);
                break;
            case STRAIGHT_ONCE_KILL_DIA:
                movements = getStraightOnceKillDiaMoves(piece, board);
                break;
            case DIA_THROUGH:
                movements = getDiagThroughMoves(piece, board);
                break;
            case ALL_THROUGH:
                break;
            case ALL_ONCE:
                movements = getAllOnceMoves(piece, board);
                break;
            case L:
                movements = getLMoves(piece, board);
                break;
        }
        return movements;
    }

    /**
     * determine where Next L positions may land on board 
     * @param piece
     * @return
     */
    private static List<Position> getLMoves(ChessPiece piece, Board board) {
        Position position = piece.getPosition();
        List<Position> movements = new ArrayList<Position>();
        PieceColor playersColor = piece.getColor();

        //One vertical + two horizontal +
        movements.add(new Position(position.getX() + 2, position.getY() + 1));
        //One vertical + two horizontal -
        movements.add(new Position(position.getX() - 2, position.getY() + 1));

        //two vertical + one horizontal +
        movements.add(new Position(position.getX() + 1, position.getY() + 2));
        //two vertical + one horizontal -
        movements.add(new Position(position.getX() - 1, position.getY() + 2));

        //One vertical - two horizontal +
        movements.add(new Position(position.getX() + 2, position.getY() - 1));
        //One vertical - two horizontal -
        movements.add(new Position(position.getX() - 2, position.getY() - 1));

        //two vertical - one horizontal +
        movements.add(new Position(position.getX() + 1, position.getY() - 2));
        //two vertical - one horizontal -
        movements.add(new Position(position.getX() - 1, position.getY() - 2));

        //remove invalid positions
        List<Position> validMovements = new ArrayList<Position>();
        for (int i = 0; i < movements.size(); i++) {
            Position p = movements.get(i);
            if(p.getX() > 7 || p.getX() < 0 || p.getY() > 7 || p.getY() < 0) {
                continue;
            }
            ChessPiece encounteredPiece = board.getPieceAt(p);
            if(encounteredPiece != null && encounteredPiece.getColor() == playersColor)
                continue;
            validMovements.add(movements.get(i));
        }

        return validMovements;
    }

    /**
     * determine where Next STRAIGHT_THROUGH positions may land on board 
     * @param piece current piece
     * @return list of position a piece can move
     */
    private static List<Position> getStraightThroughMoves(ChessPiece piece, Board board) {
        Position position = piece.getPosition();
        List<Position> movements = new ArrayList<Position>();
        PieceColor playersColor = piece.getColor();
        int x,y=0;
        //move vertically up
        x = position.getX();
        y = position.getY() + 1;
        while (x >= 0 && x < 7 && y >=0 && y < 7) {
            ChessPiece encounteredPiece = board.getPieceAt(new Position(x, y));
            if(encounteredPiece != null) {
                if(encounteredPiece.getColor() !=  playersColor) {
                    movements.add(new Position(x, y));
                }
                break;
            } else {
                movements.add(new Position(x, y));
                y++;
            }

        }

        //move vertically down
        x = position.getX();
        y = position.getY() - 1;
        while (x >= 0 && x < 7 && y >= 0 && y < 7) {
            ChessPiece encounteredPiece = board.getPieceAt(new Position(x, y));
            if(encounteredPiece != null) {
                if(encounteredPiece.getColor() !=  playersColor) {
                    movements.add(new Position(x, y));
                }
                break;
            } else {
                movements.add(new Position(x, y));
                y--;
            }
        }

        //move horizontally up
        x = position.getX() + 1;
        y = position.getY();
        while (x >= 0 && x < 7 && y >= 0 && y < 7) {
            ChessPiece encounteredPiece = board.getPieceAt(new Position(x, y));
            if(encounteredPiece != null) {
                if(encounteredPiece.getColor() !=  playersColor) {
                    movements.add(new Position(x, y));
                }
                break;
            } else {
                movements.add(new Position(x, y));
                x++;
            }
        }

        //move horizontally down
        x = position.getX() - 1;
        y = position.getY();
        while (x >= 0 && x < 7 && y >= 0 && y < 7) {
            ChessPiece encounteredPiece = board.getPieceAt(new Position(x, y));
            if(encounteredPiece != null) {
                if(encounteredPiece.getColor() !=  playersColor) {
                    movements.add(new Position(x, y));
                }
                break;
            } else {
                movements.add(new Position(x, y));
                x--;
            }
        }
        return movements;
    }

    /**
     * determine where Next STRAIGHT_THROUGH positions may land on board 
     * @param piece current piece
     * @return list of position a piece can move
     */
    private static List<Position> getDiagThroughMoves(ChessPiece piece, Board board) {
        Position position = piece.getPosition();
        List<Position> movements = new ArrayList<Position>();
        PieceColor playersColor = piece.getColor();
        int x,y=0;
        //move up
        x = position.getX() + 1;
        y = position.getY() + 1;
        while (x >= 0 && x < 7 && y >= 0 && y < 7) {
            ChessPiece encounteredPiece = board.getPieceAt(new Position(x, y));
            if(encounteredPiece != null) {

                if(encounteredPiece.getColor() !=  playersColor) {
                    movements.add(new Position(x, y));
                }
                break;
            } else {
                movements.add(new Position(x, y));
                y++;
                x++;
            }
        }

        //move down
        x = position.getX() -1;
        y = position.getY() - 1;
        while (x >= 0 && x < 7 && y >= 0 && y < 7) {
            ChessPiece encounteredPiece = board.getPieceAt(new Position(x, y));
            if(encounteredPiece != null) {

                if(encounteredPiece.getColor() !=  playersColor) {
                    movements.add(new Position(x, y));
                }
                break;
            } else {
                movements.add(new Position(x, y));
                y--;
                x--;
            }
        }
        return movements;
    }

    /**
     * determine where Next STRAIGHT_ONCE_KILL_DIA positions may land on board
     * @param piece current piece
     * @param board instance of board
     * @return list of positions
     */
    private static List<Position> getStraightOnceKillDiaMoves(ChessPiece piece, Board board) {
        Position position = piece.getPosition();
        PieceColor playersColor = piece.getColor();
        List<Position> movements = getAllOnceMoves(piece, board);
        // for white remove moves which has horizontally ahead than position
        // and for black remove which are horizontally behind
        // and vertical deviation only when opposition's piece is there at that position
        List<Position> validPositions = new ArrayList<Position>();
        for (int i = 0; i < movements.size(); i++) {
            Position pos = movements.get(i);
            ChessPiece encounteredPiece = board.getPieceAt(pos);
            if(playersColor == PieceColor.WHITE) {
                if(pos.getX() > position.getX()) {
                    if(pos.getY() != position.getY()) {
                        if(encounteredPiece != null && encounteredPiece.getColor() != playersColor) {
                            validPositions.add(pos);
                        }
                    } else
                        validPositions.add(pos);
                }
            } else {
                if(pos.getX() < position.getX()) {
                    if(pos.getY() != position.getY()) {
                        if(encounteredPiece != null && encounteredPiece.getColor() != playersColor) {
                            validPositions.add(pos);
                        }
                    } else
                        validPositions.add(pos);
                }
            }
        }



        return validPositions;
    }
    /**
     * determine where Next ALL_ONCE positions may land on board
     * @param piece current piece
     * @return list of position a piece can move
     */
    private static List<Position> getAllOnceMoves(ChessPiece piece, Board board) {
        Position position = piece.getPosition();
        List<Position> movements = new ArrayList<Position>();
        PieceColor playersColor = piece.getColor();
        int x,y=0;
        //move up
        x = position.getX() + 1;
        y = position.getY() + 1;
        ChessPiece encounteredPiece = board.getPieceAt(new Position(x, y));
        if(encounteredPiece == null || encounteredPiece.getColor() != playersColor)
            movements.add(new Position(x, y));

        //move down
        x = position.getX() - 1;
        y = position.getY() - 1;
        if(x >= 0 && y >= 0) {
            encounteredPiece = board.getPieceAt(new Position(x, y));
            if (encounteredPiece == null || encounteredPiece.getColor() != playersColor)
                movements.add(new Position(x, y));
        }

        //move horizontally up
        x = position.getX() + 1;
        y = position.getY();
        encounteredPiece = board.getPieceAt(new Position(x, y));
        if(encounteredPiece == null || encounteredPiece.getColor() != playersColor)
            movements.add(new Position(x, y));

        //move vertically up
        x = position.getX();
        y = position.getY() + 1 ;
        encounteredPiece = board.getPieceAt(new Position(x, y));
        if(encounteredPiece == null || encounteredPiece.getColor() != playersColor)
            movements.add(new Position(x, y));

        //move horizontally down
        x = position.getX() - 1;
        y = position.getY();
        if(x >= 0) {
            encounteredPiece = board.getPieceAt(new Position(x, y));
            if (encounteredPiece == null || encounteredPiece.getColor() != playersColor)
                movements.add(new Position(x, y));
        }

        //move vertically up
        x = position.getX();
        y = position.getY() - 1 ;
        if(y >= 0) {
            encounteredPiece = board.getPieceAt(new Position(x, y));
            if (encounteredPiece == null || encounteredPiece.getColor() != playersColor)
                movements.add(new Position(x, y));
        }
        return movements;
    }

    /**
     * determine where Next ALL_ONCE positions may land on board 
     * can be used for move STRAIGHT_ONCE_KILL_DIA as well with some cancellation of backward moves
     * @param piece current position
     * @return list of position a piece can move
     */
    private static List<Position> getAllThroughMoves(ChessPiece piece, Board board) {
        List<Position> movements = new ArrayList<Position>();
        movements.addAll(getDiagThroughMoves(piece, board));
        movements.addAll(getStraightThroughMoves(piece, board));
        return movements;
    }
}