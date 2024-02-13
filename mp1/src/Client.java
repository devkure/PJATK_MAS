import java.util.ArrayList;

public class Client extends Person{
    private ArrayList<String> companyInfo;
    private String email;
    private String phoneNumber;

    public Client(PersonalData personalData, ArrayList<String> companyInfo, String email, String phoneNumber) {
        super(personalData);
        this.companyInfo = companyInfo;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
