import java.util.ArrayList;

public class Artist extends TeamMember {
    private String specialization;
    private ArrayList<String> software;

    public Artist(PersonalData personalData, String nName, String email, String role, String specialization, ArrayList<String> software) throws ValidationException {
        super(personalData, nName, email, role);
        setSpecialization(specialization);
        setSoftware(software);
    }

    //Przesłonięcie metody viewassignedtasks z klasy teammember
    /*public ArrayList<StringBuilder> viewAssignedTasks() {
        ArrayList<StringBuilder> note = super.viewAssignedTasks();
        note.get(0).append(specialization + ", ").append(software);
        return note;
    }*/

    //specjalizacja

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) throws ValidationException {
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new ValidationException("Specialization cannot be empty.");
        }
        this.specialization = specialization;
    }

    //software

    public ArrayList<String> getSoftware() {
        return software;
    }

    public void setSoftware(ArrayList<String> software) throws ValidationException {
        if (software == null || software.isEmpty()) {
            throw new ValidationException("Software list cannot be empty.");
        }
        this.software = software;
    }

    @Override
    public String toString() {
        return super.toString() + "Artist{" +
                "specialization='" + specialization + '\'' +
                ", software=" + software +
                '}';
    }
}
