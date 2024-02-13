import java.util.ArrayList;

public class TeamMember extends Person {

    private static int idTracker = 0; // Atrybut klasowy

    private int teamMemberID;
    private String nName;
    private String email;
    private String role;

    ArrayList<Task> assignedTasks = new ArrayList<Task>(); //atr. powtarzalny

    public TeamMember(PersonalData personalData, String nName, String email, String role) throws ValidationException {
        super(personalData);
        setTeamMemberID();
        setnName(nName);
        setEmail(email);
        setRole(role);
    }

    public void addTask(Task task) {
        assignedTasks.add(task);
    }


    private void viewAvailableTasks() {

    }

    //Metoda służy do outputowania przypisanych memberowi tasków
    public ArrayList<StringBuilder> viewAssignedTasks() {
        StringBuilder personal = new StringBuilder();
        StringBuilder note = new StringBuilder();
        ArrayList<StringBuilder> assignedTaskList = new ArrayList<StringBuilder>();

        personal.append(nName + ", ");
        for (Task task : assignedTasks) {
            note.append(task.getProjectName() + ", ").append(task.getName() + ", ").append
                    (task.getDeadline() + ", ").append(task.getDescription() + ", ").append
                    (task.getDifficulty() + ", ").append(task.getPriority()).append("\n");
        }
        assignedTaskList.add(personal);
        assignedTaskList.add(note);
        return assignedTaskList;
    }

    private void taskAccept() {

    }

    //ID

    public int getTeamMemberID() {
        return teamMemberID;
    }

    public void setTeamMemberID() {
        this.teamMemberID = idTracker++;
    }

    //nick

    public String getnName() {
        return nName;
    }

    public void setnName(String nName) throws ValidationException {
        if (nName == null || nName.trim().isEmpty()) {
            throw new ValidationException("Nick cannot be empty or null");
        }
        this.nName = nName;
    }

    //Email

    //Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ValidationException {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("Email cannot be empty or null");
        }
        //ew można zrobić tu regex sprawdzający popr. emaila to do later ...
        this.email = email;
    }

    //Rola
    public String getRole() {
        return role;
    }

    public void setRole(String role) throws ValidationException {
        if (role == null || role.trim().isEmpty()) {
            throw new ValidationException("Role cannot be empty or null");
        }
        this.role = role;
    }

    //Assigned Tasks

    public ArrayList<Task> getAssignedTasks() {
        return assignedTasks;
    }


    @Override
    public String toString() {
        return "TeamMember{" +
                "teamMemberID=" + teamMemberID +
                ", nName='" + nName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", assignedTasks=" + assignedTasks +
                '}';
    }
}
