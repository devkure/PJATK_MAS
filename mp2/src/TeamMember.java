import java.util.ArrayList;

public class TeamMember extends Person {

    private static int idTracker = 0; // Atrybut klasowy

    private int teamMemberID;
    private String nName;
    private String email;
    private String role;

//    private ArrayList<Task> assignedTasks = new ArrayList<Task>();

    private ArrayList<Quest> quests = new ArrayList<Quest>(); //asocjacja z atrybutem

    private Project project; //Asocjacja "zwykła" member -> project

    public TeamMember(PersonalData personalData, String nName, String email, String role) throws ValidationException {
        super(personalData);
        setTeamMemberID();
        setnName(nName);
        setEmail(email);
        setRole(role);
    }

    public Project getProject() {
        return project;
    }

    //Asocjacja z atrybutem

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public void addQuest(Quest quest) throws ValidationException {
        if (quest == null) {
            throw new ValidationException("Quest cannot be null.");
        }

        if (this.quests.contains(quest)) {
            return;
        }

        if (quest.getTeamMember() != this) {
            throw new ValidationException("Quest is not related to the team member");
        }
        this.quests.add(quest);
        quest.setTeamMember(this);
    }

    public void removeQuest(Quest quest) {

        if (!this.quests.contains(quest)) {
            return;
        }

        this.quests.remove(quest);
        quest.delete();

    }

    //Asocjacja "zwykła" member -> project
    public void setProject(Project newProject) {
        if(this.project == newProject){
            return;
        }

        if(this.project != null){
            Project tmpProject = this.project;
            this.project = null;
            tmpProject.removeTeamMember(this);
           if(newProject != null){
               this.project = newProject;
               newProject.addTeamMember(this);
           }
        }else {
            this.project = newProject;
        }
        if(newProject == null){
            return;
        }

        this.project = newProject;
//        newProject.addTeamMember(this);
    }

    /*public void addTask(Task task) {
        assignedTasks.add(task);
    }*/


    private void viewAvailableTasks() {

    }

    //Metoda służy do outputowania przypisanych memberowi tasków
    /*public ArrayList<StringBuilder> viewAssignedTasks() {
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
    }*/

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

    /*public ArrayList<Task> getAssignedTasks() {
        return assignedTasks;
    }*/


    @Override
    public String toString() {
        return "TeamMember{" +
                "teamMemberID=" + teamMemberID +
                ", nName='" + nName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", quests=" + quests +
                '}';
    }
}
