package com.richy.chessentity;

import com.hypixel.hytale.component.AddReason;
import com.hypixel.hytale.component.Holder;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Vector2d;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.server.core.asset.type.model.config.Model;
import com.hypixel.hytale.server.core.asset.type.model.config.ModelAsset;
import com.hypixel.hytale.server.core.entity.Entity;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.event.events.entity.EntityRemoveEvent;
import com.hypixel.hytale.server.core.modules.entity.EntityModule;
import com.hypixel.hytale.server.core.modules.entity.component.*;
import com.hypixel.hytale.server.core.modules.entity.tracker.NetworkId;
import com.hypixel.hytale.server.core.modules.interaction.Interactions;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.NPCPlugin;
import com.hypixel.hytale.server.npc.role.Role;

import javax.annotation.Nonnull;
import java.util.Map;

public class Chessboard {

    // Chess edge positions
    public Vector3d pos1;
    public Vector3d pos2;

    public World world;
    public Store<EntityStore> store;
    public PlayerRef playerRef;

    // Chess pieces and the positons
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
        NPCPlugin.get().spawnNPC(store, "Piece_Mannequin_Rook", null, Vector3d.add(pos1, new Vector3d(0, 0, 0)), Vector3f.ZERO);
        NPCPlugin.get().spawnNPC(store, "Piece_Mannequin_Knight", null, Vector3d.add(pos1, new Vector3d(1, 0, 0)), Vector3f.ZERO);
        NPCPlugin.get().spawnNPC(store, "Piece_Mannequin_Bishop", null, Vector3d.add(pos1, new Vector3d(2, 0, 0)), Vector3f.ZERO);
        NPCPlugin.get().spawnNPC(store, "Piece_Mannequin_Queen", null, Vector3d.add(pos1, new Vector3d(3, 0, 0)), Vector3f.ZERO);
        NPCPlugin.get().spawnNPC(store, "Piece_Mannequin_King", null, Vector3d.add(pos1, new Vector3d(4, 0, 0)), Vector3f.ZERO);
        NPCPlugin.get().spawnNPC(store, "Piece_Mannequin_Bishop", null, Vector3d.add(pos1, new Vector3d(5, 0, 0)), Vector3f.ZERO);
        NPCPlugin.get().spawnNPC(store, "Piece_Mannequin_Knight", null, Vector3d.add(pos1, new Vector3d(6, 0, 0)), Vector3f.ZERO);
        NPCPlugin.get().spawnNPC(store, "Piece_Mannequin_Rook", null, Vector3d.add(pos1, new Vector3d(7, 0, 0)), Vector3f.ZERO);

        NPCPlugin.get().spawnNPC(store, "Piece_Mannequin_Pawn", null, Vector3d.add(pos1, new Vector3d(0, 0, -1)), Vector3f.ZERO);
        NPCPlugin.get().spawnNPC(store, "Piece_Mannequin_Pawn", null, Vector3d.add(pos1, new Vector3d(1, 0, -1)), Vector3f.ZERO);
        NPCPlugin.get().spawnNPC(store, "Piece_Mannequin_Pawn", null, Vector3d.add(pos1, new Vector3d(2, 0, -1)), Vector3f.ZERO);
        NPCPlugin.get().spawnNPC(store, "Piece_Mannequin_Pawn", null, Vector3d.add(pos1, new Vector3d(3, 0, -1)), Vector3f.ZERO);
        NPCPlugin.get().spawnNPC(store, "Piece_Mannequin_Pawn", null, Vector3d.add(pos1, new Vector3d(4, 0, -1)), Vector3f.ZERO);
        NPCPlugin.get().spawnNPC(store, "Piece_Mannequin_Pawn", null, Vector3d.add(pos1, new Vector3d(5, 0, -1)), Vector3f.ZERO);
        NPCPlugin.get().spawnNPC(store, "Piece_Mannequin_Pawn", null, Vector3d.add(pos1, new Vector3d(6, 0, -1)), Vector3f.ZERO);
        NPCPlugin.get().spawnNPC(store, "Piece_Mannequin_Pawn", null, Vector3d.add(pos1, new Vector3d(7, 0, -1)), Vector3f.ZERO);


