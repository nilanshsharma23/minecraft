package com.pyscrap.terrain;

public class BlockData {

        public static Float[] positiveXVertices = { 0.5f, 0.5f, -0.5f,
                        0.5f, -0.5f, -0.5f,
                        0.5f, -0.5f, 0.5f,
                        0.5f, 0.5f, 0.5f, };

        public static Float[] negativeXVertices = { -0.5f, 0.5f, -0.5f,
                        -0.5f, -0.5f, -0.5f,
                        -0.5f, -0.5f, 0.5f,
                        -0.5f, 0.5f, 0.5f, };

        public static Float[] positiveYVertices = { -0.5f, 0.5f, 0.5f,
                        -0.5f, 0.5f, -0.5f,
                        0.5f, 0.5f, -0.5f,
                        0.5f, 0.5f, 0.5f, };

        public static Float[] negativeYVertices = { -0.5f, -0.5f, 0.5f,
                        -0.5f, -0.5f, -0.5f,
                        0.5f, -0.5f, -0.5f,
                        0.5f, -0.5f, 0.5f };

        public static Float[] positiveZVertices = { -0.5f, 0.5f, 0.5f,
                        -0.5f, -0.5f, 0.5f,
                        0.5f, -0.5f, 0.5f,
                        0.5f, 0.5f, 0.5f, };

        public static Float[] negativeZVertices = { -0.5f, 0.5f, -0.5f,
                        -0.5f, -0.5f, -0.5f,
                        0.5f, -0.5f, -0.5f,
                        0.5f, 0.5f, -0.5f, };

        public static int[] indices = {
                        0, 1, 3,
                        3, 1, 2,
                        4, 5, 7,
                        7, 5, 6,
                        8, 9, 11,
                        11, 9, 10,
                        12, 13, 15,
                        15, 13, 14,
                        16, 17, 19,
                        19, 17, 18,
                        20, 21, 23,
                        23, 21, 22 };

        public static float[] textureCoords = {
                        0f, 0f,
                        0f, 1f,
                        1f, 1f,
                        1f, 0f,

                        0f, 0f,
                        0f, 1f,
                        1f, 1f,
                        1f, 0f,

                        0f, 0f,
                        0f, 1f,
                        1f, 1f,
                        1f, 0f,

                        0f, 0f,
                        0f, 1f,
                        1f, 1f,
                        1f, 0f,

                        0f, 0f,
                        0f, 1f,
                        1f, 1f,
                        1f, 0f,

                        0f, 0f,
                        0f, 1f,
                        1f, 1f,
                        1f, 0f, };
}
