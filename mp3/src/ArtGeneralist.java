import java.util.ArrayList;

public class ArtGeneralist extends Artist3D implements Artist2D{
    private ArrayList<String> additionalSkills;
    private String tablet;
    private String portfolioURL;

    public ArtGeneralist(PersonalData personalData, String nName, String email, String role, String specialization, ArrayList<String> software, String preferedStyle, String showreelURL, String tablet, String portfolioURL, SeniorityLevel seniorityLevel, AccessLevel accessLevel) throws ValidationException {
        super(personalData, nName, email, role, specialization, software, preferedStyle, showreelURL, seniorityLevel, accessLevel);
        setPortfolioURL(portfolioURL);
        setTablet(tablet);
    }

    @Override
    public void getLatestWorks() {
        System.out.println("Here are the latest 2d artworks of " + this.getnName());
        System.out.println("Here are the latest 3d artworks of " + this.getnName());
    }

    @Override
    public void setPortfolioURL(String portfolioURL) throws ValidationException {
        if(portfolioURL == null || portfolioURL.trim().isEmpty()){
            throw new ValidationException("PortfolioURL cannot be empty or null");
        }
        this.portfolioURL = portfolioURL;
    }

    @Override
    public void setTablet(String tablet) throws ValidationException {
        if (tablet == null || tablet.trim().isEmpty()){
            throw new ValidationException("Tablet info cannot be empty or null");
        }
        this.tablet = tablet;
    }
}
