package de.lubowiecki.gui.todos;

public class Todo {

    private String title;

    private boolean done;

    public Todo() {
    }

    public Todo(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone() {
        this.done = true;
    }

    public void toggleDone() {
        done = !done;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", title, (done ? "erledigt" : "nicht erledigt"));
    }
}
