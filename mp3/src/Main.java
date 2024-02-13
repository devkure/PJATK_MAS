import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception {
        test();
        ObjectPlus.save();
    }

    public static void test() throws ValidationException {
        System.out.println("Test report");
        Project amadeus = new Project("AMADEUS", "Hack N Slash", "Designing", LocalDate.now());

        EnumSet<ReportType> progressSet = EnumSet.of(ReportType.PROGRESS_REPORT);
        EnumSet<ReportType> bugSet = EnumSet.of(ReportType.BUG_REPORT);
        EnumSet<ReportType> progressBugSet = EnumSet.of(ReportType.PROGRESS_REPORT, ReportType.BUG_REPORT);

        System.out.println("Progress report:" + "\n");
        ReportImpl prog = new ReportImpl(amadeus, LocalDate.now(), "Tobi", "It works", progressSet);
        prog.sendReport();
        System.out.println();

        System.out.println("Bug report:");
        ReportImpl bug = new ReportImpl(amadeus, LocalDate.now(), "Tobi", "Character doesnt instantiate", bugSet);
        bug.sendReport();
        System.out.println();

        System.out.println("Progress & Bug report:");
        ReportImpl progressBug = new ReportImpl(amadeus, LocalDate.now(), "Tobi", "Character doesnt instantiate", progressBugSet);
        progressBug.sendReport();
        System.out.println();

        System.out.println("Test artist");

        PersonalData personalData1 = new PersonalData("Tomasz", "Bęczycki", LocalDate.now());
        ArrayList<String> a1 = new ArrayList<>();
        a1.add("Krita");
        a1.add("Photoshop");
        Artist2DImpl artist2D = new Artist2DImpl(personalData1, "tobęcz" ,"tobęczycki@gmail.com" ,"Artist", "Concept art", a1, "Huion Kamvas", "www.artstation-tbęczycki.com", SeniorityLevel.SENIOR, AccessLevel.VIEW);
        artist2D.getLatestWorks();

        PersonalData personalData2 = new PersonalData("Jan", "Brzeziński", LocalDate.of(1999, 12, 01));
        ArrayList<String> a2 = new ArrayList<>();
        a2.add("Substance");
        a2.add("Blender");
        a2.add("UE5");
        Artist3D artist3d = new Artist3D(personalData2, "Tobi" ,"s20414@pjwstk.edu.pl" ,"Artist", "Environment", a2, "Stylized", "www.artstation-3dkure.com", SeniorityLevel.MID, AccessLevel.ADMIN);
        artist3d.getLatestWorks();

        PersonalData personalData3 = new PersonalData("Artur", "Kuś", LocalDate.now());
        ArrayList<String> a3 = new ArrayList<>();
        a3.add("Blender");
        a3.add("Stable Diffusion");
        a3.add("Substance");
        a3.add("UE5");
        a3.add("Photoshop");
        ArtGeneralist generalist = new ArtGeneralist(personalData3, "Bones", "bbones@gmail.com", "Magic man", "Everything at once", a3, "Stylized", "www.youtube/tobikure/2137.avi", "Huion Kamvas 13", "https://www.artstation.com/artkure", SeniorityLevel.JUNIOR, AccessLevel.EDIT);
        generalist.getLatestWorks();

        System.out.println("Test seniority level");
        PersonalData p4 = new PersonalData("Mikołaj", "Ziółek", LocalDate.of(1998, 05, 05));
        ArrayList<String> tech = new ArrayList<String>();
        tech.add("Unity Engine");
        tech.add("C#");
        tech.add("Git");
        Programmer p1 = new Programmer(p4, "Mikosz08", "mikosz08@gmail.com", "Magic man", "Doing things", tech, SeniorityLevel.MID, AccessLevel.ADMIN);
        System.out.println(p1);

        System.out.println("Test Dynamic / Access");
        p1.changeAccess(AccessLevel.EDIT);
        /*System.out.println("Test klasy team member: " + "\n");

        // #1
        System.out.println("Stworzono team member: " + "\n");
        LocalDate d1 = LocalDate.of(1999, 12, 01);
        PersonalData personalData1 = new PersonalData("Jan", "Brzeziński", d1);
        TeamMember member1 = new TeamMember(personalData1, "Tobi", "s20414@pjwstk.edu.pl", "Autist");
        System.out.println(member1);

        // #2
        System.out.println("Stworzono team member: " + "\n");
        LocalDate d2 = LocalDate.of(1999, 12, 01);
        PersonalData personalData2 = new PersonalData("Jan", "Brzeziński", d2);
        TeamMember member2 = new TeamMember(personalData1, "Zły brat bliźniak", "s20414@pjwstk.edu.pl", "Programmer");
        System.out.println(member2);

        // Project
        System.out.println("Stworzono projekt: ");
        Project amadeus = new Project("AMADEUS", "Hack N Slash", "Designing", LocalDate.now());
        System.out.println(amadeus);

        // Asocjacja zwykła team member - project

        System.out.println("\n" + "Test zwykłej asocjacji m-dzy Member - Project: " + "\n");

        System.out.println("Dodaje member1 do projektu:" + "\n");
        amadeus.addTeamMember(member1);

        System.out.println("Info o projekcie: ");
        System.out.println("W projekcie " + amadeus.getName() + " znajduje się " + member1.getnName() + ": " + amadeus.getProjectMembers().contains(member1));

        System.out.println("Info o memberze: ");
        System.out.println("Member " + member1.getnName() + " uczestniczy w projekcie: " + member1.getProject().getName());

        //Asocjacja z atrybutem

        System.out.println("\n" + "Test asocjacji z atrybutem TeamMember - Task = Quest " + "\n");

        System.out.println("Tworze obiekt task:" + "\n");
        Task task1 = new Task("Enviro", LocalDate.now(), "Model the environment", "Hard", "MUST", "AMADEUS");
        System.out.println(task1);

        System.out.println("Tworze obiekt klasy asocjacyjnej quest: " + "\n");
        Quest quest1 = new Quest("WIP", LocalDate.now(), task1, member1);
        System.out.println("Info o Quest:");
        System.out.println(quest1 + " łączy: " + quest1.getTask().getName() + " z " + quest1.getTeamMember().getnName());

        //Kompozycja Project - Report

        System.out.println("\n" + "Test kompozycji Project - Report ");
        System.out.println("Tworzę obiekt klasy report: " + "\n");
        ReportImpl report = new ReportImpl(amadeus, LocalDate.now(), "Jan Brzeziński / Tobi", "Zepsuło siem i masy są za trudne");
        System.out.println("Pomyślnie utworzono obiekt klasy report");
        System.out.println(report);
        System.out.println("Usuńmy informację o reporcie:" + "\n");
        System.out.println(amadeus.getReports());
        report.delete();
        System.out.println("Pomyślnie usunięto informacje o reporcie: ");
        System.out.println(amadeus.getReports());

        //Asocjacja kwalifikowana - Project - Client

        System.out.println("\n" + "Test asocjacji kwalifikowanej Project - Client");

        System.out.println("Tworzę nowy projekt: ");
        Project oneoften = new Project("1 of 10", "Other", "Pre-Alpha", LocalDate.now());
        System.out.println(oneoften);

        System.out.println("Tworzę obiekt klasy klient: ");
        LocalDate d3 = LocalDate.of(1943, 07, 16);
        PersonalData personalData3 = new PersonalData("Tadeusz", "Sznuk", d3);
        ArrayList<String> compInf = new ArrayList<String>();
        compInf.add("Telewizja Polska S.A");
        Client client = new Client(personalData3, compInf, "tsznuk@gmail.com", "666-666-666", oneoften);

        System.out.println("Przypisuję klienta do projektu:");
        oneoften.setClient(client);

        System.out.println("Informacje o kliencie: ");
        System.out.println(client);
        System.out.println("Lista projektów klienta:");
        System.out.println(client.getProjects());
        System.out.println("Informacje o projekcie:");
        System.out.println("Właścicielem projektu '" + oneoften.getName() + "'" + " jest " + oneoften.getClient().getPersonalData());

        System.out.println("Metoda biznesowa get project by client:");
        System.out.println(client.getProjectByClient(0).getName());
*/
    }
}
