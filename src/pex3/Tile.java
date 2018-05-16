/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pex3;

import java.io.File;
import javafx.scene.image.Image;

/**
 *
 * @author Jared
 */
public class Tile {
    //X and Y positions, a value of the tile, the icon image, and a boolean to see if the tile was just made.

    private int x, y;
    private int value;
    private Image icon;
    private boolean justMade;

    public Tile() {
        value = 0;
        icon = new Image(new File("src\\white.png").toURI().toString());
        justMade = false;

    }

    public Tile(int x, int y, int value, Image icon, boolean justMade) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.icon = icon;
        this.justMade = justMade;
    }

   

    //Function that sets the icon of the tile depending on the value stored.
    public void changeIcon() {
        switch (value) {
            case 0:
                icon = new Image(new File("src\\white.png").toURI().toString());
                break;
            case 1:
                icon = new Image(new File("src\\2-javascript.png").toURI().toString());
                break;
            case 2:
                icon = new Image(new File("src\\4-java.jpg").toURI().toString());
                break;
            case 3:
                icon = new Image(new File("src\\8-python.png").toURI().toString());
                break;
            case 4:
                icon = new Image(new File("src\\16-perl.png").toURI().toString());
                break;
            case 5:
                icon = new Image(new File("src\\32-rust.png").toURI().toString());
                break;
            case 6:
                icon = new Image(new File("src\\64-php.png").toURI().toString());
                break;
            case 7:
                icon = new Image(new File("src\\128-ruby.png").toURI().toString());
                break;
            case 8:
                icon = new Image(new File("src\\256-swift.png").toURI().toString());
                break;
            case 9:
                icon = new Image(new File("src\\512-c.png").toURI().toString());
                break;
            case 10:
                icon = new Image(new File("src\\1024-c++.png").toURI().toString());
                break;
            case 11:
                icon = new Image(new File("src\\2048-c#.png").toURI().toString());
                break;
            default:
                icon = new Image(new File("src\\white.png").toURI().toString());
                break;

        }
    }

    public boolean isJustMade() {
        return justMade;
    }

    public void setJustMade(boolean justMade) {
        this.justMade = justMade;
    }

    
    
    
    public Image getIcon() {
        return icon;
    }

    public int getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public void setValue(int value) {
        this.value = value;
        changeIcon();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
