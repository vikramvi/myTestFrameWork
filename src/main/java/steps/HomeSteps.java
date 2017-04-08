package steps;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.HomePage;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HomeSteps extends ScenarioSteps {

    private static final long serialVersionUID = 1L;

    HomePage homePage;

    @Step("Getting the featured series title text")
    public HomeSteps gettingTheFeaturedSeriesTitleText() {
        Serenity.setSessionVariable("featuredSeriesTitle")
                .to(homePage.featuredSeriesTitle.getText());
        return this;
    }

    @Step("Check if Featured Series Title is displayed")
    public HomeSteps checkVisibilityOfFeaturedSeriesTitleButton() {
        assertThat("Featured Series Title is not visible",
                homePage.featuredSeriesTitle.isVisible(),
                is(true));
        return this;
    }
}