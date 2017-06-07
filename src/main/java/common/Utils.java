package common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class Utils extends PageObject {

    public static final String EMAIL_BASE = "iosTest";
    public static final String EMAIL_DOMAIN = "is.com";

    public String randomEmail() {
        long unixTime = System.currentTimeMillis() / 1000L;
        return EMAIL_BASE + "+" + unixTime + "@" + EMAIL_DOMAIN;
    }

    @SuppressWarnings("unchecked")
    public AppiumDriver<WebElement> appiumDriver() {
        return (AppiumDriver<WebElement>) ((WebDriverFacade) getDriver()).getProxiedDriver();
    }

    public void hideKeyboard() {
        if (isElementDisplayed(appiumDriver().findElementByClassName("UIAKeyboard"))) {
            iOSDriver().getKeyboard().sendKeys(Keys.RETURN);
        }
    }

    public void alertShouldBeDisplayed(String message) {
        boolean containMessage = false;
        WebElement alert = getDriver().findElement(By.className("XCUIElementTypeAlert"));
        Assert.assertTrue(alert.isDisplayed());
        List<WebElement> texts = alert.findElements(By.className("XCUIElementTypeStaticText"));
        for (WebElement text : texts) {
            if (text.getText().equalsIgnoreCase(message)) {
                containMessage = true;
            }
        }
        Assert.assertTrue("Alert message is not displayed", containMessage);
    }

    public boolean isElementVisible(WebElementFacade element) {
        try {
            return element.isVisible();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public boolean isElementPresent(WebElement el) {
        try {
            return ((WebElementFacade) el).isPresent();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public boolean isElementDisplayed(WebElement el) {
        try {
            return el.isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        } catch (ElementNotVisibleException ex) {
            return false;
        }
    }

    public List<IOSElement> findListOfElements(List<WebElement> listOfElements) {
        return iOSDriver().findElements(By.name(listOfElements.get(0).getAttribute("name")));
    }

    public String fluentWaitForText(WebElementFacade element) {
        fluentWaitForElement(element, 30, 100);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class, TimeoutException.class)
                .ignoring(StaleElementReferenceException.class,
                        ElementNotVisibleException.class);
        wait.until(
                driver -> driver.findElement(By.name(element.getAttribute("name"))).isDisplayed());
        return element.getText();
    }

    public WebElementFacade fluentWaitForElement(WebElementFacade el, int timeOutSec,
            int pollingMilSec) {
        By element = By.name(el.getAttribute("name"));
        FluentWait<WebDriver> fWait =
                new FluentWait<>(getDriver()).withTimeout(timeOutSec, TimeUnit.SECONDS)
                        .pollingEvery(pollingMilSec, TimeUnit.MILLISECONDS)
                        .ignoring(NoSuchElementException.class, TimeoutException.class)
                        .ignoring(StaleElementReferenceException.class,
                                ElementNotVisibleException.class);
        try {
            fWait.until(ExpectedConditions.visibilityOfElementLocated(element));
            fWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println("Element Not found trying again - " + element.toString());
            e.printStackTrace();
        }
        return find(element);
    }

    public void enterData(WebElementFacade el, String text) {
        el.waitUntilPresent();
        tapElement(el);
        el.sendKeys(text);
    }
    
    public boolean doubleTapElement(WebElement el) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            HashMap<String, String> tapObject = new HashMap<>();
            tapObject.put("element", w(el).getId());
            js.executeScript("mobile:doubleTap", tapObject);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean tapElement(WebElementFacade el) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            HashMap<String, String> tapObject = new HashMap<>();
            tapObject.put("x", String.valueOf(el.getSize().getWidth() / 2));
            tapObject.put("y", String.valueOf(el.getSize().getHeight() / 2));
            tapObject.put("element", w(el).getId());
            js.executeScript("mobile:tap", tapObject);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickElement(WebElementFacade el) {
        el.click();
    }

    public MobileElement w(WebElement el) {
        return (MobileElement) ((WebElementFacade) el).getWrappedElement();
    }

    public void setContextToNative() {
        Set<String> contextNames = appiumDriver().getContextHandles();
        appiumDriver().context((String) contextNames.toArray()[0]);
    }

    public void openApp() {
        appiumDriver().launchApp();
    }

    public void closeApp() {
        appiumDriver().closeApp();
    }

    public void quitApp() {
        appiumDriver().quit();
    }

    public void resetApp() {
        appiumDriver().resetApp();
    }

    public void removeApp() {
        appiumDriver().removeApp("com.domain.projectName");
    }

    public void setContextToWebview() {
        int loops = 0;
        while (true) {
            loops++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (appiumDriver().getContextHandles().size() == 2 || loops == 5) {
                break;
            }
        }
        Set<String> contextNames = appiumDriver().getContextHandles();
        System.out.println(appiumDriver().getContextHandles());
        appiumDriver().context((String) contextNames.toArray()[1]);
    }

    @SuppressWarnings("unchecked")
    public IOSDriver<IOSElement> iOSDriver() {
        return (IOSDriver<IOSElement>) ((WebDriverFacade) getDriver()).getProxiedDriver();
    }

    public void scroll(String direction) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("direction", direction);
        js.executeScript("mobile: scroll", scrollObject);
    }

    public void scrollToElement(WebElement el, String direction) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("element", ((RemoteWebElement) el).getId());
        scrollObject.put("direction", direction);
        js.executeScript("mobile: scroll", scrollObject);
    }

    public IOSElement scrollToChannel(String elementName) {
        WebElement elementToScroll =
                iOSDriver().findElementByAccessibilityId("channel" + elementName + "Cell");
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("element", ((RemoteWebElement) elementToScroll).getId());
        scrollObject.put("toVisible", "true");
        scrollObject.put("direction", "down");
        js.executeScript("mobile: scroll", scrollObject);
        js.executeScript("mobile: scroll", scrollObject);
        return iOSDriver().findElementByAccessibilityId("channel" + elementName + "Cell");
    }
    
    public void tapOnVisibleChannel(String elementName){
        WebElement el = scrollToChannel(elementName);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        HashMap<String, String> tapObject = new HashMap<>();
        tapObject.put("x", String.valueOf(el.getSize().getWidth() / 2));
        tapObject.put("y", String.valueOf(el.getSize().getHeight() / 2));
        tapObject.put("element", ((RemoteWebElement) el).getId());
        js.executeScript("mobile:tap", tapObject);
    }

    public void swipe(String direction) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        HashMap<String, String> swipeObject = new HashMap<>();
        swipeObject.put("direction", direction);
        js.executeScript("mobile: swipe", swipeObject);
    }

    public void swipeToElement(WebElement element, String direction) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        HashMap<String, String> swipeObject = new HashMap<>();
        swipeObject.put("element", ((RemoteWebElement) element).getId());
        swipeObject.put("direction", direction);
        js.executeScript("mobile: swipe", swipeObject);
    }

    public void swipeToSpecificElement(List<IOSElement> listOfElements, int iteration,
            String direction) {
        switch (direction) {
            case "right": {
                for (int i = iteration; i > -1; --i) {
                    swipeToElement(listOfElements.get(i), "right");
                }
                break;
            }

            case "left": {
                for (int i = 0; i < iteration; i++) {
                    swipeToElement(listOfElements.get(i), "left");
                }
                break;
            }
            case "up": {
                for (int i = 1; i < iteration; i++) {
                    swipeToElement(listOfElements.get(i), "up");
                }
            }
            case "down": {
                for (int i = iteration; i > -1; --i) {
                    swipeToElement(listOfElements.get(i), "up");
                }
            }
        }
    }

    public void swipeLeftOld() {
        Dimension dimensions = appiumDriver().manage().window().getSize();
        Double screenWidthStart = dimensions.getWidth() * 0.7;
        int swipeStart = screenWidthStart.intValue();
        Double screenHeightEnd = dimensions.getWidth() * 0.1;
        int swipeEnd = screenHeightEnd.intValue();
        iOSDriver().swipe(swipeStart, 400, swipeEnd, 400, 1000);
    }

    public void swipeDownOld() {
        Dimension size;
        size = appiumDriver().manage().window().getSize();
        int starty = (int) (size.height * 0.80);
        int endy = (int) (size.height * 0.60);
        int startx = size.width / 2;
        iOSDriver().swipe(startx, starty, startx, endy, 1000);
    }

    public void swipeRightOld() {
        Dimension dimensions = appiumDriver().manage().window().getSize();
        Double screenHeightStart = dimensions.getWidth() * 0.003;
        int scrollStart = screenHeightStart.intValue();
        Double screenHeightEnd = dimensions.getWidth() * 0.45;
        int scrollEnd = screenHeightEnd.intValue();
        iOSDriver().swipe(scrollStart, 15, scrollEnd, 15, 1000);
    }
}