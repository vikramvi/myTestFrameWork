package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.steps.ScenarioSteps;
import pages.SignInPage;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SignInSteps extends ScenarioSteps {

    private static final long serialVersionUID = 1L;

    SignInPage signInPage;

    @Step("Fill ***** password field")
    public SignInSteps enterSignInPassword(String strPassword) {
        signInPage.enterSignInPassword(strPassword);
        return this;
    }

    @Step("Fill {0} email field")
    public SignInSteps enterSignInEmail(String strEmail) {
        signInPage.enterSignInEmail(strEmail);
        return this;
    }

    @Step("Tap Sign In Button  in Sign In Page")
    public SignInSteps tapSignInButton() {
        signInPage.tapSignInButton();
        return this;
    }

    @Step("Check if keyboard appears while tapping on email field")
    public SignInSteps emailKeyboardAppearanceCheck() {
        signInPage.emailKeyboardAppearanceCheck();
        assertThat("Keyboard did not appear", signInPage.returnKeyboardButton.isDisplayed(),
                is(true));
        return this;
    }

    @StepGroup("Sign In steps")
    public SignInSteps signInProcedure(String email, String password) {
        enterSignInEmail(email);
        enterSignInPassword(password);
        tapSignInButton();
        return this;
    }
}
