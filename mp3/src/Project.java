import java.time.LocalDate;
import java.util.*;

public class Project extends ObjectPlus {
    private String name;
    private String genre;
    private String phase;
    private LocalDate dateOfStart;
    private LocalDate eta; // Atrybut pochodny //niech to bedzie getter

    //Lista członków zespołu, którzy uczestniczą w projekcie.
    //Wykorzystywana min. do wyłuskania informacji o taskach, na podstawie ich sygnatury projektu.
    private ArrayList<TeamMember> projectMembers = new ArrayList<TeamMember>(); //atr. powtarzalny

    //Kompozycja z klasą Report:
    private ArrayList<ReportImpl> reports = new ArrayList<>();

    //Asocjacja kwalifikowana
    private Client client;
    private int clientId;

    public Project(String name, String genre, String phase, LocalDate dateOfStart) throws ValidationException {
        super();
        setName(name);
        setGenre(genre);
        setPhase(phase);
        setDateOfStart(dateOfStart);
    }

    //Asocjacja kwalifikowana

    public int getClientId(){
        return clientId;
    }

    public Client getClient() {
        return client;
    }

    public void setClientId(int clientId){
        if(clientId <= 0){
            System.out.println("Id must be > 0");
            return;
        }
        this.clientId = clientId;
    }

    public void setClient(Client client){
        if(client == null){
            System.out.println("Client cannot be empty!");
            return;
        }
        if(this.client != null){
            Client tmpClient = this.client;
            this.client = null;
            tmpClient.removeProject(this);
            if(client != null){
                this.client = client;
                client.addProject(this);
            }
        }else {
            this.client = client;
        }
        if(client == null){
            return;
        }
        this.client = client;
        client.addProject(this);
    }

    //Asocjacja TeamMember -> Project cd.
    public void addTeamMember(TeamMember member) {
        if(member == null){
            return;
        }
        if(this.projectMembers.contains(member)){
            System.out.println("This member already exists on the project members list");
            return;
        }
        this.projectMembers.add(member);
        member.setProject(this);
    }

    //Metoda służąca do wyrzucenia membera z projektu
    public void removeTeamMember(TeamMember member) {
        projectMembers.remove(member);
        member.setProject(null);
    }

    //Kompozycja project - report
    public void addReport(ReportImpl newReport){
        if(newReport == null){
            return;
        }
        if(newReport.getProject() != this){
            System.out.println("Report is not related to this particular project");
        }
        this.reports.add(newReport);
    }

    public void removeReport(ReportImpl report){
        if(!this.reports.contains(report)){
            return;
        }
        this.reports.remove(report);
        report.delete();
    }


    //Metoda klasowa, służy do pokazania jakie role (memberów) biorą udział w projekcie
    public static void getProjectRoles() throws Exception {
        List<ObjectPlus> extent = ObjectPlus.getFromExtent(Project.class);
        //Mapa służąca do przeliczenia ile razy unikatowa rola wystąpiła
        Map<String, Integer> roleCounts = new HashMap<>();
        for (ObjectPlus proj : extent) {
            System.out.println(proj);
            Project p = (Project) proj;

            //Iterujemy po każdym memberze
            for (TeamMember memb : p.getProjectMembers()){
                //Zbieramy jego role
                String role = memb.getRole();
                //Metoda zwraca wartość związaną z kluczem - O ILE ISTNIEJE w mapie.
                //Jeśli NIE ISTNIEJE, to zwraca defaultowa wartość - czyli u mnie 0.
                //W kontekście powyższego kodu, metoda jest używana do wyłuskania obecnej ilości ról
                //Jeśli rola nie była wcześniej *"widziana"* - jej wartością będzie 0
                //Jeśli była, to jej wartość jest wyłuskiwana z mapy i inkrementowana o 1
                int count = roleCounts.getOrDefault(role, 0);
                roleCounts.put(role, count + 1);
            }
            // wydrukuj mapę
            for (Map.Entry<String, Integer> entry : roleCounts.entrySet()) {
                System.out.println("Member Role: " + entry.getKey() + ", in the project: " + entry.getValue());
            }
        }
    }

    ///Name

    public String getName() {
        return name;
    }

    public void setName(String name) throws ValidationException {
        if (name == null || name.isEmpty()) {
            throw new ValidationException("Name cannot be empty.");
        }
        this.name = name;
    }

    //Genre

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) throws ValidationException {
        if (genre == null || genre.isEmpty()) {
            throw new ValidationException("Genre cannot be empty.");
        }
        this.genre = genre;
    }

    //Phase

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) throws ValidationException {
        if (phase == null || phase.isEmpty()) {
            throw new ValidationException("Phase cannot be empty.");
        }
        this.phase = phase;
    }

    //Date of start

    public LocalDate getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(LocalDate dateOfStart) throws ValidationException {
        if (dateOfStart == null) {
            throw new ValidationException("Date of start cannot be null.");
        }
        this.dateOfStart = dateOfStart;
    }

    //Eta

    //Atrybut pochodny
    //TODO LATER
    /*public LocalDate getEta(String signature) {
        LocalDate tmp = dateOfStart;
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
        //Wyłuskiwanie daty
        //Iteracja po każdym członku projektu
        for (TeamMember member : projectMembers) {
            //Iteracja po taskach każdego członka
            for (Task task : member.getAssignedTasks()) {
                //Jeżeli sygnatura projektu jest wskazaną sygnaturą projektu, dodaj do listy tasków
                if (task.getProjectName() == signature) {
                    tasks.add(task);
                }
            }
        }*/

        /*//Teraz przeiterujmy po tablicy aktualnych tasków
        for (Task task : tasks) {
            //i dodajmy wszystkie deadlin'y do listy dat
            LocalDate tempDate = task.getDeadline();
            dates.add(tempDate);
        }*/

        // znajdź najpóźniejszą datę w liście
        /*
         * Notka wyjaśniająca działanie poniższej linijki:
         * toEpochDay z klasy LocalDate, zwraca liczbę dni od 1 stycznia 1970 roku.
         * tworzymy Comparator, który porównuje obiekty typu LocalDate na podstawie liczby dni od 1 stycznia 1970 roku
         * Collections.max zwraca największą datę w liście dates na podstawie stworzonego Comparatora, którą przypisujemy do zmiennej latestDate.
         */
        /*LocalDate latestDate = Collections.max(dates, Comparator.comparing(LocalDate::toEpochDay));

        //Tutaj będziemy mogli w przyszłości jeszcze dalej modyfikować eta, wiec zostawiam to na razie tak
        eta = latestDate;
        return eta;
    }*/

    public void setEta(LocalDate eta) throws ValidationException {
        if (eta == null) {
            throw new ValidationException("ETA cannot be null.");
        }
        this.eta = eta;
    }

    //Project members

    public ArrayList<TeamMember> getProjectMembers() {
        return projectMembers;
    }

    /*public void setProjectMembers(ArrayList<TeamMember> projectMembers) throws ValidationException {
        if (projectMembers == null || projectMembers.isEmpty()) {
            throw new ValidationException("Project members cannot be empty.");
        }
        this.projectMembers = projectMembers;
    }*/

    //Report

    public ArrayList<ReportImpl> getReports() {
        return reports;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", phase='" + phase + '\'' +
                ", dateOfStart=" + dateOfStart +
                ", eta=" + eta +
                ", projectMembers=" + projectMembers +
                ", reports=" + reports +
                ", client=" + client +
                ", clientId=" + clientId +
                '}';
    }
}
