import java.util.Date;

public class TodoItem {

    private Integer id;
    private Date dueDate;
    private String description;
    private boolean isDone;


    public TodoItem(Integer id, Date dueDate, String description, boolean isDone) {
        this.id = id;
        this.dueDate = dueDate;
        this.description = description;
        this.isDone = isDone;
    }

    public TodoItem(Date dueDate, String description, boolean isDone) {
        this.dueDate = dueDate;
        this.description = description;
        this.isDone = isDone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
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

    @Override
    public String toString() {
        return TodoApp.dateFormat.format(getDueDate()) + " - " + getDescription();
    }
}
