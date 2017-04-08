package steps;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Assert;
import pages.CreateAccountPage;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CreateAccountSteps extends ScenarioSteps {

    private static final long serialVersionUID = 1L;

    CreateAccountPage createAccountPage;

    @StepGroup("Create account procedure")
    public CreateAccountSteps createAccountProcedure(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        tapOnCreateAccountButton();
        return this;
    }

    @Step("Fill {0} into Username")
    public CreateAccountSteps enterEmail(String email) {
        createAccountPage.enterEmail(email);
        return this;
    }

    @Step("Fill {0} into Password")
    public CreateAccountSteps enterPassword(String password) {
        createAccountPage.enterPassword(password);
        return this;
    }

    @Step("Tap Create Account Button")
    public CreateAccountSteps tapOnCreateAccountButton() {
        createAccountPage.tapOnCreateAccountButton();
        return this;
    }

    @Step("Getting the userName ")
    public CreateAccountSteps gettingNewUserUserName() {
        String userNameText = createAccountPage.utils.fluentWaitForText(
                createAccountPage.chosenUserNameTextField);
        Serenity.setSessionVariable("userNameText").to(userNameText);
        return this;
    }

    @Step("Check that UserName changes after the shaking")
    public CreateAccountSteps checkUserNameAfterShaking() {
        String expectedUserName = Serenity.sessionVariableCalled("userNameText").toString();
        assertThat("expectedUserName is not the same",
                createAccountPage.chosenUserNameTextField.getText().contentEquals(expectedUserName),
                is(false));
        return this;
    }

    @Step("Check if keyboard appears while tapping on email field")
    public CreateAccountSteps emailKeyboardAppearanceCheck() {
        createAccountPage.emailKeyboardAppearanceCheck();
        assertThat("Keyboard did not appear", createAccountPage.returnKeyboardButton.isDisplayed(),
                is(true));
        return this;
    }

    @Step("Check if keyboard appears while tapping on password field")
    public CreateAccountSteps passwordKeyboardAppearanceCheck() {
        createAccountPage.passwordKeyboardAppearanceCheck();
        assertThat("Keyboard did not appear", createAccountPage.returnKeyboardButton.isDisplayed(),
                is(true));
        return this;
    }
}
