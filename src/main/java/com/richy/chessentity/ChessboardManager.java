package com.richy.chessentity;

import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import tools.jackson.core.StreamReadFeature;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ChessboardManager {

    List<Chessboard> chessboardsList = new ArrayList<>();

    public ChessboardManager(){

    }

    public void loadAll(){

        ObjectMapper mapper = JsonMapper.builder()
                .enable(StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION)
                .build();

        chessboardsList = mapper.readValue(
                new File("universe/worlds/default/chessboard.json"),
                new TypeReference<List<Chessboard>>() {}
        );
    }

    private void unload(){

    }

    private void saveAll(){
        ObjectMapper mapper = new ObjectMapper();

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File("universe/worlds/default/chessboard.json"), chessboardsList);
    }

    public void create(Store<EntityStore> store, Vector3d positon){
        Chessboard newChessboard = new Chessboard(store, positon);

        chessboardsList.add(newChessboard);

        saveAll();
    }

    public void listAll(){
        for (Chessboard chessboard : chessboardsList) {
            System.out.println(chessboard.id);
        }
    }
}
