package entities.adapted;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdaptedPlayer {
    private String name;
    private String username;
    private String password;
    private int bestClassicScore;
    private int bestArcadeScore;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBestClassicScore() {
        return bestClassicScore;
    }

    public void setBestClassicScore(int bestClassicScore) {
        this.bestClassicScore = bestClassicScore;
    }

    public int getBestArcadeScore() {
        return bestArcadeScore;
    }

    public void setBestArcadeScore(int bestArcadeScore) {
        this.bestArcadeScore = bestArcadeScore;
    }
}
