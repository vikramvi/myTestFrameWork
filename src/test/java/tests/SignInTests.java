package tests;

import baseTest.BaseTest;
import common.Application;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTagValuesOf;
import org.junit.Test;
import org.junit.runner.RunWith;

@Story(Application.SignInTests.class)
@RunWith(SerenityRunner.class)
@WithTagValuesOf({ "SignInTests" })
public class SignInTests extends BaseTest {

    @Test
    @WithTagValuesOf({ "priority:critical" })
    @Title("First Time Sign In - 0000000")
    public void smokeTestSignInFullCycle() {
        onBoardingSteps.tapOnSignInButton();
        signInSteps.signInProcedure(freeUserEmail, freeUserPassword);
        homeSteps.checkVisibilityOfFeaturedSeriesTitleButton();
    }
}
