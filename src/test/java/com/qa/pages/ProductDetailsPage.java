package com.qa.pages;

import com.qa.utils.GlobalParams;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class ProductDetailsPage extends MenuPage {
	private static Logger logger = LogManager.getLogger(ProductDetailsPage.class);
	
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]\n" + 
			"") 
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name=\"test-Description\"]/child::XCUIElementTypeStaticText[1]")
	private WebElement title;
	
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]"
			+ "") 
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name=\"test-Description\"]/child::XCUIElementTypeStaticText[2]")
	private WebElement desc;

	@AndroidFindBy (accessibility = "test-BACK TO PRODUCTS") 
	@iOSXCUITFindBy (id = "test-BACK TO PRODUCTS")
	private WebElement backToProductsBtn;

	@iOSXCUITFindBy (id = "test-Price")
	private WebElement iOSProductPrice;

	@iOSXCUITFindBy (id = "test-ADD TO CART") private WebElement addToCartBtn;

public String getTitle() {
	return getText(title, "title is: ");
}

public String getDesc() {
	return getText(desc, "description is: ");
}

public String getPrice() throws Exception {
	switch(new GlobalParams().getPlatformName().toLowerCase()){
		case "android":
			return getText(andScrollToElementUsingUiScrollable("description", "test-Price"), "price is: ");
		case "ios":
			return getText(iOSScrollToElementUsingMobileScroll(iOSProductPrice), "price is: ");
		default:
			throw new Exception("Invalid platform name");
	}
}

public Boolean isAddToCartBtnDisplayed() {
	return addToCartBtn.isDisplayed();
}

public ProductsPage pressBackToProductsBtn() {
	click(backToProductsBtn, "navigate back to products page");
	return new ProductsPage();
}

}
