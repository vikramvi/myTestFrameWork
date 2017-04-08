package pages;

import common.Utils;
import net.serenitybdd.core.pages.PageObject;

public class AbstractPage extends PageObject {

    public Utils utils;

    public AbstractPage() {
        utils = new Utils();
    }
}