package com.pyscrap.terrain;

import java.util.ArrayList;
import java.util.List;

import com.pyscrap.renderEngine.Loader;
import com.pyscrap.renderEngine.MasterRenderer;
import com.pyscrap.textures.ModelTexture;
import com.pyscrap.toolbox.OpenSimplex2S;

public class World {
    static final int NO_OF_CHUNKS_X = 2;
    static final int NO_OF_CHUNKS_Z = 2;
    static final int CHUNK_LENGTH = 32;
    static final int CHUNK_HEIGHT = 32;
    static final int NO_OF_BLOCKS = CHUNK_HEIGHT * CHUNK_LENGTH * CHUNK_LENGTH * NO_OF_CHUNKS_X * NO_OF_CHUNKS_Z;
    static final double FREQUENCY = 1.0 / 16.0;

    static byte[] blockIDs = new byte[NO_OF_BLOCKS];

    List<Chunk> chunks = new ArrayList<>();

    public World(ModelTexture[] textures, MasterRenderer renderer, Loader loader) {
        for (int x = 0; x < CHUNK_LENGTH * NO_OF_CHUNKS_X; x++) {
            for (int y = 0; y < CHUNK_HEIGHT; y++) {
                for (int z = 0; z < CHUNK_LENGTH * NO_OF_CHUNKS_Z; z++) {

                    double noise = Math
                            .floor(OpenSimplex2S.noise3_ImproveXY(0, x * FREQUENCY,
                                    y * FREQUENCY, z * FREQUENCY)
                                    * 10)
                            + 5;

                    int id = x + (y * CHUNK_HEIGHT) + (z * CHUNK_LENGTH * CHUNK_HEIGHT);
                    blockIDs[id] = (byte) (noise >= y ? 1 : 0);
                }
            }
        }

        for (int x = 0; x < NO_OF_CHUNKS_X; x++) {
            for (int z = 0; z < NO_OF_CHUNKS_Z; z++) {
                Chunk chunk = new Chunk(x, z, textures, renderer, loader, blockIDs);
                chunks.add(chunk);
            }
        }
    }

    public static byte getBlockID(int x, int y, int z, int xoffset, int zoffset) {
        int id = x + (xoffset * CHUNK_LENGTH) + (y * CHUNK_HEIGHT)
                + ((z + (zoffset * CHUNK_LENGTH)) * CHUNK_LENGTH * CHUNK_HEIGHT);
        return blockIDs[id];
    }

    public void render() {
        for (Chunk chunk : chunks) {
            chunk.render();
        }
    }
}
