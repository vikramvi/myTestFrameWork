package baseTest;

import common.ReadData;
import common.Utils;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import steps.*;

public class BaseTest {

    @Managed(driver = "appium")
    public WebDriver driver;

    @Steps
    public CreateAccountSteps createAccountSteps;

    @Steps
    public HomeSteps homeSteps;

    @Steps
    public SignInSteps signInSteps;

    @Steps
    public OnBoardingSteps onBoardingSteps;

    public Utils utils = new Utils();

    public String userDefaultPassword = ReadData.getValue("defaultPassword");
    public String userSignInEmail = ReadData.getValue("signInEmail");
    public String userSignInPassword = ReadData.getValue("defaultPassword");
    public String freeUserEmail = ReadData.getValue("freeUserEmail");
    public String freeUserPassword = ReadData.getValue("freeUserPassword");
    public String specialCharEmail = ReadData.getValue("specialCharUserEmail");
    public String longUserPassword = ReadData.getValue("longUserPassword");
    public String longUserEmail = ReadData.getValue("longUserEmail");
    public String threeCategoriesSearchKeyword = ReadData.getValue("threeCategoriesSearchKeyword");

    public String getRandomEmail() {
        return utils.randomEmail();
    }

    @Before
    public void resetApp() {
        utils.resetApp();
    }
}