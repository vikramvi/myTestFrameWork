package pages;

import io.appium.java_client.pagefactory.iOSFindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class SignInPage extends AbstractPage {

    @iOSFindBy(accessibility = "Return")
    public WebElementFacade returnKeyboardButton;
    @iOSFindBy(accessibility = "signInModalViewSignInButton")
    public WebElementFacade signInButton;
    @iOSFindBy(accessibility = "signInModalViewEmailTextField")
    protected WebElementFacade emailField, recoveryPasswordEmail;
    @iOSFindBy(accessibility = "signInModalViewPasswordTextField")
    protected WebElementFacade passwordField;

    public void enterSignInEmail(String email) {
        emailField.clear();
        utils.w(emailField).setValue(email);
    }

    public void enterSignInPassword(String password) {
        utils.w(passwordField).setValue(password);
    }

    public void enterEmailToRecoveryForgetPassword(String email) {
        emailField.clear();
        utils.w(recoveryPasswordEmail).setValue(email);
    }

    public void tapSignInButton() {
        utils.tapElement(signInButton);
    }

    public void emailKeyboardAppearanceCheck() {
        utils.tapElement(emailField);
    }

}
