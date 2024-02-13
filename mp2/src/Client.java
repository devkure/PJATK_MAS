import java.util.ArrayList;
import java.util.Arrays;

public class Client extends Person{
    private ArrayList<String> companyInfo;
    private String email;
    private String phoneNumber;

    //asocjacja kwalifikowana
    private ArrayList<Project> projects = new ArrayList<Project>();

    public Client(PersonalData personalData, ArrayList<String> companyInfo, String email, String phoneNumber, Project project) throws ValidationException {
        super(personalData);
        setCompanyInfo(companyInfo);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        addProject(project);
    }

    // Asocjacja kwalifikowana

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void addProject(Project newProject) {
        if (this.projects.contains(newProject)) {
            return;
        }
        if (newProject.getClient() != this) {
            System.out.println("Client is not related to this project");
            return;
        }
        this.projects.add(newProject);
    }

    public void removeProject(Project project){
        if (!this.projects.contains(project)) {
            return;
        }
        this.projects.remove(project);
        //usuń klienta z projektu
    }

    //przykładowa metoda biznesowa do asocjacji kwalifikowanej
    public Project getProjectByClient(int clientId){
        Project tmp = null;
        for(Project p : projects){
            if(p.getClientId() == clientId){
                tmp = p;
            }
        }
        return tmp;
    }

    public ArrayList<String> getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(ArrayList<String> companyInfo) throws ValidationException {
        if (companyInfo == null) {
            throw new ValidationException("Company info list cannot be null");
        }
        this.companyInfo = companyInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ValidationException {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("Email cannot be empty or null");
        }
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws ValidationException {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new ValidationException("Phone number cannot be empty or null");
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "companyInfo=" + companyInfo +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' + +
                '}';
    }
}
