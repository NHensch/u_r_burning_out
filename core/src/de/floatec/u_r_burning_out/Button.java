package de.floatec.u_r_burning_out;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by johannesheiler on 25.02.15.
 */
public class Button {

    private Texture texture;
    private Texture textureSelected;
    private Texture texturePressed;
    private Texture[] textureArray = new Texture[3];
    Vector2 position = new Vector2();
    public float height;
    public float width;
    public boolean isPressed=false;
    public boolean isSelected=false;
    private int index=0;

    public Button(Vector2 position, String texturenormal, String textureSelected, String texturePressed) {
        this.position = position;
        //texturen laden
        this.texture=new Texture(Gdx.files.internal(texturenormal));
        this.textureSelected=new Texture(Gdx.files.internal(textureSelected));
        this.texturePressed=new Texture(Gdx.files.internal(texturePressed));
        this.height=this.texture.getHeight();
        this.width=this.texture.getWidth();
        this.isPressed = false;
        this.textureArray[0] = this.texture;
        this.textureArray[1] = this.textureSelected;
        this.textureArray[2] = this.texturePressed;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture(){
        if(this.index<3&&this.index>-1) {
            return textureArray[this.index];
        }
        else{
            return textureArray[0];
        }
    }

    public Texture getTextureSelected(){
        return textureSelected;
    }

    public Texture getTexturePressed(){
        return texturePressed;
    }

    public float getHeight(){
        return height;
    }

    public float getWidth(){
        return width;
    }

    public void setIndex(int index){
        this.index = index;
    }


    //select/press
    public boolean isSelected(){
        return isSelected;
    }

    public void setSelected() {
        if(isSelected){
        isSelected=false;
    }
    else {
        isSelected = true;
    }

    }

    public void setSelected(boolean isSelected){
        this.isSelected= isSelected;
    }

    public boolean isPressed (){
        return isPressed;
    }

    public void setPressed () {
        if(isPressed){
            isPressed=false;
        }
        else {
            isPressed = true;
        }
    }

    public void setPressed(boolean isPressed){
        this.isPressed=isPressed;

    }
}