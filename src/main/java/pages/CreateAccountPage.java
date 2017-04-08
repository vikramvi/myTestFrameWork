package pages;

import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import java.util.List;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CreateAccountPage extends AbstractPage {

    @iOSFindBy(accessibility = "Return")
    public WebElementFacade returnKeyboardButton;

    @iOSFindBy(accessibility = "createAccountModalViewSkipButton")
    public WebElementFacade skipButton;

    @iOSFindBy(accessibility = "createAccountModalViewEmailTextField")
    public WebElementFacade emailField;

    @iOSFindBy(accessibility = "createAccountModalViewPasswordTextField")
    public WebElementFacade passwordField;

    @iOSFindBy(accessibility = "createAccountModalViewCreateAccountButton")
    public WebElementFacade createAccountButton;
    ///////////////////////////////////////////////////////////////////////////////
    @iOSFindBy(accessibility = "avatarImageCollectionViewCell")
    public List<WebElement> avatarsList;

    @iOSFindBy(accessibility = "chooseUsernameViewRandomUsernameTextField")
    public WebElementFacade chosenUserNameTextField;

    @iOSFindBy(accessibility = "profileViewContinueButton")
    public WebElementFacade continueButton;

    public void enterEmail(String email) {
        emailField.clear();
        utils.w(emailField).setValue(email);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        utils.w(passwordField).setValue(password);
    }

    public void tapOnCreateAccountButton() {
        utils.tapElement(createAccountButton);
    }

    public void swipeLeftThroughTheAvatars() {
        utils.swipeToSpecificElement(utils.findListOfElements(avatarsList), 2, "left");
    }

    public void swipeLeftThroughTheAvatarsList() {
        List<IOSElement> avatars = utils.findListOfElements(avatarsList);
        utils.swipeToSpecificElement(avatars, avatars.size(), "left");
        assertThat("Avatar at index 10 is not visible",
                avatars.get(avatars.size() - 1).isDisplayed(), is(true));
    }

    public void swipeRightThroughTheAvatarsList() {
        List<IOSElement> avatars =
                utils.iOSDriver().findElements(By.name("avatarImageCollectionViewCell"));
        utils.swipeToSpecificElement(avatars, avatars.size() - 1, "right");
        assertThat("Avatar at index 0 is not visible", avatars.get(0).isDisplayed(), is(true));
    }

    public void usernameCharCheck(String strUsername) {
        chosenUserNameTextField.clear();
        chosenUserNameTextField.clear();
        utils.w(chosenUserNameTextField).setValue(strUsername);
    }

    public void tapOnContinueButton() {
        if (returnKeyboardButton.isCurrentlyVisible()) {
            utils.tapElement(returnKeyboardButton);
        }
        utils.tapElement(continueButton);
    }

    public void tapSkipButton() {
        utils.tapElement(skipButton);
    }

    public void emailKeyboardAppearanceCheck() {
        utils.tapElement(emailField);
    }

    public void passwordKeyboardAppearanceCheck() {
        utils.tapElement(passwordField);
    }
}
