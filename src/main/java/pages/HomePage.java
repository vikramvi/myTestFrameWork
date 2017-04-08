package pages;

import io.appium.java_client.pagefactory.iOSFindBy;
import java.util.List;
import net.serenitybdd.core.pages.WebElementFacade;

public class HomePage extends AbstractPage {

    @iOSFindBy(accessibility = "commonControlsHamburgerButton")
    public WebElementFacade menuButton;

    @iOSFindBy(accessibility = "commonControlsSearchButton")
    public WebElementFacade searchButton;

    @iOSFindBy(accessibility = "feedViewNavigationBarChannelIcon")
    public WebElementFacade vrvLogo;

    @iOSFindBy(accessibility = "feedViewFeaturedSeriesImage")
    public WebElementFacade featuredSeriesCell;

    @iOSFindBy(accessibility = "feedViewHeroCellTitleLabel")
    public WebElementFacade featuredSeriesTitle;

    @iOSFindBy(accessibility = "feedViewHeroCellDescriptionLabel")
    public WebElementFacade featuredSeriesDescription;

    @iOSFindBy(accessibility = "seriesViewContinueWatchingView")
    public WebElementFacade startWatchingButton;

    @iOSFindBy(className = "XCUIElementTypeCollectionView")
    public List<WebElementFacade> carouselCollection;

    @iOSFindBy(className = "XCUIElementTypeCell")
    public List<WebElementFacade> overlayCollection;

    @iOSFindBy(xpath = "//XCUIElementTypeCell//XCUIElementTypeStaticText")
    public List<WebElementFacade> carouselTitles;

    @iOSFindBy(accessibility = "feedViewNavigationBarChannelTitleLabel")
    public WebElementFacade channelName;

    public void swipeLeft() {
        if (!startWatchingButton.isCurrentlyVisible()) {
            startWatchingButton.waitUntilVisible();
        } else {
            utils.swipe("right");
        }
    }

    public void scrollDown() {
        if (!featuredSeriesTitle.isCurrentlyVisible()) {
            featuredSeriesTitle.waitUntilVisible();
        } else {
            utils.scroll("down");
        }
    }

    public void scrollUp() {
        utils.scroll("up");
    }

    public void tapOnFeaturedSeries() {
        if (featuredSeriesTitle.isDisplayed()) {
            utils.tapElement(featuredSeriesTitle);
        } else {
            featuredSeriesTitle.waitUntilVisible();
        }
    }

    public void tapOnSearchButton() {
        utils.tapElement(searchButton);
    }

    public void tapOnHamburgerButton() {
        utils.tapElement(menuButton);
    }
}
