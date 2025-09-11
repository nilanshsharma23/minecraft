//package com.pyscrap.entities;
//
//import com.pyscrap.models.RawModel;
//import com.pyscrap.models.TexturedModel;
//import com.pyscrap.renderEngine.Loader;
//import com.pyscrap.textures.ModelTexture;
//import org.joml.Vector3f;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Block extends Entity{
//    private List<Float> vertices = new ArrayList<>();
//    private List<Float> textureCoords = new ArrayList<>();
//    private List<int> indices = new ArrayList<>();
//
//    public Block(Vector3f position, Vector3f rotation, Vector3f scale) {
//        Loader loader = new Loader();
//        RawModel rawModel = loader.loadToVAO(vertices, textureCoords, indices);
//        ModelTexture texture = new ModelTexture(loader.loadTexture("texture"));
//        TexturedModel model = new TexturedModel();
//
//        super(model, position, rotation, scale);
//    }
//}
