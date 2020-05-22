package gui;


import entities.Player;

import entities.adapted.Data;
import gui.controllers.Authentication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Font.loadFont(Main.class.getResourceAsStream("/resources/fonts/greeting-font.ttf"), 12);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
//        primaryStage.setFullScreen(true);
//        Dimensions dimensions = new Dimensions((int) Screen.getPrimary().getBounds().getHeight(), (int) Screen.getPrimary().getBounds().getWidth());
//        Game game = GameModeFactory.getGame("arcadeGame", dimensions);
//        GamePane gamePane = new GamePane(game);
//        gamePane.prepareGame();
   //  //      Players players = Players.getInstance();
    //players.addPlayer(new Player("a", "a", "a"));
        //(new Authentication()).getData().getPlayers().add(new Player("a","a","a"));
        Parent root = FXMLLoader.load(getClass().getResource("/gui/views/intro.fxml"));
        primaryStage.setTitle("Fruit Ninja");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

}
