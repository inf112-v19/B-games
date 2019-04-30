package inf112.skeleton.app.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {

    private static TextureAtlas textureAtlas;

    public static TextureAtlas getTextureAtlas(){
        if(textureAtlas == null){
        textureAtlas = new TextureAtlas(Gdx.files.internal("assets/atlas/test.atlas"));}
        return textureAtlas;
    }

}
