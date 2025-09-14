package com.pyscrap.terrain;

import com.pyscrap.Globals;
import com.pyscrap.entities.Entity;
import com.pyscrap.models.RawModel;
import com.pyscrap.models.TexturedModel;
import com.pyscrap.renderEngine.Loader;
import com.pyscrap.renderEngine.MasterRenderer;
import com.pyscrap.textures.ModelTexture;
import com.pyscrap.toolbox.Methods;

import org.joml.Vector3f;

import java.util.*;

public class Chunk {
    List<Entity> blocks = new ArrayList<>();

    Random random = new Random();

    MasterRenderer renderer;
    boolean playerInChunk = false;

    int xoffset;
    int zoffset;

    public Chunk(int xoffset, int zoffset, List<ModelTexture> textures, MasterRenderer renderer,
            Loader loader) {
        this.renderer = renderer;
        this.xoffset = xoffset;
        this.zoffset = zoffset;

        for (int x = 0; x < Globals.CHUNK_LENGTH; x++) {
            for (int z = 0; z < Globals.CHUNK_LENGTH; z++) {
                for (int y = 0; y < Globals.CHUNK_HEIGHT; y++) {
                    int xOffsetted = x + (xoffset * Globals.CHUNK_LENGTH);
                    int zOffsetted = z + (zoffset * Globals.CHUNK_LENGTH);

                    if (World.getBlockID(xOffsetted, y, zOffsetted) == BlockType.AIR) {
                        continue;
                    }

                    List<Float> vertices = new ArrayList<>();
                    List<Float> textureCoords = new ArrayList<>();

                    if (World.getBlockID(xOffsetted + 1, y, zOffsetted) == BlockType.AIR) {
                        vertices.addAll(Arrays.asList(BlockData.positiveXVertices));
                        textureCoords.addAll(Arrays.asList(BlockData.positiveXTextureCoords));
                    }

                    if (World.getBlockID(xOffsetted - 1, y, zOffsetted) == BlockType.AIR) {
                        vertices.addAll(Arrays.asList(BlockData.negativeXVertices));
                        textureCoords.addAll(Arrays.asList(BlockData.negativeXTextureCoords));
                    }

                    if (World.getBlockID(xOffsetted, y + 1, zOffsetted) == BlockType.AIR) {
                        vertices.addAll(Arrays.asList(BlockData.positiveYVertices));
                        textureCoords.addAll(Arrays.asList(BlockData.positiveYTextureCoords));
                    }

                    if (World.getBlockID(xOffsetted, y - 1, zOffsetted) == BlockType.AIR) {
                        vertices.addAll(Arrays.asList(BlockData.negativeYVertices));
                        textureCoords.addAll(Arrays.asList(BlockData.negativeYTextureCoords));
                    }

                    if (World.getBlockID(xOffsetted, y, zOffsetted + 1) == BlockType.AIR) {
                        vertices.addAll(Arrays.asList(BlockData.positiveZVertices));
                        textureCoords.addAll(Arrays.asList(BlockData.positiveZTextureCoords));
                    }

                    if (World.getBlockID(xOffsetted, y, zOffsetted - 1) == BlockType.AIR) {
                        vertices.addAll(Arrays.asList(BlockData.negativeZVertices));
                        textureCoords.addAll(Arrays.asList(BlockData.negativeZTextureCoords));
                    }

                    RawModel model = loader.loadToVAO(Methods.FloatListToArray(vertices),
                            Methods.FloatListToArray(textureCoords),
                            BlockData.indices);
                    TexturedModel texturedModel = new TexturedModel(model,
                            textures.get(World.getBlockID(xOffsetted, y, zOffsetted) - 1));

                    blocks.add(new Entity(texturedModel,
                            new Vector3f(xOffsetted, y, zOffsetted),
                            new Vector3f(0, 0, 0),
                            new Vector3f(1, 1, 1)));
                }
            }
        }
    }

    public void render() {
        playerInChunk = (Globals.chunkCoordX <= xoffset + 1 && Globals.chunkCoordX >= xoffset - 1)
                & (Globals.chunkCoordZ <= zoffset + 1 && Globals.chunkCoordZ >= zoffset - 1);

        if (playerInChunk) {
            for (Entity block : blocks) {
                renderer.processEntity(block);
            }
        }
    }
}
