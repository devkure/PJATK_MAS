import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        test();
        ObjectPlus.save();
    }

    public static void test() throws ValidationException {
        System.out.println("Test klasy team member: " + "\n");

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
        Report report = new Report(amadeus, LocalDate.now(), "Jan Brzeziński / Tobi", "Zepsuło siem i masy są za trudne");
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

    }
}
