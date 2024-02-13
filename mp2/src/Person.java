
public class Person extends ObjectPlus {
    private PersonalData personalData;

    public Person(PersonalData personalData) {
        super();
        this.personalData = personalData;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }
}
