package StepDefinitions.web;

import java.time.Duration;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.junit.Assert;
import java.time.Duration;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LabCorpJobSteps {
	
	WebDriver driver;
    WebDriverWait wait;
    String jobTitle, jobLocation, jobId;

    @Given("I open the LabCorp homepage")
    public void openLabCorpHomepage() {
        driver = new ChromeDriver(); 
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://www.labcorp.com");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Accept All Cookies']"))).click();
        driver.manage().deleteAllCookies();
    }

    @When("I navigate to the Careers page")
    public void navigateToCareersPage() {
        WebElement careersLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Careers")));
        careersLink.click();
    }

    @When("I search for {string}")
    public void searchForJob(String jobName) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("typehead")));
        searchInput.sendKeys(jobName);
        searchInput.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Accept All Cookies']"))).click();
    }

    @When("I select the job listing titled {string}")
    public void selectJobListing(String title) {
        String dynamicXPath = String.format("//a[@data-ph-at-job-title-text='%s']", title);
        WebElement jobLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXPath)));
        jobLink.click();
    }

    @Then("I verify job details:")
    public void verifyJobDetails(Map<String, String> data) {
        jobTitle = driver.findElement(By.xpath("//h1[@class='job-title']")).getText().replaceAll("\\s+", " ").trim();
        jobLocation = driver.findElement(By.xpath("//span[@class='au-target job-location']")).getText().replaceAll("\\s+", " ").trim();
        jobId = driver.findElement(By.xpath("//span[@data-ph-id='ph-job-fields-1632216542376-ph-job-details-v1glzi12-ui7wgF']")).getText().replaceAll("\\s+", " ").trim();
        Assert.assertEquals(data.get("Job Title").replaceAll("\\s+", " ").trim(), jobTitle);
        Assert.assertEquals(data.get("Job Location").replaceAll("\\s+", " ").trim(), jobLocation);
        Assert.assertEquals(data.get("Job ID").replaceAll("\\s+", " ").trim(), jobId);

        // 3 Custom Assertions
        WebElement descPara = driver.findElements(By.xpath("//b[text()='About Us']")).getFirst();
        Assert.assertTrue(descPara.getText().contains(data.get("Description")));

		/*
		 * WebElement mgmtBullet = driver.findElement(By.
		 * xpath("//h2[contains(text(),'Management Support')]/following-sibling::ul/li[2]"
		 * )); Assert.assertEquals(data.get("Management Bullet"), mgmtBullet.getText());
		 * 
		 * WebElement reqItem = driver.findElement(By.xpath(
		 * "//h2[contains(text(),'Requirements')]/following-sibling::ul/li[3]"));
		 * Assert.assertEquals(data.get("Requirement"), reqItem.getText());
		 * 
		 * WebElement tool =
		 * driver.findElement(By.xpath("//*[contains(text(),'JDK11')]"));
		 * Assert.assertTrue(tool.getText().contains(data.get("Automation Tool")));
		 */
    }

    @When("I click Apply Now")
    public void clickApplyNow() {
        WebElement applyBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href.bind='applyUrl(jobDetail.applyUrl)']")));
        applyBtn.click();
    }


    @And("I return to the Job Search page")
    public void returnToSearchPage() {
        WebElement backBtn = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Return to Job Search")));
        backBtn.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("search"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
