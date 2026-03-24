package com.richy.chessentity;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Vector2d;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.server.core.modules.entity.component.*;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.NPCPlugin;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Chessboard {

    // Chess edge positions
    public Vector3d pos1;
    public Vector3d pos2;

    public World world;
    public Store<EntityStore> store;
    public PlayerRef playerRef;

    // Chess pieces and the positons
    Map<Vector3d, String> chessboardsList;
    Map<Vector2d, Ref<EntityStore>> chessboardOccupancy;

    // Even = White turn; Odd = Black turn
    public int turns = 0;

    public Chessboard(World world, Vector3d pos1, Vector3d pos2, @Nonnull PlayerRef playerRef) {
        this.world = world;
        this.store = world.getEntityStore().getStore();
        this.playerRef = playerRef;
        this.pos1 = new Vector3d(Math.ceil(pos1.x) - 0.5, pos1.y, Math.ceil(pos1.z) - 0.5);
        this.pos2 = pos2;

        setup();
    }

    public void setup(){
        spawnChessPiece("Piece_Mannequin_Rook"  , getChessFieldGlobalPos(new Vector2d(0, 0)), 0);
        spawnChessPiece("Piece_Mannequin_Knight", getChessFieldGlobalPos(new Vector2d(1, 0)), 0);
        spawnChessPiece("Piece_Mannequin_Bishop", getChessFieldGlobalPos(new Vector2d(2, 0)), 0);
        spawnChessPiece("Piece_Mannequin_Queen" , getChessFieldGlobalPos(new Vector2d(3, 0)), 0);
        spawnChessPiece("Piece_Mannequin_King"  , getChessFieldGlobalPos(new Vector2d(4, 0)), 0);
        spawnChessPiece("Piece_Mannequin_Bishop", getChessFieldGlobalPos(new Vector2d(5, 0)), 0);
        spawnChessPiece("Piece_Mannequin_Knight", getChessFieldGlobalPos(new Vector2d(6, 0)), 0);
        spawnChessPiece("Piece_Mannequin_Rook"  , getChessFieldGlobalPos(new Vector2d(7, 0)), 0);

        for (int i = 0; i < 8; i++){
            spawnChessPiece("Piece_Mannequin_Pawn", getChessFieldGlobalPos(new Vector2d(i, -1)), 0);
        }


        spawnChessPiece("Piece_Skeleton_Rook"  , getChessFieldGlobalPos(new Vector2d(0, -7)), 180);
        spawnChessPiece("Piece_Skeleton_Knight", getChessFieldGlobalPos(new Vector2d(1, -7)), 180);
        spawnChessPiece("Piece_Skeleton_Bishop", getChessFieldGlobalPos(new Vector2d(2, -7)), 180);
        spawnChessPiece("Piece_Skeleton_Queen" , getChessFieldGlobalPos(new Vector2d(3, -7)), 180);
        spawnChessPiece("Piece_Skeleton_King"  , getChessFieldGlobalPos(new Vector2d(4, -7)), 180);
        spawnChessPiece("Piece_Skeleton_Bishop", getChessFieldGlobalPos(new Vector2d(5, -7)), 180);
        spawnChessPiece("Piece_Skeleton_Knight", getChessFieldGlobalPos(new Vector2d(6, -7)), 180);
        spawnChessPiece("Piece_Skeleton_Rook"  , getChessFieldGlobalPos(new Vector2d(7, -7)), 180);

        for (int i = 0; i < 8; i++){
            spawnChessPiece("Piece_Skeleton_Pawn", getChessFieldGlobalPos(new Vector2d(i, -6)), 180);
        }

    }

    public void clear(){

    }

    public void reset(){
        clear();
        setup();
    }

    // Spawn a new Chess Piece
    private Ref<EntityStore> spawnChessPiece(String npcName, Vector3d position, float rotationDeg){

        Vector3f rotationRad = new Vector3f(0f, (float)Math.toRadians(rotationDeg), 0f);
        Ref<EntityStore> npc = Objects.requireNonNull(NPCPlugin.get().spawnNPC(store, npcName, null, position, rotationRad)).key();

        return npc;
    }

    // Return global position from a chess field
    private Vector3d getChessFieldGlobalPos(Vector2d chessField){
        return Vector3d.add(pos1, new Vector3d(chessField.x, 0, chessField.y));
    }
}
