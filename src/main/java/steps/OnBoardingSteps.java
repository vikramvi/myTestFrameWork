package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import pages.OnBoardingPage;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class OnBoardingSteps extends ScenarioSteps {

    private static final long serialVersionUID = 1L;

    @Steps
    OnBoardingPage onBoardingPage;

    @Step("Tap on Sign In button")
    public OnBoardingSteps tapOnSignInButton() {
        onBoardingPage.tapOnSignInButton();
        return this;
    }

    @Step("Tap on Create Account button")
    public OnBoardingSteps tapOnCreateAccountButton() {
        onBoardingPage.tapOnCreateAccountButton();
        return this;
    }
}