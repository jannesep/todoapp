import java.time.LocalDate;

public class TodoItem {

    private LocalDate dueDate;
    private String description;
    private boolean isDone;

    public TodoItem(LocalDate dueDate, String description, boolean isDone) {
        this.dueDate = dueDate;
        this.description = description;
        this.isDone = isDone;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
