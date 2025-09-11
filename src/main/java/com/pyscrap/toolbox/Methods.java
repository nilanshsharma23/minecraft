package com.pyscrap.toolbox;

import java.util.List;

public class Methods {
    public static float[] FloatListToArray(List<Float> value){
        float[] out = new float[value.size()];

        for (int i = 0; i < out.length; i++) {
            out[i] = value.get(i);
        }

        return out;
    }
}
