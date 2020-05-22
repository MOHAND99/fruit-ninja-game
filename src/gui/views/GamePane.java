package gui.views;

import entities.Cursor;
import entities.Player;
import entities.Blade;
import fruitninja.Game;
import gui.customcontrols.Invoker;
import gui.customcontrols.PlaySoundCommand;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.lang.reflect.InvocationTargetException;

public abstract class GamePane extends Pane{

    protected HBox heartsBox;
    protected VBox wrappingBox;
    protected Label bestScore;
    protected HBox scoreBox;
    protected Label scoreLabel;
    protected Player player;
    private ImageView sliceEffect;
    private Canvas canvas;
    private Blade blade;
    private Invoker invoker;
    private Cursor cursor;
    private Scene gameScene;
    private Game game;

    public GamePane(Game game, Player player){
        super();
        this.player = player;
        this.game = game;
        wrappingBox = new VBox();
        wrappingBox.setPadding(new Insets(15, 0, 0, 15));
        sliceEffect = new ImageView(new Image("/resources/images/effects/slice-effect.png"));
        scoreLabel = new Label("Score : 0");
        scoreLabel.setStyle("-fx-text-fill: linear-gradient( #ffa31a, #e68a00); -fx-font-size: 40; -fx-font-weight: bold");
        scoreBox = new HBox(scoreLabel);
        scoreBox.setPadding(new Insets(15, 0, 0, 15));
        canvas = new Canvas(Game.getDimensions().getWidth(), Game.getDimensions().getHeight());
        blade = new Blade(this);
        blade.customizeSword(Color.WHITE, 5);
        invoker = new Invoker();
        getChildren().add(canvas);
        setStyle("-fx-background-image: url('/resources/images/cover.jpg'); -fx-background-size: cover");
        setOnDragDetected(e -> slicedEvent()); // https://stackoverflow.com/questions/50008182/detect-enter-event-while-mouse-is-pressed-javafx
    }

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
        cursor = new Cursor(gameScene);
    }

    public ImageView getSliceEffect() {
        return sliceEffect;
    }

    public abstract void prepareGame() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException;//TODO put the prepare game in a new Arcade and Classic classes


    private void slicedEvent(){
        this.startFullDrag();
        invoker.setCommand(new PlaySoundCommand("/resources/sound/blade-sound-effect.mp3"));
        invoker.slideMouse();
//        mouse.setCommand(new ChangeCursorCommand(cursor));
//        mouse.slideMouse();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }
}
