package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import pages.LoginPageUS2;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class SeleniumUtils {

    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }

    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    public static void selectFromDropdownByVisibleText(WebElement selectElement, String optionText) {
        new Select(selectElement).selectByVisibleText(optionText);
    }

    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
    return elemTexts;
    }
    // Explicit Wait methods

    public static void waitForVisibility(WebElement element, int seconds){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }

    public static void waitForVisibility(By locator, int seconds){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
    }

    public static void waitForVisibilityOfMultipleElementsAsList(List<WebElement> list, int seconds){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(list)));
    }

    public static void waitForClickability(WebElement element, int seconds){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }

    public static void waitForClickability(By locator, int seconds){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
    }

    public static void waitForPresenceOfElementLocated(By locator, int seconds){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(locator)));
    }

    public static void waitForTitleContains(String partOfTitle, int seconds){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.titleContains(partOfTitle)));
    }

    public static void waitForTitleIs(String title, int seconds){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.titleIs(title)));
    }

    public static void waitForUrlContains(String partOfUrl, int seconds){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains(partOfUrl)));
    }

    public static void waitFor(int seconds){
        try{
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public static void waitForPageToLoad (int seconds){
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try{
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
            wait.until(expectation);
        }catch (Throwable error){
            System.out.println("Timed out waiting for page load");
        }
    }

    public static WebElement fluentWait(WebElement webElement, int timeOutSeconds, int pollingSeconds){

        Wait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(timeOutSeconds))
                .pollingEvery((Duration.ofSeconds(pollingSeconds)))
                .ignoring(NoSuchElementException.class);
        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });
    }

    public static boolean elementExists(WebElement element, int seconds){
        try{
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
            return true;
        } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException e){
            return false;
        }
    }

    public static void takeScreenshot(String pathToFile){
        byte[] screenshotAsFiles = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        try {
            Path write = Files.write(Path.of(pathToFile), screenshotAsFiles);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static String getScreenshot(String name){
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String fileName = name + date + ".png";
        String target = System.getProperty("user.dir") + "/target/extentReport/" + fileName;
        File finalDestination = new File(target);
        try{
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return fileName;
    }

    public static void jsClick(WebElement webElement){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", webElement);
    }

    public  static void uploadFile(By chooseFileButton, String pathToAFileToBeUploaded){
        Driver.getDriver().findElement(chooseFileButton).sendKeys(pathToAFileToBeUploaded);
    }

    public static void jsSendKeys(String cssExpression, String value){
        ((JavascriptExecutor) Driver.getDriver()).executeScript("document.querySelector(\""
                + cssExpression + "').value='" + value + "';");
    }

    public static void scrollToElement(WebElement element){
        int y = element.getLocation().getY();
        ((JavascriptExecutor) Driver.getDriver()).executeScript( "window.scrollBy(0,"+ y +")");
    }

    public static void scroll(int x, int y){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollBy("+x+", "+y+");");
    }


    public static boolean containsOnlyLetterAndSpace(String str) {
        boolean format = false;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            } else if (Character.isLetter(c)) {
                format = true;
            } else if (!Character.isSpaceChar(c)) {
                return false;
            }
        }
        return format;
    }

    public static boolean containsUpperCase(String str){
        for (char c : str.toCharArray()) {if (Character.isUpperCase(c)) {return true;}}
        return false;
    }

    public static boolean containsLowerCase(String str){
        for (char c : str.toCharArray()) {if (Character.isLowerCase(c)) {return true;}}
        return false;
    }

    public static boolean containsDigit(String str){
        for (char c : str.toCharArray()) {if (Character.isDigit(c)) {return true;}}
        return false;
    }

    public static String copyAndGetClipboardText(WebDriver driver, WebElement element){
        String copiedText;
        try {
            Actions actions = new Actions(driver);
            actions.click(element)
                    .keyDown(Keys.COMMAND)
                    .sendKeys("a")
                    .sendKeys("c")
                    .keyUp(Keys.COMMAND)
                    .perform();
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable transferable = clipboard.getContents(null);
            copiedText = (String) transferable.getTransferData(DataFlavor.stringFlavor);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return copiedText;
    }

    public static List<String> findDuplicateString(List<Map<String, Object>> listOfMaps, String key) {

        Set<String> uniqueStrings = new HashSet<>();
        List<String> duplicateStrings = new ArrayList<>();

        for (Map<String, Object> map : listOfMaps) {
            Object value = map.get(key);
            if (value instanceof String) {
                String targetWord = (String) value;
                if (uniqueStrings.contains(targetWord)) {
                    duplicateStrings.add(targetWord);
                } else {
                    uniqueStrings.add(targetWord);
                }
            }
        }
        return duplicateStrings;
    }
}
