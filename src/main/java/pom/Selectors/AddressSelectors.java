package pom.Selectors;

import org.openqa.selenium.By;

public class AddressSelectors {

    public static final By ADDRESS_MENU = By.id("glow-ingress-line2");
    public static final By ADDRESS_BOOK = By.id("GLUXManageAddressLink");
    public static final By ADD_ADDRESS = By.id("ya-myab-address-add-link");
    public static final By FULL_NAME = By.id("address-ui-widgets-enterAddressFullName");
    public static final By ADDRESS_PHONE_NUMBER = By.id("address-ui-widgets-enterAddressPhoneNumber");
    public static final By ADDRESS_STREET = By.id("address-ui-widgets-enterAddressLine1");
    public static final By ADDRESS_BUILDING_NUMBER = By.id("address-ui-widgets-enter-building-name-or-number");
    public static final By ADDRESS_CITY = By.id("address-ui-widgets-enterAddressCity");
    public static final By ADDRESS_CITY_DROPDOWN_FIRST_RESULT = By.id("address-ui-widgets-autoCompleteResult-0");
    public static final By ADDRESS_DISTRICT = By.id("address-ui-widgets-enterAddressDistrictOrCounty");
    public static final By ADDRESS_DISTRICT_DROPDOWN_FIRST_RESULT = By.id("address-ui-widgets-autoCompleteResult-0");
    public static final By ADD_ADDRESS_BUTTON = By.xpath("//input[@type='submit' and following-sibling::*[@id='address-ui-widgets-form-submit-button-announce']]");

    public static final By CREATED_ADDRESS_BLOCK = By.id("ya-myab-display-address-block-1");
    public static final By CREATED_ADDRESS_NAME = By.xpath("//*[@id='address-ui-widgets-FullName' and ancestor::*[@id='ya-myab-display-address-block-1']]");
    public static final By CREATED_ADDRESS_PHONE_NUMBER = By.xpath("//*[@id='address-ui-widgets-PhoneNumber' and ancestor::*[@id='ya-myab-display-address-block-1']]");
    public static final By CREATED_ADDRESS_STREET = By.xpath("//*[@id='address-ui-widgets-AddressLineOne' and ancestor::*[@id='ya-myab-display-address-block-1']]");
    public static final By CREATED_ADDRESS_BUILDING = By.xpath("//*[@id='address-ui-widgets-AddressLineTwo' and ancestor::*[@id='ya-myab-display-address-block-1']]");
    public static final By REMOVE_CREATED_ADDRESS = By.id("ya-myab-address-delete-btn-1");
    public static final By CONFIRM_REMOVE_CREATED_ADDRESS = By.xpath("//input[following-sibling::*[@id='deleteAddressModal-1-submit-btn-announce']]");







}
