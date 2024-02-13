import java.time.LocalDate;
import java.util.Date;

public class Task extends ObjectPlus {

    private String name;
    private LocalDate deadline;
    private String description; //Atr. opcjonalny
    private String difficulty;
    private String priority;
    private String projectName; //sygnatura projektu

    public Task(String name, LocalDate deadline, String description, String difficulty, String priority, String projectName) throws ValidationException {
        super();
        setName(name);
        setDeadline(deadline);
        setDescription(description);
        setDifficulty(difficulty);
        setPriority(priority);
        setProjectName(projectName);

    }

    public Task(String name, LocalDate deadline, String difficulty, String priority, String projectName) throws ValidationException {
        setName(name);
        setDeadline(deadline);
        setDifficulty(difficulty);
        setPriority(priority);
        setProjectName(projectName);
    }

    private void viewAllTasks() {

    }

    //Name

    public String getName() {
        return name;
    }

    public void setName(String name) throws ValidationException{
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Name cannot be empty.");
        }
        this.name = name;
    }

    // Deadline

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) throws ValidationException {
        if (deadline == null) {
            throw new ValidationException("Deadline cannot be empty.");
        }
        this.deadline = deadline;
    }

    // Description

    public String getDescription() {
        if(description == null || description.trim().isEmpty()) {
            return "No description";
        }
            return description;
    }

    public void setDescription(String description) throws ValidationException {
        if (description == null || description.trim().isEmpty()) {
            throw new ValidationException("Description cannot be empty.");
        }
        this.description = description;
    }

    // Difficulty

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) throws ValidationException {
        if (difficulty == null || difficulty.trim().isEmpty()) {
            throw new ValidationException("Difficulty cannot be empty.");
        }
        this.difficulty = difficulty;
    }

    // Priority

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) throws ValidationException {
        if (priority == null || priority.trim().isEmpty()) {
            throw new ValidationException("Priority cannot be empty.");
        }
        this.priority = priority;
    }

    // Project name


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) throws ValidationException {
        if (projectName == null | projectName.trim().isEmpty()){
            throw new ValidationException("Project name cannot be empty.");
        }
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", deadline=" + deadline +
                ", description='" + description + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", priority='" + priority + '\'' +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
