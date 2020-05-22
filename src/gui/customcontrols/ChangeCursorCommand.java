package gui.customcontrols;

import entities.Cursor;

public class ChangeCursorCommand implements Command {

    Cursor cursor;

    public ChangeCursorCommand(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public void execute() {
        cursor.changeCursor();
    }
}
