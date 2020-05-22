package gui.customcontrols;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class PlaySoundCommand implements Command{
    private String path;
    private Media media ;
    private MediaPlayer mediaPlayer ;
    public PlaySoundCommand(String soundPath)  {
        path = soundPath;
        media =  new Media(getClass().getResource(path).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
    }

    @Override
    public void execute()  {
        mediaPlayer.play();
    }
}
