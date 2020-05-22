package entities.adapter;

import entities.Player;
import entities.adapted.AdaptedPlayer;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PlayerAdapter extends XmlAdapter<AdaptedPlayer, Player>{

    @Override
    public Player unmarshal(AdaptedPlayer vt) throws Exception {
        return new Player(vt.getName(), vt.getUsername(), vt.getPassword(),vt.getBestClassicScore(),vt.getBestArcadeScore());
    }

    @Override
    public AdaptedPlayer marshal(Player bt) throws Exception {
        AdaptedPlayer adaptedPlayer = new AdaptedPlayer();
        adaptedPlayer.setName(bt.getName());
        adaptedPlayer.setBestClassicScore(bt.getBestClassicScore());
        adaptedPlayer.setBestArcadeScore(bt.getBestArcadeScore());
        adaptedPlayer.setPassword(bt.getPassword());
        adaptedPlayer.setUsername(bt.getUserName());
        return adaptedPlayer;
    }
    
}
