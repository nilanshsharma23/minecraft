package com.pyscrap;

public class Globals {
    public static final int NO_OF_CHUNKS_X = 2;
    public static final int NO_OF_CHUNKS_Z = 2;
    public static final int CHUNK_LENGTH = 16;
    public static final int CHUNK_HEIGHT = 16;
    public static final int NO_OF_BLOCKS = CHUNK_HEIGHT * CHUNK_LENGTH * CHUNK_LENGTH * NO_OF_CHUNKS_X * NO_OF_CHUNKS_Z;
    public static final double FREQUENCY = 1.0 / 32.0;
}
