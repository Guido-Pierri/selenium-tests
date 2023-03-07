package com.example.produktapi.seliumTests;

import com.example.produktapi.model.Product;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
public class SeleniumTests {
	WebDriver driver = new ChromeDriver();

	@BeforeEach
	void init(){
		driver.get("https://onlineoasisfrontend.netlify.app/");
	}

	@AfterEach
	void quit(){
		driver.quit();

	}
	//G-nivå tester
	@Test
	@DisplayName("Kontrollerar att webbplatsens titel stämmer")
	public void checkTitle(){

		Assertions.assertEquals("Online Oasis",driver.getTitle(),"Titeln stämmer inte med förväntat");
		Assertions.assertNotEquals("Website",driver.getTitle(),"Titeln stämmer inte överens med förväntat");
	}
	@Test
	@DisplayName("Kolla att det totala antalet produkter stämmer")
	void numberOfProductsShouldBeTwenty(){
		List<WebElement> products = driver.findElements(By.className("productItem"));
		Assertions.assertEquals(20, products.size(), "Antalet produkter stämmer inte");
		Assertions.assertNotEquals(21,products.size(), "Antalet produkter stämmer inte");
	}

	//VG nivå tester
	@Test
	@DisplayName("Kolla att alla kategorier har rätt namn")
	void checkIfCategoriesAreCorrect(){

		String[] categories = {
				"men's clothes",
				"women's clothes",
				"jewelery",
				"electronics"}
				;
		String[] elementId = {
				"men",
				"women",
				"jewelery",
				"electronics"
		};
		for (int i = 0; i < categories.length; i++) {
			WebElement categoryButton = driver.findElement(By.xpath("//*[@id=\"" + elementId[i] + "\"]"));
			String text = categoryButton.getText();
			Assertions.assertEquals(categories[i],text.toLowerCase(), "Texten verkar inte stämma överens med kategorin");
			Assertions.assertNotEquals("categories",text.toLowerCase(), "Texten verkar inte stämma överens med kategorin");
		}
	}

	@Test
	@DisplayName("Kontrollera att priset blir rätt på 6 till produkter")
	void checkThatPriceOfSixProductsIsCorrect(){
		String[] prices = {
				"109.95",
				"22.3",
				"55.99",
				"15.99",
				"695.00",
				"168.00"
		};
		for (int i = 0; i < prices.length; i++) {
			WebElement productPrice = driver.findElement(By.xpath("//*[@id=\"products\"]/div[" +(i+1)+"]/div/div/div/div/p/b"));
			String productPriceText = productPrice.getText();
			if (prices[i].contains(".00")) {
				Assertions.assertEquals("$" + prices[i].replace(".00", ""), productPriceText, "Priset verkar inte stämma överens");
			}
			else {
				Assertions.assertEquals("$" + prices[i], productPriceText, "Priset verkar inte stämma överens");
				Assertions.assertNotEquals("$" + prices[i] + 1, productPriceText, "Priset verkar inte stämma överens");
			}
		}
	}
	@Test
	@DisplayName("Kontrollera att bilderna skrivs ut/visas för 3 produkter")
	void imageOfThreeProductsIsVisible() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://onlineoasisfrontend.netlify.app/");
		for (int i = 0; i < 2; i++) {
			WebElement productImage = new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"products\"]/div[" + (i + 1) + "]/div/img[1]")));
			Assertions.assertTrue(productImage.isDisplayed(), "Bilden verkar inte läsas in");
		}
	}
	@Test
	@DisplayName("Kontrollera att bilden har rätt src-attribut på produkter 3")
	void checkIfAllProductsHaveRightSrcAttribute(){
		String[] srcArray = {
				"https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
				"https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg",
				"https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg"
		};
		for (int i = 0; i < srcArray.length; i++) {
			WebElement productImage = driver.findElement(By.xpath("//*[@id=\"products\"]/div[" + (i + 1) + "]/div/img[1]"));
			String sqlSrc = srcArray[i];
			String frontEndSrc = productImage.getAttribute("src");
			Assertions.assertEquals(sqlSrc,frontEndSrc,"src attributen verkar inte stämma överens");
			Assertions.assertNotEquals("Wrong src attribute",frontEndSrc,"src attributen verkar inte stämma överens");
		}
	}
	@Test
	@DisplayName("Kontrollerar att alla 20 produkter har rätt namn")
	void checkAllNames(){
		String[] names = {"Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
				"Mens Casual Premium Slim Fit T-Shirts",
				"Mens Cotton Jacket",
				"Mens Casual Slim Fit",
				"John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet",
				"SolGold Petite Micropave",
				"White Gold Plated Princess",
				"Pierced Owl Rose Gold Plated Stainless Steel Double",
				"WD 2TB Elements Portable External Hard Drive - USB 3.0",
				"SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s",
				"Silicon Power 256GB SSD 3D NAND A55 SLC Cache Performance Boost SATA III 2.5",
				"WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive",
				"Acer SB220Q bi 21.5 inches Full HD (1920 x 1080) IPS Ultra-Thin",
				"Samsung 49-Inch CHG90 144Hz Curved Gaming Monitor (LC49HG90DMNXZA) – Super Ultraw Screen QLED",
				"BIYLACLESEN Women's 3-in-1 Snowboard Jacket Winter Coats",
				"Lock and Love Women's Removable Hooded Faux Leather Moto Biker Jacket",
				"Rain Jacket Women Windbreaker Striped Climbing Raincoats",
				"MBJ Women's SolShort Sleeve Boat Neck V",
				"Opna Women's Short Sleeve Moisture",
				"DANVOUY Womens T Shirt Casual Cotton Short"};
		for (int i = 0; i < names.length; i++) {
			WebElement productNameElement = driver.findElement(By.id("title" +(i+1)));
			String productNameText = productNameElement.getText();
			String productNameTextExpected = names[i];
			String productNameNotExpected = "Random text";
			Assertions.assertEquals(productNameTextExpected,productNameText, "Namnet verkar inte stämma överens");
			Assertions.assertNotEquals(productNameNotExpected,productNameText, "Namnet verkar inte stämma överens");
		}
	}
	@Test
	@DisplayName("Gör produktkategorierna klickbara och testa att rätt antal produkter visas vid klick på respektive kategori")
	void checkIfCategoryButtonsTargetsHaveRightNumberOfProducts(){
		String[] categoryIds = {"men",
				"women",
				"jewelery",
				"electronics"};
		Integer[] numberOfProducts = {4,
				6,
				4,
				6
		};
		for (int i = 0; i < categoryIds.length; i++) {
			WebElement button = driver.findElement(By.id(categoryIds[i]));
			button.click();
			List<WebElement> products = new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.className("product"))));
			Assertions.assertEquals(numberOfProducts[i], products.size(), "Antalet produkter stämmer inte");
			Assertions.assertNotEquals(numberOfProducts[i] + 1, products.size(), "Antalet produkter stämmer inte");
			driver.get("https://onlineoasisfrontend.netlify.app/");
		}
	}
}
