import java.util.ArrayList;

public class Artist3D extends Artist{
    private String preferedStyle;
    private String showreelURL;

    public Artist3D(PersonalData personalData, String nName, String email, String role, String specialization, ArrayList<String> software, String preferedStyle, String showreelURL, SeniorityLevel seniorityLevel, AccessLevel accessLevel) throws ValidationException {
        super(personalData, nName, email, role, specialization, software, seniorityLevel, accessLevel);
        setPreferedStyle(preferedStyle);
        setShowreelURL(showreelURL);
    }

    public String getPreferedStyle() {
        return preferedStyle;
    }

    public void setPreferedStyle(String preferedStyle) throws ValidationException {
        if(preferedStyle == null || preferedStyle.trim().isEmpty()){
            throw new ValidationException("Style cannot be null or empty");
        }
        this.preferedStyle = preferedStyle;
    }

    public String getShowreelURL() {
        return showreelURL;
    }

    public void setShowreelURL(String showreelURL) throws ValidationException {
        if (showreelURL == null || showreelURL.trim().isEmpty()){
            throw new ValidationException("ShowreelURL cannot be empty");
        }
        this.showreelURL = showreelURL;
    }

    public void getLatestWorks() {
        System.out.println("Here are the latest 3d artworks of " + this.getnName());
    }
}
