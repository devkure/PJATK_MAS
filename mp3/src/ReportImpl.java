import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;

public class ReportImpl extends ObjectPlus implements BugReport, ProgressReport {
    private static int idTracker;

    private int report_id;
    private LocalDate date;
    private String author;
    private String content;

    //Kompozycja z klasą project
    private Project project;

    //Overlapping
    private EnumSet<ReportType> reportTypes;
    //Progress report
    private ArrayList<Task> tasksCovered;
    //Bug report
    private String description;
    private String bugType;
    private String priority;

    public ReportImpl(Project project, LocalDate date, String author, String content, EnumSet<ReportType> reportTypes) throws ValidationException {
        super();
        setReport_id();
        setProject(project);
        this.date = date;
        this.author = author;
        this.content = content;

        //Overlapping
        setReportType(reportTypes);
    }

    @Override
    public void sendReport() {
        System.out.println("Generic report send");

        if(reportTypes.contains(ReportType.BUG_REPORT)){
            System.out.println("Additional info: *bug report*");
        }

        if(reportTypes.contains(ReportType.PROGRESS_REPORT)){
            System.out.println("Additional info: *percentage done*");
        }

    }

    //Enum report type
    private void setReportType(EnumSet<ReportType> reportTypes) throws ValidationException {
        if (reportTypes == null || reportTypes.isEmpty()) {
            throw new ValidationException("Event need at least one type.");
        }
        this.reportTypes = reportTypes;
    }

    //Progress report

    public ArrayList<Task> getTasksCovered() {
        return tasksCovered;
    }

    public void setTasksCovered(ArrayList<Task> tasksCovered) throws ValidationException {
        if(tasksCovered == null || tasksCovered.isEmpty()) {
            throw new ValidationException("Tasks cannot be empty or null");
        }
        this.tasksCovered = tasksCovered;
    }

    //Bug report

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws ValidationException {
        if(description == null || description.trim().isEmpty()){
            throw new ValidationException("Description cannot be null or empty!");
        }
        this.description = description;
    }

    public String getBugType() {
        return bugType;
    }

    public void setBugType(String bugType) throws ValidationException {
        if(bugType == null || bugType.trim().isEmpty()){
            throw new ValidationException("Bug type cannot be null or empty!");
        }
        this.bugType = bugType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) throws ValidationException {
        if(priority == null || priority.trim().isEmpty()){
            throw new ValidationException("Priority cannot be null or empty!");
        }
        this.priority = priority;
    }

    //Kompozycja cd...
    public void setProject(Project newProject) {
        if(newProject == null){
            System.out.println("Project cannot be null");
        }
        this.project = newProject;
        newProject.addReport(this);
    }

    public Project getProject() {
        return project;
    }

    public void delete() {
        if (this.project != null) {
            Project tmpProject = this.project;
            this.project = null;
            tmpProject.removeReport(this);
        }
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id() {
        this.report_id = idTracker++;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) throws ValidationException {
        if (date == null) {
            throw new ValidationException("Date cannot be null");
        }
        this.date = date;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) throws ValidationException {
        if (author == null || author.trim().isEmpty()) {
            throw new ValidationException("Author cannot be empty or null");
        }
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) throws ValidationException {
        if (content == null || content.trim().isEmpty()) {
            throw new ValidationException("Content cannot be empty or null");
        }
        this.content = content;
    }

    @Override
    public String toString() {
        return "Report{" +
                "report_id=" + report_id +
                ", date=" + date +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", project=" + project.getName() +
                '}';
    }
}
