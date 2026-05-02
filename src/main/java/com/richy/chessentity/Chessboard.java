package com.richy.chessentity;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Vector2d;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.NPCPlugin;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;

public class Chessboard {

    public int id;

    // Chess edge positions
    public Vector3d boardPosition;


    // Chess pieces and the positons
    //Map<Vector3d, String> chessboardsList;
    //Map<Vector2d, Ref<EntityStore>> chessboardOccupancy;

    // Even = White turn; Odd = Black turn
    public int turns = 0;

    public Chessboard(Store<EntityStore> store, Vector3d boardPosition) {
        this.boardPosition = new Vector3d(Math.ceil(boardPosition.x) - 0.5, boardPosition.y, Math.ceil(boardPosition.z) - 0.5);

        setup(store);
    }

    public void setup(Store<EntityStore> store){
        spawnChessPiece(store, "Piece_Mannequin_Rook"  , getChessFieldGlobalPos(new Vector2d(0, 0)), 0);
        spawnChessPiece(store, "Piece_Mannequin_Knight", getChessFieldGlobalPos(new Vector2d(1, 0)), 0);
        spawnChessPiece(store, "Piece_Mannequin_Bishop", getChessFieldGlobalPos(new Vector2d(2, 0)), 0);
        spawnChessPiece(store, "Piece_Mannequin_Queen" , getChessFieldGlobalPos(new Vector2d(3, 0)), 0);
        spawnChessPiece(store, "Piece_Mannequin_King"  , getChessFieldGlobalPos(new Vector2d(4, 0)), 0);
        spawnChessPiece(store, "Piece_Mannequin_Bishop", getChessFieldGlobalPos(new Vector2d(5, 0)), 0);
        spawnChessPiece(store, "Piece_Mannequin_Knight", getChessFieldGlobalPos(new Vector2d(6, 0)), 0);
        spawnChessPiece(store, "Piece_Mannequin_Rook"  , getChessFieldGlobalPos(new Vector2d(7, 0)), 0);

        for (int i = 0; i < 8; i++){
            spawnChessPiece(store, "Piece_Mannequin_Pawn", getChessFieldGlobalPos(new Vector2d(i, -1)), 0);
        }


        spawnChessPiece(store, "Piece_Skeleton_Rook"  , getChessFieldGlobalPos(new Vector2d(0, -7)), 180);
        spawnChessPiece(store, "Piece_Skeleton_Knight", getChessFieldGlobalPos(new Vector2d(1, -7)), 180);
        spawnChessPiece(store, "Piece_Skeleton_Bishop", getChessFieldGlobalPos(new Vector2d(2, -7)), 180);
        spawnChessPiece(store, "Piece_Skeleton_Queen" , getChessFieldGlobalPos(new Vector2d(3, -7)), 180);
        spawnChessPiece(store, "Piece_Skeleton_King"  , getChessFieldGlobalPos(new Vector2d(4, -7)), 180);
        spawnChessPiece(store, "Piece_Skeleton_Bishop", getChessFieldGlobalPos(new Vector2d(5, -7)), 180);
        spawnChessPiece(store, "Piece_Skeleton_Knight", getChessFieldGlobalPos(new Vector2d(6, -7)), 180);
        spawnChessPiece(store, "Piece_Skeleton_Rook"  , getChessFieldGlobalPos(new Vector2d(7, -7)), 180);

        for (int i = 0; i < 8; i++){
            spawnChessPiece(store, "Piece_Skeleton_Pawn", getChessFieldGlobalPos(new Vector2d(i, -6)), 180);
        }

    }

    public void clear(){

    }

    public void delete(){

    }

    public void reset(Store<EntityStore> store){
        clear();
        setup(store);
    }

    // Spawn a new Chess Piece
    private Ref<EntityStore> spawnChessPiece(Store<EntityStore> store, String npcName, Vector3d position, float rotationDeg){

        Vector3f rotationRad = new Vector3f(0f, (float)Math.toRadians(rotationDeg), 0f);
        Ref<EntityStore> npc = Objects.requireNonNull(NPCPlugin.get().spawnNPC(store, npcName, null, position, rotationRad)).key();

        return npc;
    }

    // Return global position from a chess field
    private Vector3d getChessFieldGlobalPos(Vector2d chessField){
        return Vector3d.add(boardPosition, new Vector3d(chessField.x, 0, chessField.y));
    }
}
