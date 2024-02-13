import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Quest extends ObjectPlus {
    private String completionStatus;
    private LocalDate dateOfAssignment;
    //Asocjacja z atrybutem
    private Task task;
    private TeamMember teamMember;

    public Quest(String completionStatus, LocalDate dateOfAssignment, Task task, TeamMember teamMember) throws ValidationException {
        super();
        this.completionStatus = completionStatus;
        this.dateOfAssignment = dateOfAssignment;
        setTask(task);
        setTeamMember(teamMember);
    }

    public String getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(String completionStatus) throws ValidationException {
        if (completionStatus == null || completionStatus.trim().isEmpty()) {
            throw new ValidationException("Completion status cannot be empty or null");
        }
        this.completionStatus = completionStatus;
    }

    public LocalDate getDateOfAssignment() {
        return dateOfAssignment;
    }

    public void setDateOfAssignment(LocalDate dateOfAssignment) throws ValidationException {
        if (dateOfAssignment == null) {
            throw new ValidationException("Date of assignment cannot be null");
        }
        this.dateOfAssignment = dateOfAssignment;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        if(task == null){
            return;
        }
        this.task = task;
        task.addQuest(this);
    }

    public TeamMember getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(TeamMember teamMember) throws ValidationException {
        if(teamMember == null){
            return;
        }
        this.teamMember = teamMember;
        teamMember.addQuest(this);
    }

    public void delete() {
        if (this.teamMember != null) {
            TeamMember tmpTeamMember = this.teamMember;
            this.teamMember = null;
            tmpTeamMember.removeQuest(this);
        }
        if (this.task != null) {
            Task tmpPlayer = this.task;
            this.task = null;
            tmpPlayer.removeQuest(this);
        }
    }

    @Override
    public String toString() {
        return "Quest{" +
                "completionStatus='" + completionStatus + '\'' +
                ", dateOfAssignment=" + dateOfAssignment +
                ", task=" + task.getName() +
                ", teamMember=" + teamMember.getnName() +
                '}';
    }
}
