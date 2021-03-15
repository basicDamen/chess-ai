/*
    Group Names:
        - Damen DeBerry (@basicDamen)
        - James Grady (@JaymeAlann)
        - Tyra Buadoo (@misstj555)
        - Ashlei Williams (@AshW-2018)
        - Mahad Farah (@mfarah-ksu)
        - Mandela Issa-Boube (@aliamaza)
        - Shivank Rao (@shivankrao)
    Project: Chess with AI Agent
    Class: CS4850 - Senior Project
 */
package chess.game;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board
{

    private final Tile[][] tile = new Tile[8][8];
    private final List<Piece> whiteTeam;
    private final List<Piece> blackTeam;
    public ArrayList<Tile> chessBoard;
    private static String top = "black";
    private static String bottom = "white";

    private Board(SetBoard setter){
        this.chessBoard = createBoard(setter);
        this.whiteTeam = getRemainingTeam(this.chessBoard, "white");
        this.blackTeam = getRemainingTeam(this.chessBoard, "black");
    }

    public List<Piece> getBlackPieces() {
        return this.blackTeam;
    }

    public List<Piece> getWhitePieces() {
        return this.whiteTeam;
    }

    //TODO Make corp lists

    public List<Piece> getAlivePieces(){
        return Stream.concat(this.whiteTeam.stream(), this.blackTeam.stream()).collect(Collectors.toList());
    }

    /* Check the Board for Remaining Team Piece */
    private List<Piece> getRemainingTeam(List<Tile> boardTiles, String teamColor){
        final List<Piece> alivePieces = new ArrayList<>();
        for(final Tile tile : boardTiles){
            if(tile.isOccupied()){
                final Piece piece = tile.getPiece();
                if(piece.getColor().equals(teamColor)){
                    alivePieces.add(piece);
                }
            }
        }
        return alivePieces;
    }

    private static ArrayList<Tile> createBoard(final SetBoard setter){
        final Tile[] tiles = new Tile[64];
        for(int i = 0; i < 64; i++){
            tiles[i] = Tile.createTiles(i, setter.setConfigure.get(i));
        }
        ArrayList<Tile> listTiles = new ArrayList<Tile>();
        Collections.addAll(listTiles, tiles);
        return listTiles;
    }

    /**
     * @param isPlayerTurn determines if player is white or black
     * @return the initial board state
     *
     * set Piece paramenters needed are as follows:
     *  1: Original Starting Coordinates
     *  2: Final Color of each piece
     *  3: Corp that individual piece is associated with
     *  3: Name of individual piece
     *  4: Offset Multiplier for single directional moves (1 is down : -1 is up)
     */
    public static Board createInitialBoard(boolean isPlayerTurn){
        final SetBoard setter = new SetBoard();
        //String top = "black";
        //String bottom = "white";
        if (!isPlayerTurn) {top = "white"; bottom = "black";}

        /* AI Pieces on the top of the board */
        setter.setPiece( new Piece.Rook(0, top, "king", "Rook", 1));
        setter.setPiece( new Piece.Knight(1, top, "kingsBishop", "Knight", 1));
        setter.setPiece( new Piece.Bishop(2, top, "kingsBishop", "Bishop", 1));
        setter.setPiece( new Piece.King(3, top, "king", "King", 1));
        setter.setPiece( new Piece.Queen(4, top, "king", "Queen", 1));
        setter.setPiece( new Piece.Rook(7, top, "queensBishop", "Rook", 1));
        setter.setPiece( new Piece.Knight(6, top, "queensBishop", "Knight", 1));
        setter.setPiece( new Piece.Bishop(5, top, "king", "Bishop", 1));

        setter.setPiece(new Piece.Pawn(8, top, "kingsBishop", "Pawn", 1));
        setter.setPiece(new Piece.Pawn(9, top, "kingsBishop", "Pawn", 1));
        setter.setPiece(new Piece.Pawn(10, top, "kingsBishop", "Pawn", 1));
        setter.setPiece(new Piece.Pawn(11, top, "king", "Pawn", 1));
        setter.setPiece(new Piece.Pawn(12, top, "king", "Pawn", 1));
        setter.setPiece( new Piece.Pawn(13, top, "queensBishop", "Pawn", 1));
        setter.setPiece( new Piece.Pawn(14, top, "queensBishop", "Pawn", 1));
        setter.setPiece( new Piece.Pawn(15, top, "queensBishop", "Pawn", 1));

        /* Player Pieces on the bottom of the board */
        setter.setPiece( new Piece.Pawn(48, bottom, "queensBishop", "Pawn", -1));
        setter.setPiece( new Piece.Pawn(49, bottom, "queensBishop", "Pawn", -1));
        setter.setPiece( new Piece.Pawn(50, bottom, "queensBishop", "Pawn", -1));
        setter.setPiece( new Piece.Pawn(51, bottom, "king", "Pawn", -1));
        setter.setPiece( new Piece.Pawn(52, bottom, "king", "Pawn", -1));
        setter.setPiece( new Piece.Pawn(53, bottom, "kingsBishop", "Pawn", -1));
        setter.setPiece( new Piece.Pawn(54, bottom, "kingsBishop", "Pawn", -1));
        setter.setPiece( new Piece.Pawn(55, bottom, "kingsBishop", "Pawn", -1));

        setter.setPiece( new Piece.Rook(56, bottom, "king", "Rook", -1));
        setter.setPiece( new Piece.Knight(57, bottom, "kingsBishop", "Knight", -1));
        setter.setPiece( new Piece.Bishop(58, bottom, "kingsBishop", "Bishop", -1));
        setter.setPiece( new Piece.King(59, bottom, "king", "King", -1));
        setter.setPiece( new Piece.Queen(60, bottom, "king", "Queen", -1));
        setter.setPiece( new Piece.Rook(63, bottom, "queensBishop", "Rook", -1));
        setter.setPiece( new Piece.Knight(62, bottom, "queensBishop", "Knight", -1));
        setter.setPiece( new Piece.Bishop(61, bottom, "king", "Bishop", -1));

        setter.setIsPlayerTurn(isPlayerTurn);
        return setter.build();
    }

    public Tile getTile(final int coordinate){
        return chessBoard.get(coordinate);
    }
    //* Returns the tile-array for the board. /*
    public Tile[][] getTiles()
    {
        return tile;
    }

    /**
     * Setter Class for setting the board up in the beginning
     * and after each move. Minimizes error and maximizes immutability
     */
    public static class SetBoard {

        /* Map a Tile Coordinate to Given Piece */
        Map<Integer, Piece> setConfigure;
        /* Keeps Track of whoes turn it is */
        Boolean isPlayerTurn;

        public SetBoard(){
            this.setConfigure = new HashMap<>();
        }

        public void setIsPlayerTurn(Boolean playerTurn){
            this.isPlayerTurn = playerTurn;
        }

        public void setPiece(final Piece piece){
            this.setConfigure.put(piece.getCoordinates(), piece);
        }

        public Board build(){
            return new Board(this);
        }
    }

}
