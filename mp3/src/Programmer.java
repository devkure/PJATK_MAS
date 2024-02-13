import java.util.ArrayList;

public class Programmer extends TeamMember {
    String specialization;
    ArrayList<String> technologies;

    public Programmer(PersonalData personalData, String nName, String email, String role, String specialization, ArrayList<String> technologies, SeniorityLevel seniorityLevel, AccessLevel accessLevel) throws ValidationException {
        super(personalData, nName, email, role, seniorityLevel, accessLevel);
        setSpecialization(specialization);
        setTechnologies(technologies);
    }

    //Specjalizacja

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) throws ValidationException {
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new ValidationException("Specialization cannot be empty.");
        }
        this.specialization = specialization;
    }

    //Technologie

    public ArrayList<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(ArrayList<String> technologies) throws ValidationException {
        if (technologies == null || technologies.isEmpty()) {
            throw new ValidationException("List of technologies cannot be empty.");
        }
        this.technologies = technologies;
    }

    @Override
    public String toString() {
        return super.toString() + "Programmer{" +
                "specialization='" + specialization + '\'' +
                ", technologies=" + technologies +
                '}';
    }
}
