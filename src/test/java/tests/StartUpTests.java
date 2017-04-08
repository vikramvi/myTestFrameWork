package tests;

import baseTest.BaseTest;
import common.Application;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTagValuesOf;
import org.junit.Test;
import org.junit.runner.RunWith;

@Story(Application.StartUpTests.class)
@RunWith(SerenityRunner.class)
@WithTagValuesOf({ "StartUpTests" })
public class StartUpTests extends BaseTest {

    @Test
    @WithTagValuesOf({ "priority:critical" })
    @Title("Initial loading (First Time Launch) - 000000")
    public void smokeTestLoadWithInternet() {
        onBoardingSteps.tapOnCreateAccountButton();
        createAccountSteps.emailKeyboardAppearanceCheck();
        createAccountSteps.passwordKeyboardAppearanceCheck();
    }
}