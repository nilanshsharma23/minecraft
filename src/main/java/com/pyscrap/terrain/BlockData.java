package com.pyscrap.terrain;

public class BlockData {

        public static Float[] positiveXVertices = { 0.5f, -0.5f, 0.5f,
                        0.5f, -0.5f, -0.5f,
                        0.5f, 0.5f, -0.5f,
                        0.5f, 0.5f, 0.5f, };

        public static Float[] positiveXTextureCoords = { 0.0f, 0.0f,
                        1.0f, 0.0f,
                        1.0f, 1.0f,
                        0.0f, 1.0f, };

        public static Float[] negativeXVertices = { -0.5f, -0.5f, 0.5f,
                        -0.5f, 0.5f, 0.5f,
                        -0.5f, 0.5f, -0.5f,
                        -0.5f, -0.5f, -0.5f,
        };

        public static Float[] negativeXTextureCoords = {
                        1.0f, 0.0f,
                        1.0f, 1.0f,
                        0.0f, 1.0f,
                        0.0f, 0.0f, };

        public static Float[] positiveYVertices = { -0.5f, 0.5f, 0.5f,
                        0.5f, 0.5f, 0.5f,
                        0.5f, 0.5f, -0.5f,
                        -0.5f, 0.5f, -0.5f, };

        public static Float[] positiveYTextureCoords = { 0.0f, 0.0f,
                        1.0f, 0.0f,
                        1.0f, 1.0f,
                        0.0f, 1.0f, };

        public static Float[] negativeYVertices = { -0.5f, -0.5f, 0.5f,
                        -0.5f, -0.5f, -0.5f,
                        0.5f, -0.5f, -0.5f,
                        0.5f, -0.5f, 0.5f };

        public static Float[] negativeYTextureCoords = { 0.0f, 0.0f,
                        1.0f, 0.0f,
                        1.0f, 1.0f,
                        0.0f, 1.0f, };

        public static Float[] positiveZVertices = { -0.5f, -0.5f, 0.5f,
                        0.5f, -0.5f, 0.5f,
                        0.5f, 0.5f, 0.5f,
                        -0.5f, 0.5f, 0.5f, };

        public static Float[] positiveZTextureCoords = {
                        0.0f, 0.0f,
                        1.0f, 0.0f,
                        1.0f, 1.0f,
                        0.0f, 1.0f, };

        public static Float[] negativeZVertices = { -0.5f, -0.5f, -0.5f,
                        -0.5f, 0.5f, -0.5f,
                        0.5f, 0.5f, -0.5f,
                        0.5f, -0.5f, -0.5f, };

        public static Float[] negativeZTextureCoords = {
                        1.0f, 0.0f,
                        1.0f, 1.0f,
                        0.0f, 1.0f,
                        0.0f, 0.0f, };

        public static int[] indices = {
                        0, 1, 2,
                        2, 3, 0,

                        // Back Face (using vertices 4-7)
                        4, 5, 6,
                        6, 7, 4,

                        // Right Face (using vertices 8-11)
                        8, 9, 10,
                        10, 11, 8,

                        // Left Face (using vertices 12-15)
                        12, 13, 14,
                        14, 15, 12,

                        // Top Face (using vertices 16-19)
                        16, 17, 18,
                        18, 19, 16,

                        // Bottom Face (using vertices 20-23)
                        20, 21, 22,
                        22, 23, 20 };

}
