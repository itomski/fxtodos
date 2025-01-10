package de.lubowiecki.gui.todos;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.*;
import java.util.function.Predicate;

public class TodoController implements Initializable {

    @FXML
    private TextField input;

    @FXML
    private ListView<Todo> todoList;

    // FXCollections ist eine Util-Klasse für Sammlungen, die in JavaFX nutzbar sind
    private ObservableList<Todo> data = FXCollections.observableArrayList();

    @FXML
    protected void showAll() {
        FilteredList<Todo> filteredList = new FilteredList<>(data);
        todoList.setItems(filteredList);
    }

    @FXML
    protected void showUndone() {
        Predicate<Todo> filter = t -> !t.isDone(); // Filtert die Erledigten raus
        FilteredList<Todo> filteredList = new FilteredList<>(data, filter);
        todoList.setItems(filteredList);
    }

    @FXML
    protected void showDone() {
        //Predicate<Todo> filter = t -> t.isDone(); // Filtert die Nicht-Erledigten raus
        //FilteredList<Todo> filteredList = new FilteredList<>(data, filter);
        //Predicate<Todo> filter = Todo::isDone; // Als Methodenreferenz
        FilteredList<Todo> filteredList = new FilteredList<>(data, Todo::isDone);
        todoList.setItems(filteredList);
    }


    // KeyEvent kann als Parameter in Methoden verwendet werden, die durch Betätigen von Tasten
    // ausgelöst werden
    @FXML // Nur mit dieser Annotation ist die Methode in FXML/SceneBuilder auswählbar
    protected void addTodo(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) { // Prüfen, ob ENTER betätigt wurde
            String title = input.getText().trim(); // Stuerzeichen/Leerzeichen an beiden Enden entfernen
            if (!title.isEmpty()) { // Prüfen, ob title leer ist
                data.add(new Todo(title)); // Neues To-do zu der Datensammlung  hinzufügen
                input.clear(); // Textfeld leeren
            }
        }
    }

    @FXML
    protected void toggleDone(KeyEvent event) {

        // TODO: Todo entfernen, wenn BACK_SPACE betätigt wurde

        if(event.getCode() == KeyCode.SPACE) { // Prüfen, ob SPACE betätigt wurde
            Todo selected = todoList.getSelectionModel().getSelectedItem();
            if(selected != null) // Prüfen, ob eine bestimmte Zeile ausgewählt wurde
                selected.toggleDone(); // Zustand des Todos ändern

            todoList.refresh(); // Anzeige aktuallisieren
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        todoList.setItems(data); // Datensammlung und Anzeigen werden miteinander verbunden
    }
}