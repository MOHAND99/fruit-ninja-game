package entities;

import entities.adapter.PlayerAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(PlayerAdapter.class)
public class Player {

    private String name;
    private String userName;
    private String password;
    private int bestClassicScore;
    private int bestArcadeScore;

    public Player(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.bestClassicScore = 0;
        this.bestArcadeScore = 0;
    }

    public Player(String name, String userName, String password, int bestClassicScore, int bestArcadeScore) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.bestClassicScore = bestClassicScore;
        this.bestArcadeScore = bestArcadeScore;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    public int getBestArcadeScore() {
        return bestArcadeScore;
    }

    public void setBestArcadeScore(int bestArcadeScore) {
        this.bestArcadeScore = bestArcadeScore;
    }

    public void setBestClassicScore(int bestClassicScore) {
        this.bestClassicScore = bestClassicScore;
    }

    public void displayState() {
        System.out.println("name = " + name);
        System.out.println("user name = " + userName);
        System.out.println("password = " + password);
        System.out.println("best classic score = " + bestClassicScore);
        System.out.println("best arcade score = " + bestArcadeScore);
    }
    
}
