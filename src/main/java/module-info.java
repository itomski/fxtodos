module de.lubowiecki.gui.todos {
    requires javafx.controls;
    requires javafx.fxml;


    opens de.lubowiecki.gui.todos to javafx.fxml;
    exports de.lubowiecki.gui.todos;
}