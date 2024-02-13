import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        //save();
        load();
    }

    //DEBUG / PREZENTACJA SCENARIUSZA POZYTYWNEGO

    static void save() throws Exception {
        //Przykład dla hierarchii Team Member -> Programmer/Artist

        PersonalData personal1 = new PersonalData("Jan", "Brzeziński", LocalDate.of(1999, 12, 1));
        PersonalData personal2 = new PersonalData("Mikołaj", "Zet", LocalDate.of(1987, 5, 12));

        TeamMember m1 = new TeamMember(personal1, "Tobi", "s20414@pjwstk.edu.pl", "3D Artist");
        TeamMember m2 = new TeamMember(personal2, "Ziolek", "kochamjave@gmail.com", "Programmer");

        ArrayList<String> technologiesM1 = new ArrayList<String>();
        technologiesM1.add("Blender");
        technologiesM1.add("Unity");

        ArrayList<String> technologiesM2 = new ArrayList<String>();
        technologiesM2.add("C#");
        technologiesM2.add("Unity");

        Artist a1 = new Artist(personal1, "Tobi", "s20414@pjwstk.edu.pl", "3D Artist", "3D Environments", technologiesM1);
        Programmer p1 = new Programmer(personal2, "Ziolek", "kochamjave@gmail.com", "Programmer", "C#", technologiesM2);

        //Task
        LocalDate d1 = LocalDate.of(2023, 4, 25);
        LocalDate d2 = LocalDate.of(2023, 4, 30);

        Task task1 = new Task("Enviro", d1, "Finish the environment", "Hard", "MUST", "PROJECT:AMADEUS");
        Task task2 = new Task("Movement system", d2, "Hard", "MUST", "PROJECT:AMADEUS");

        //ustawienie assigned tasks dla memberów:
        a1.addTask(task1);
        p1.addTask(task2);

        //Project
        ArrayList<TeamMember> projectMembers = new ArrayList<TeamMember>();
        projectMembers.add(a1);
        projectMembers.add(p1);

        Project amadeus = new Project("PROJECT:AMADEUS", "Hack&Slash", "Initial", LocalDate.of(2022, 7, 1), projectMembers);

        //Atrybut pochodny
        System.out.println(amadeus.getEta("PROJECT:AMADEUS"));

        //Metoda klasowa
        Project.getProjectRoles();

        //Przesłonięcie metody
        for (StringBuilder sb : a1.viewAssignedTasks()) {
            System.out.println(sb.toString());
        }

        ObjectPlus.save();
    }

    static void load() throws Exception {
        ObjectPlus.load();

        ObjectPlus.showExtent(Programmer.class);
        ObjectPlus.showExtent(Artist.class);
        ObjectPlus.showExtent(Task.class);
        ObjectPlus.showExtent(Project.class);
    }
}
