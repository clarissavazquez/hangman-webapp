package edu.csumb.cst438fa16.hangman.webclient;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * User story:
 *
 *   As a user I want to guess a letter and see it 
 *   replace the blank spaces if guessed correctly 
 *
 * Class name ends in IT so it runs with "mvn integration-test", not "mvn test".
 */
public class HangmanIT {
    // Requires chromedriver. See:
    // http://docs.seleniumhq.org/docs/03_webdriver.jsp#chromedriver
    // On Mac you can install it with "brew install googledriver" if you have Homebrew.
    WebDriver driver = new ChromeDriver();

    @Before
    public void setUp() {
        driver.get("http://localhost:8080/hangman.html");
    }

    @After
    public void tearDown() {
        driver.quit(); // close browser
    }

    /**
     * Acceptance test 1:
     *
     *   Given I am on the hangman page
     *   and the word cat, I pass the letter
     *   c and it should replace the first 
     *   blank space
     */
    @Test
    public void testAppendOneCorrectLetter() {
        driver.findElement(By.id("newGuesses")).sendKeys("c");
        driver.findElement(By.id("submit")).click();

        // The greeting is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBe(By.id("pattern"), "c.."));

        // Success
    }

    /**
     * Acceptance test 2:
     * 
     * Given I am on the hangman page and
     * the word is cat, I pass the pattern
     * abc and it should replace the blank
     * spaces with c and a
     */
    @Test
    public void testAppendCorrectPattern() {
        driver.findElement(By.id("newGuesses")).sendKeys("abc");
        driver.findElement(By.id("submit")).click();
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBe(By.id("pattern"), "ca.")); 
    }

}
