package pages;

import io.appium.java_client.pagefactory.iOSFindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class OnBoardingPage extends AbstractPage {

    @iOSFindBy(accessibility = "onboardingViewSignInButton")
    public WebElementFacade signInButtonOnboardingView;

    @iOSFindBy(accessibility = "onboardingViewCreateAccountButton")
    public WebElementFacade createAccountButtonOnboardingView;

    public void tapOnSignInButton() {
        utils.tapElement(signInButtonOnboardingView);
    }

    public void tapOnCreateAccountButton() {
        utils.tapElement(createAccountButtonOnboardingView);
    }
}