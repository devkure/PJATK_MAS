import java.util.ArrayList;

public interface Artist2D {
    void getLatestWorks();
    void setPortfolioURL(String portfolioURL) throws ValidationException;
    void setTablet(String tablet) throws ValidationException;
}
