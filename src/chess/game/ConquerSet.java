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

import java.util.HashMap;
import java.util.Map;

public class ConquerSet
{
    /* Creates a Hashmap that hold table data needed for dice roll capturing */
    private final Map<String, Integer> conquerSet = new HashMap<String, Integer>();
    private final Piece defendingPiece;

    public ConquerSet(final Piece attackingPiece, final Piece defendingPiece)
    {
        setConquerSet(attackingPiece);
        this.defendingPiece = defendingPiece;
    }

    /* Set Conquering Values to Win Dice Rolls */
    private void setConquerSet(final Piece piece)
    {
        switch (piece.getName())
        {
            case "King" -> {
                conquerSet.put("King", 3);
                conquerSet.put("Queen", 3);
                conquerSet.put("Knight", 3);
                conquerSet.put("Bishop", 3);
                conquerSet.put("Rook", 4);
                conquerSet.put("Pawn", 0);
            }

            case "Queen" -> {
                conquerSet.put("King", 3);
                conquerSet.put("Queen", 3);
                conquerSet.put("Knight", 3);
                conquerSet.put("Bishop", 3);
                conquerSet.put("Rook", 4);
                conquerSet.put("Pawn", 1);
            }

            case "Knight" -> {
                conquerSet.put("King", 5);
                conquerSet.put("Queen", 5);
                conquerSet.put("Knight", 3);
                conquerSet.put("Bishop", 3);
                conquerSet.put("Rook", 4);
                conquerSet.put("Pawn", 1);
            }

            case "Bishop" -> {
                conquerSet.put("King", 4);
                conquerSet.put("Queen", 4);
                conquerSet.put("Knight", 4);
                conquerSet.put("Bishop", 3);
                conquerSet.put("Rook", 4);
                conquerSet.put("Pawn", 2);
            }

            case "Rook" -> {
                conquerSet.put("King", 3);
                conquerSet.put("Queen", 3);
                conquerSet.put("Knight", 4);
                conquerSet.put("Bishop", 4);
                conquerSet.put("Rook", 5);
                conquerSet.put("Pawn", 4);
            }

            case "Pawn" -> {
                conquerSet.put("King", 5);
                conquerSet.put("Queen", 5);
                conquerSet.put("Knight", 5);
                conquerSet.put("Bishop", 4);
                conquerSet.put("Rook", 5);
                conquerSet.put("Pawn", 3);
            }
        }
    }

    /* Return the required value to capture the defending piece tile. */
    public int getConquerSet()
    {
        return conquerSet.get(defendingPiece.getName());
    }
}