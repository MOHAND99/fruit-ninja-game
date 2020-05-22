package gui.controllers;

import entities.adapted.Data;
import entities.Player;
import gui.StoreDataMethods;
import gui.views.Alert;
import javax.xml.bind.JAXBException;

public class Authentication {
    
    private final Data data;

    public Authentication() throws JAXBException {
        this.data = StoreDataMethods.loadDataFromXmlFile("Data.xml");
    }
    public boolean isValid(String password1, String password2) {
        return password1.equals(password2);
    }
    public boolean allDataFilled(String name, String userName, String password1, String password2) {
        if (!name.isEmpty() && !userName.isEmpty() && !password1.isEmpty() && !password2.isEmpty()) {
            return true;
        }
        return false;
    }
    public boolean isValidAccount(String name, String userName, String password, String confirmationPassword) {
        for(Player player: data.getPlayers()) {
            if(player.getUserName().equals(userName)) {
                Alert.showMessage("user already exists!");
                return false;
            }
        }

        if (!allDataFilled(name, userName, password, confirmationPassword)) {
            Alert.showMessage("please fill all the fields");
            return false;
        } else if (!isValid(password, confirmationPassword)) {
            Alert.showMessage("passwords don't match");
            return false;
        } else {
            Alert.showMessage("you have signed up successfully");
            return true;
        }
    }

    private boolean isValidPlayer(Player player, String userName, String password) {
        return (player.getUserName().equals(userName) && player.getPassword().equals(password));
    }

    public boolean userExists(String userName, String password) {

        for(Player player: data.getPlayers()) {
            if(isValidPlayer(player, userName, password)) {
                Alert.showMessage("Welcome to fruit ninja");
                return true;
            }
        }

        if(userName.isEmpty() || password.isEmpty()) {
            Alert.showMessage("please fill all the fields");
            return false;
        } else {
            Alert.showMessage("user name or password are incorrect");
            return false;
        }
    }
    
    public Data getData() {
        return data;
    }

}
