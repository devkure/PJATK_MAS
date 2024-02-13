import java.util.ArrayList;
import java.util.Date;

public class Quest extends ObjectPlus {
    private String completionStatus;
    private Date dateOfAssignment;
    private String difficulty; //optional
    private ArrayList<Task> tasks;
    private ArrayList<TeamMember> members;

    public Quest(String completionStatus, Date dateOfAssignment, String difficulty, ArrayList<Task> tasks, ArrayList<TeamMember> members) {
        super();
        this.completionStatus = completionStatus;
        this.dateOfAssignment = dateOfAssignment;
        this.difficulty = difficulty;
        this.tasks = tasks;
        this.members = members;
    }

    public Quest(String completionStatus, Date dateOfAssignment, ArrayList<Task> tasks, ArrayList<TeamMember> members) {
        this.completionStatus = completionStatus;
        this.dateOfAssignment = dateOfAssignment;
        this.tasks = tasks;
        this.members = members;
    }
}
