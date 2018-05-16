package pex3;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;

public class MainGameFXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane mainPane;

    @FXML
    private ImageView tile0;

    @FXML
    private ImageView tile12;

    @FXML
    private ImageView tile11;

    @FXML
    private ImageView tile10;

    @FXML
    private ImageView tile9;

    @FXML
    private ImageView tile8;

    @FXML
    private ImageView tile4;

    @FXML
    private ImageView tile5;

    @FXML
    private ImageView tile6;

    @FXML
    private ImageView tile7;

    @FXML
    private ImageView tile3;

    @FXML
    private ImageView tile2;

    @FXML
    private ImageView tile1;

    @FXML
    private ImageView tile13;

    @FXML
    private ImageView tile14;

    @FXML
    private ImageView tile15;

    @FXML
    private Button restartButton;

    @FXML
    private Label outcomeText;

    @FXML
    private Rectangle outcomeRectangle;

    private Game currentGame;

    @FXML
    private Label score;

    private int up = 0;
    private int down = 0;
    private int right = 0;
    private int left = 0;

    //This function returns the correct imageview corresponding to the tile in the board.
    public ImageView getImageView(Tile tempTile) {
        switch (4 * (tempTile.getX()) + tempTile.getY()) {
            case 0:
                return tile0;
            case 1:
                return tile1;
            case 2:
                return tile2;
            case 3:
                return tile3;
            case 4:
                return tile4;
            case 5:
                return tile5;
            case 6:
                return tile6;
            case 7:
                return tile7;
            case 8:
                return tile8;
            case 9:
                return tile9;
            case 10:
                return tile10;
            case 11:
                return tile11;
            case 12:
                return tile12;
            case 13:
                return tile13;
            case 14:
                return tile14;
            case 15:
                return tile15;
            default:
                return tile0;
        }
    }

    
    //Restart button that resets it all.
    @FXML
    public void restart(ActionEvent event) {
        mediaPlay.stop();
        startUp();

    }

    //Runs all the start up neccessities.
    public void startUp() {
        //Loads a new game, sets all the images on the board to white.
        currentGame = new Game();
        ArrayList<ArrayList<Tile>> blankBoard = currentGame.getGameBoard().getCurrentBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                getImageView(blankBoard.get(i).get(j)).setImage(blankBoard.get(i).get(j).getIcon());
            }
        }
        //Sets the outcome text to an empty string so it doesn't display.
        outcomeText.setText("");
        outcomeRectangle.setVisible(false);
        score.setText("0");
        
        playMusic();

    }
    private MediaPlayer mediaPlay;
    public void playMusic() {
        String musicFile = "src\\blankSpace.wav";

        Media song = new Media(new File(musicFile).toURI().toString());
        mediaPlay = new MediaPlayer(song);
        mediaPlay.play();
    }

    //Updates the icon of the tile passed into it.
    //Used for when tiles move or combine.
    public void updateIcon(Tile changing) {
        if (changing == null) {
            return;
        }
        System.out.println(changing.getValue());
        getImageView(changing).setImage(changing.getIcon());
    }

    //Combining function, takes two tiles and changes their icons and values.
    public void combine(Tile base, Tile mover) {

        if (base.getValue() == mover.getValue()) {
            base.setValue(base.getValue() + 1);
            mover.setValue(0);
            updateIcon(base);
            updateIcon(mover);
            base.setJustMade(true);
            currentGame.addScore((int) Math.pow(2, base.getValue()));   //Adds to the score.
            score.setText(String.valueOf(currentGame.getScore()));
            
            //Resets the lose conditions
            up = 0;
            down = 0;
            left = 0;
            right = 0;

        }
    }

    //Huge movement function, very hard to read.
    //Each movement direction is two for loops, three ifs, and three else ifs.
    //The loops provide the coordinates for checking tiles.
    //The ifs are to check if the tiles in the direction of movement are blank.
    //The else ifs are to check if the tile in the direction of movement is the same as the moving one, and combines if they are.
    @FXML
    public void move(KeyEvent event) {
        System.out.println("KeyReleased");

        switch (event.getCode()) {
            case UP:
                for (int i = 1; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        Tile temp = currentGame.getGameBoard().getCurrentBoard().get(i).get(j);
                        if (currentGame.getGameBoard().getCurrentBoard().get(i - 1).get(j).getValue() == 0) {
                            if (i - 2 >= 0 && currentGame.getGameBoard().getCurrentBoard().get(i - 2).get(j).getValue() == 0) {
                                if (i - 3 >= 0 && currentGame.getGameBoard().getCurrentBoard().get(i - 3).get(j).getValue() == 0) {
                                    currentGame.getGameBoard().getCurrentBoard().get(i - 3).get(j).setValue(temp.getValue());
                                    updateIcon(currentGame.getGameBoard().getCurrentBoard().get(i - 3).get(j));

                                    temp.setValue(0);
                                    updateIcon(temp);
                                } else if (i - 3 >= 0 && currentGame.getGameBoard().getCurrentBoard().get(i - 3).get(j).getValue() == temp.getValue()
                                        && !temp.isJustMade() && !currentGame.getGameBoard().getCurrentBoard().get(i - 3).get(j).isJustMade()) {
                                    combine(currentGame.getGameBoard().getCurrentBoard().get(i - 3).get(j), temp);
                                }

                                currentGame.getGameBoard().getCurrentBoard().get(i - 2).get(j).setValue(temp.getValue());
                                updateIcon(currentGame.getGameBoard().getCurrentBoard().get(i - 2).get(j));
                                temp.setValue(0);
                                updateIcon(temp);
                            } else if (i - 2 >= 0 && currentGame.getGameBoard().getCurrentBoard().get(i - 2).get(j).getValue() == temp.getValue()
                                    && !temp.isJustMade() && !currentGame.getGameBoard().getCurrentBoard().get(i - 2).get(j).isJustMade()) {
                                combine(currentGame.getGameBoard().getCurrentBoard().get(i - 2).get(j), temp);
                            }
                            currentGame.getGameBoard().getCurrentBoard().get(i - 1).get(j).setValue(temp.getValue());
                            updateIcon(currentGame.getGameBoard().getCurrentBoard().get(i - 1).get(j));
                            temp.setValue(0);
                            updateIcon(temp);
                        } else if (i - 1 >= 0 && currentGame.getGameBoard().getCurrentBoard().get(i - 1).get(j).getValue() == temp.getValue()
                                && !temp.isJustMade() && !currentGame.getGameBoard().getCurrentBoard().get(i - 1).get(j).isJustMade()) {
                            combine(currentGame.getGameBoard().getCurrentBoard().get(i - 1).get(j), temp);
                        }
                    }
                }
                up = 1;
                break;
            case DOWN:
                for (int i = 2; i >= 0; i--) {
                    for (int j = 3; j >= 0; j--) {
                        Tile temp = currentGame.getGameBoard().getCurrentBoard().get(i).get(j);
                        if (currentGame.getGameBoard().getCurrentBoard().get(i + 1).get(j).getValue() == 0) {
                            if (i + 2 <= 3 && currentGame.getGameBoard().getCurrentBoard().get(i + 2).get(j).getValue() == 0) {
                                if (i + 3 <= 3 && currentGame.getGameBoard().getCurrentBoard().get(i + 3).get(j).getValue() == 0) {
                                    currentGame.getGameBoard().getCurrentBoard().get(i + 3).get(j).setValue(temp.getValue());
                                    updateIcon(currentGame.getGameBoard().getCurrentBoard().get(i + 3).get(j));
                                    temp.setValue(0);
                                    updateIcon(temp);
                                } else if (i + 3 <= 3 && currentGame.getGameBoard().getCurrentBoard().get(i + 3).get(j).getValue() == temp.getValue()
                                        && !temp.isJustMade() && !currentGame.getGameBoard().getCurrentBoard().get(i + 3).get(j).isJustMade()) {
                                    combine(currentGame.getGameBoard().getCurrentBoard().get(i + 3).get(j), temp);
                                }

                                currentGame.getGameBoard().getCurrentBoard().get(i + 2).get(j).setValue(temp.getValue());
                                updateIcon(currentGame.getGameBoard().getCurrentBoard().get(i + 2).get(j));
                                temp.setValue(0);
                                updateIcon(temp);
                            } else if (i + 2 <= 3 && currentGame.getGameBoard().getCurrentBoard().get(i + 2).get(j).getValue() == temp.getValue()
                                    && !temp.isJustMade() && !currentGame.getGameBoard().getCurrentBoard().get(i + 2).get(j).isJustMade()) {
                                combine(currentGame.getGameBoard().getCurrentBoard().get(i + 2).get(j), temp);
                            }
                            currentGame.getGameBoard().getCurrentBoard().get(i + 1).get(j).setValue(temp.getValue());
                            updateIcon(currentGame.getGameBoard().getCurrentBoard().get(i + 1).get(j));
                            temp.setValue(0);
                            updateIcon(temp);
                        } else if (i + 1 <= 3 && currentGame.getGameBoard().getCurrentBoard().get(i + 1).get(j).getValue() == temp.getValue()
                                && !temp.isJustMade() && !currentGame.getGameBoard().getCurrentBoard().get(i + 1).get(j).isJustMade()) {
                            combine(currentGame.getGameBoard().getCurrentBoard().get(i + 1).get(j), temp);
                        }
                    }
                }
                down = 1;
                break;

            case LEFT:
                for (int i = 0; i < 4; i++) {
                    for (int j = 1; j < 4; j++) {
                        Tile temp = currentGame.getGameBoard().getCurrentBoard().get(i).get(j);
                        if (currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 1).getValue() == 0) {
                            if (j - 2 >= 0 && currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 2).getValue() == 0) {
                                if (j - 3 >= 0 && currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 3).getValue() == 0) {
                                    currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 3).setValue(temp.getValue());
                                    updateIcon(currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 3));

                                    temp.setValue(0);
                                    updateIcon(temp);
                                } else if (j - 3 >= 0 && currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 3).getValue() == temp.getValue()
                                        && !temp.isJustMade() && !currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 3).isJustMade()) {
                                    combine(currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 3), temp);
                                }

                                currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 2).setValue(temp.getValue());
                                updateIcon(currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 2));
                                temp.setValue(0);
                                updateIcon(temp);
                            } else if (j - 2 >= 0 && currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 2).getValue() == temp.getValue()
                                    && !temp.isJustMade() && !currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 2).isJustMade()) {
                                combine(currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 2), temp);
                            }
                            currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 1).setValue(temp.getValue());
                            updateIcon(currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 1));
                            temp.setValue(0);
                            updateIcon(temp);
                        } else if (j - 1 >= 0 && currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 1).getValue() == temp.getValue()
                                && !temp.isJustMade() && !currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 1).isJustMade()) {
                            combine(currentGame.getGameBoard().getCurrentBoard().get(i).get(j - 1), temp);
                        }
                    }
                }
                left = 1;
                break;

            case RIGHT:
                for (int i = 3; i >= 0; i--) {
                    for (int j = 2; j >= 0; j--) {
                        Tile temp = currentGame.getGameBoard().getCurrentBoard().get(i).get(j);
                        if (currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 1).getValue() == 0) {
                            if (j + 2 <= 3 && currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 2).getValue() == 0) {
                                if (j + 3 <= 3 && currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 3).getValue() == 0) {
                                    currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 3).setValue(temp.getValue());
                                    updateIcon(currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 3));
                                    temp.setValue(0);
                                    updateIcon(temp);
                                } else if (j + 3 <= 3 && currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 3).getValue() == temp.getValue()
                                        && !temp.isJustMade() && !currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 3).isJustMade()) {
                                    combine(currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 3), temp);
                                }

                                currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 2).setValue(temp.getValue());
                                updateIcon(currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 2));
                                temp.setValue(0);
                                updateIcon(temp);
                            } else if (j + 2 <= 3 && currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 2).getValue() == temp.getValue()
                                    && !temp.isJustMade() && !currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 2).isJustMade()) {
                                combine(currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 2), temp);
                            }
                            currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 1).setValue(temp.getValue());
                            updateIcon(currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 1));
                            temp.setValue(0);
                            updateIcon(temp);
                        } else if (j + 1 <= 3 && currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 1).getValue() == temp.getValue()
                                && !temp.isJustMade() && !currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 1).isJustMade()) {
                            combine(currentGame.getGameBoard().getCurrentBoard().get(i).get(j + 1), temp);
                        }
                    }
                }
                right = 1;
                break;

        }
        
        //Spawns a new random tile, checks win and lose conditions.
        updateIcon(currentGame.randTile());
        currentGame.getGameBoard().oldify();
        if (currentGame.checkWin()) {
            outcomeText.setText("You Win!");
            outcomeRectangle.setVisible(true);
        }
        if (currentGame.checkLose(up, down, left, right)) {
            outcomeText.setText("You Lose!");
            outcomeRectangle.setVisible(true);
            mediaPlay.stop();
        }

    }

    @FXML
    void initialize() {
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert tile0 != null : "fx:id=\"tile0\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert tile12 != null : "fx:id=\"tile12\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert tile11 != null : "fx:id=\"tile11\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert tile10 != null : "fx:id=\"tile10\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert tile9 != null : "fx:id=\"tile9\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert tile8 != null : "fx:id=\"tile8\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert tile4 != null : "fx:id=\"tile4\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert tile5 != null : "fx:id=\"tile5\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert tile6 != null : "fx:id=\"tile6\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert tile7 != null : "fx:id=\"tile7\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert tile3 != null : "fx:id=\"tile3\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert tile2 != null : "fx:id=\"tile2\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert tile1 != null : "fx:id=\"tile1\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert tile13 != null : "fx:id=\"tile13\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert tile14 != null : "fx:id=\"tile14\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert tile15 != null : "fx:id=\"tile15\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert restartButton != null : "fx:id=\"restartButton\" was not injected: check your FXML file 'MainGameFXML.fxml'.";
        assert outcomeText != null : "fx:id=\"outcomeText\" was not injected: check your FXML file 'MainGameFXML.fxml'.";

    }
}
