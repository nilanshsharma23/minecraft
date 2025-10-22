package com.pyscrap.terrain;

import java.util.ArrayList;
import java.util.List;

import com.pyscrap.Globals;
import com.pyscrap.renderEngine.Loader;
import com.pyscrap.renderEngine.MasterRenderer;
import com.pyscrap.textures.ModelTexture;
import com.pyscrap.toolbox.NoiseGenerator;
import com.pyscrap.toolbox.OpenSimplex2S;

public class World {

    static byte[][][] blockIDs = new byte[Globals.CHUNK_LENGTH
            * Globals.NO_OF_CHUNKS_X][Globals.CHUNK_HEIGHT][Globals.CHUNK_LENGTH * Globals.NO_OF_CHUNKS_Z];

    List<Chunk> chunks = new ArrayList<>();

    NoiseGenerator noiseGenerator = new NoiseGenerator();

    public World(List<ModelTexture> textures, MasterRenderer renderer, Loader loader) {
        for (int x = 0; x < Globals.CHUNK_LENGTH * Globals.NO_OF_CHUNKS_X; x++) {
            for (int z = 0; z < Globals.CHUNK_LENGTH * Globals.NO_OF_CHUNKS_Z; z++) {
                for (int y = 0; y < Globals.CHUNK_HEIGHT; y++) {

                    double noise = Math
                            .floor(OpenSimplex2S.noise3_ImproveXZ(0, x * Globals.FREQUENCY, y *
                                    Globals.FREQUENCY,
                                    z * Globals.FREQUENCY)
                                    * 10)
                            + 5;
                    if (y > noise) {
                        blockIDs[x][y][z] = BlockType.AIR;
                        continue;
                    }

                    blockIDs[x][y][z] = BlockType.STONE;
                }
            }
        }

        for (int x = 0; x < Globals.NO_OF_CHUNKS_X; x++) {
            for (int z = 0; z < Globals.NO_OF_CHUNKS_Z; z++) {
                Chunk chunk = new Chunk(x, z, textures, renderer, loader);
                chunks.add(chunk);
            }
        }
    }

    public static byte getBlockID(int x, int y, int z) {
        if (x < 0 || y < 0 || z < 0)
            return BlockType.AIR;

        if (x >= Globals.CHUNK_LENGTH * Globals.NO_OF_CHUNKS_X || y >= Globals.CHUNK_HEIGHT
                || z >= Globals.CHUNK_LENGTH * Globals.NO_OF_CHUNKS_Z)
            return BlockType.AIR;

        return blockIDs[x][y][z];
    }

    public void render() {
        for (Chunk chunk : chunks) {
            chunk.render();
        }
    }
}
