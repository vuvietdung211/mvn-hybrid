package pageObject.sauceLabs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;

public class InventoryPageObject extends BasePage{
	WebDriver driver;
	public InventoryPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDataStringSortedAscending(WebDriver driver, String locator) {
		ArrayList<String> productNameText = new ArrayList<>();

		// Tìm tất cả element matching vs điều kiện (Name/ Price/.)
		List<WebElement> productNameList = driver.findElements(By.xpath(locator));
		// Lấy text của từng element add vào Array List
		for (WebElement element : productNameList) {
			productNameText.add(element.getText());
		}
		for (String name : productNameText) {
			System.out.println(name);
		}
			ArrayList<String> sortedList = new ArrayList<>();
			for (String child : productNameText) {
				sortedList.add(child);
			}
		
		 // Thực hiện SORT ASC
		Collections.sort(sortedList);
				
		// Verify 2 array bằng nhau - nếu dữ liệu sort trên UI ko chính xác thì kết quả trả về sai
		return sortedList.equals(productNameText);

	
	}
	
	public void isDataStringSortedDescending() {
		// TODO Auto-generated method stub
		
	}

}
