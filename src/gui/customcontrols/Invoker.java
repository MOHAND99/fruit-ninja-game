package gui.customcontrols;

//Invoker
public class Invoker {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void slideMouse() {
        command.execute();
    }
}
