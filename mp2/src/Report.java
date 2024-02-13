import java.time.LocalDate;

public class Report extends ObjectPlus{
    private static int idTracker;

    private int report_id;
    private LocalDate date;
    private String author;
    private String content;

    //Kompozycja z klasą project
    private Project project;

    public Report(Project project, LocalDate date, String author, String content) {
        super();
        setReport_id();
        setProject(project);
        this.date = date;
        this.author = author;
        this.content = content;
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