        NPCPlugin.get().spawnNPC(store, "Piece_Skeleton_Rook", null, Vector3d.add(pos1, new Vector3d(0, 0, -7)), new Vector3f(0f, (float)Math.toRadians(180), 0f));
        NPCPlugin.get().spawnNPC(store, "Piece_Skeleton_Knight", null, Vector3d.add(pos1, new Vector3d(1, 0, -7)), new Vector3f(0f, (float)Math.toRadians(180), 0f));
        NPCPlugin.get().spawnNPC(store, "Piece_Skeleton_Bishop", null, Vector3d.add(pos1, new Vector3d(2, 0, -7)), new Vector3f(0f, (float)Math.toRadians(180), 0f));
        NPCPlugin.get().spawnNPC(store, "Piece_Skeleton_Queen", null, Vector3d.add(pos1, new Vector3d(3, 0, -7)), new Vector3f(0f, (float)Math.toRadians(180), 0f));
        NPCPlugin.get().spawnNPC(store, "Piece_Skeleton_King", null, Vector3d.add(pos1, new Vector3d(4, 0, -7)), new Vector3f(0f, (float)Math.toRadians(180), 0f));
        NPCPlugin.get().spawnNPC(store, "Piece_Skeleton_Bishop", null, Vector3d.add(pos1, new Vector3d(5, 0, -7)), new Vector3f(0f, (float)Math.toRadians(180), 0f));
        NPCPlugin.get().spawnNPC(store, "Piece_Skeleton_Knight", null, Vector3d.add(pos1, new Vector3d(6, 0, -7)), new Vector3f(0f, (float)Math.toRadians(180), 0f));
        NPCPlugin.get().spawnNPC(store, "Piece_Skeleton_Rook", null, Vector3d.add(pos1, new Vector3d(7, 0, -7)), new Vector3f(0f, (float)Math.toRadians(180), 0f));

        NPCPlugin.get().spawnNPC(store, "Piece_Skeleton_Pawn", null, Vector3d.add(pos1, new Vector3d(0, 0, -6)), new Vector3f(0f, (float)Math.toRadians(180), 0f));
        NPCPlugin.get().spawnNPC(store, "Piece_Skeleton_Pawn", null, Vector3d.add(pos1, new Vector3d(1, 0, -6)), new Vector3f(0f, (float)Math.toRadians(180), 0f));
        NPCPlugin.get().spawnNPC(store, "Piece_Skeleton_Pawn", null, Vector3d.add(pos1, new Vector3d(2, 0, -6)), new Vector3f(0f, (float)Math.toRadians(180), 0f));
        NPCPlugin.get().spawnNPC(store, "Piece_Skeleton_Pawn", null, Vector3d.add(pos1, new Vector3d(3, 0, -6)), new Vector3f(0f, (float)Math.toRadians(180), 0f));
        NPCPlugin.get().spawnNPC(store, "Piece_Skeleton_Pawn", null, Vector3d.add(pos1, new Vector3d(4, 0, -6)), new Vector3f(0f, (float)Math.toRadians(180), 0f));
        NPCPlugin.get().spawnNPC(store, "Piece_Skeleton_Pawn", null, Vector3d.add(pos1, new Vector3d(5, 0, -6)), new Vector3f(0f, (float)Math.toRadians(180), 0f));
        NPCPlugin.get().spawnNPC(store, "Piece_Skeleton_Pawn", null, Vector3d.add(pos1, new Vector3d(6, 0, -6)), new Vector3f(0f, (float)Math.toRadians(180), 0f));
        NPCPlugin.get().spawnNPC(store, "Piece_Skeleton_Pawn", null, Vector3d.add(pos1, new Vector3d(7, 0, -6)), new Vector3f(0f, (float)Math.toRadians(180), 0f));
    }

    public void clear(){

    }

    public void reset(){
        clear();
        setup();
    }
}
