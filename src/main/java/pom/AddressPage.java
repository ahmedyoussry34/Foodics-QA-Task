package pom;

import org.testng.Assert;
import pom.Selectors.AddressSelectors;
import utils.SeleniumActions;

import static utils.ConfigReader.getValueFromConfig;

public class AddressPage {

    public static void addAddress() {
        SeleniumActions seleniumActions = new SeleniumActions();
        String name = getValueFromConfig("full.name");
        String phoneNumber = getValueFromConfig("mobile.number");
        String streetName = getValueFromConfig("street.Name");
        String buildingName = getValueFromConfig("building.name");
        String cityName = getValueFromConfig("city.name");
        String districtName = getValueFromConfig("district.name");
        seleniumActions.clickOn(AddressSelectors.ADDRESS_MENU);
        seleniumActions.clickOn(AddressSelectors.ADDRESS_BOOK);
        seleniumActions.clickOn(AddressSelectors.ADD_ADDRESS);
        seleniumActions.setText(AddressSelectors.FULL_NAME, name, true);
        seleniumActions.setText(AddressSelectors.ADDRESS_PHONE_NUMBER, phoneNumber, true);
        seleniumActions.setText(AddressSelectors.ADDRESS_STREET, streetName, true);
        seleniumActions.setText(AddressSelectors.ADDRESS_BUILDING_NUMBER, buildingName, true);
        seleniumActions.setText(AddressSelectors.ADDRESS_CITY, cityName, true);
        seleniumActions.clickOn(AddressSelectors.ADDRESS_CITY_DROPDOWN_FIRST_RESULT);
        seleniumActions.setText(AddressSelectors.ADDRESS_DISTRICT, districtName, true);
        seleniumActions.clickOn(AddressSelectors.ADDRESS_DISTRICT_DROPDOWN_FIRST_RESULT);
        seleniumActions.clickOn(AddressSelectors.ADD_ADDRESS_BUTTON);
        verifyAddressIsAdded(name, phoneNumber, streetName, buildingName);
    }

    public static void removeAddress() {
        SeleniumActions seleniumActions = new SeleniumActions();
        seleniumActions.clickOn(AddressSelectors.ADDRESS_MENU);
        seleniumActions.clickOn(AddressSelectors.ADDRESS_BOOK);
        seleniumActions.clickOn(AddressSelectors.REMOVE_CREATED_ADDRESS);
        seleniumActions.clickOn(AddressSelectors.CONFIRM_REMOVE_CREATED_ADDRESS, true);
        verifyAddressRemoved();
    }

    public static void verifyAddressIsAdded(String name, String phoneNumber, String streetName, String buildingNumber) {
        SeleniumActions seleniumActions = new SeleniumActions();
        String nameFromWidget = seleniumActions.getValueFromAttribute(AddressSelectors.CREATED_ADDRESS_NAME, "textContent");
        String mobileFromWidget = seleniumActions.getValueFromAttribute(AddressSelectors.CREATED_ADDRESS_PHONE_NUMBER, "textContent");
        String streetFromWidget = seleniumActions.getValueFromAttribute(AddressSelectors.CREATED_ADDRESS_STREET, "textContent");
        String buildingFromWidget = seleniumActions.getValueFromAttribute(AddressSelectors.CREATED_ADDRESS_BUILDING, "textContent");
        Assert.assertEquals(nameFromWidget, name, "Full Name does not match");
        Assert.assertEquals(mobileFromWidget.replaceAll("[^\\d]", ""), "20" + phoneNumber, "Mobile Number does not match");
        Assert.assertEquals(streetFromWidget, streetName, "Street Name does not match");
        Assert.assertEquals(buildingFromWidget, buildingNumber, "Building Name does not match");
    }

    public static void verifyAddressRemoved() {
        SeleniumActions seleniumActions = new SeleniumActions();
        Assert.assertFalse(seleniumActions.isElementPresent(AddressSelectors.CREATED_ADDRESS_BLOCK), "Address is not removed");
    }

}
