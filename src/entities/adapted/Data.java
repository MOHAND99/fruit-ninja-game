package entities.adapted;

import entities.Player;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Data {

    @XmlElementWrapper(name = "players")
    @XmlElement(name = "player")
    private ArrayList<Player> players;

    public ArrayList<Player> getPlayers() {
        return players;
    }
}

