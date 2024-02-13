import java.io.Serializable;
import java.time.LocalDate;

public class PersonalData implements Serializable {
    private String fName;
    private String lName;
    private LocalDate birthDate;

    public PersonalData(String fName, String lName, LocalDate birthDate) throws ValidationException {
        setfName(fName);
        setlName(lName);
        setBirthDate(birthDate);
    }

    // fname

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) throws ValidationException {
        if (fName == null || fName.isEmpty()) {
            throw new ValidationException("Name cannot be empty.");
        }
        this.fName = fName;
    }

    // lname

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) throws ValidationException {
        if (lName == null || lName.isEmpty()) {
            throw new ValidationException("Surname cannot be empty.");
        }
        this.lName = lName;
    }

    // birth date

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) throws ValidationException {
        if (birthDate == null) {
            throw new ValidationException("Birth date cannot be empty.");
        }
        this.birthDate = birthDate;
    }
}
