import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
@SuppressWarnings("rawtypes")
public class Appium 
{

	protected URL url;
	protected DesiredCapabilities capabilities;
	protected AndroidDriver driver;
	protected WebDriverWait wait;
	
	
	public Appium() throws MalformedURLException {
		
		this.url= new URL("http://0.0.0.0:4723/wd/hub");
		this.capabilities = new DesiredCapabilities();
	}
	
	
	public void setCapabilities(String androidVer) {
		
		this.capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		this.capabilities.setCapability("deviceName", "My New Phone");
		this.capabilities.setCapability("platformVersion", androidVer);
		this.capabilities.setCapability("udid", "192.168.1.110:5555");
		this.capabilities.setCapability("platformName", "Android");
		this.capabilities.setCapability("appPackage", "com.sitpros.alsalam");
		this.capabilities.setCapability("appActivity", "sitpros.com.alsalam.LoginActivity");
	}
	
	
	
	public void setDriver() {
		
		this.driver = new AndroidDriver(url, capabilities);

		this.wait= new WebDriverWait(driver, 60);
	}
	
	public void reset() {
		this.driver.resetApp();
		
	}
	public void close() {
		
		this.driver.quit();
	}
	
	public void wait(int time) throws InterruptedException  {
		
		time*=1000;
		Thread.sleep(time);
	}
	public void waitLoading() throws InterruptedException {
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(this.byId("p_title"))));
		//String text;
		if(this.driver.findElement(By.id(this.byId("p_title"))).getText() != null) {
			System.out.println(this.driver.findElement(By.id(this.byId("p_title"))).getText());	
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(this.byId("p_title"))));
		}
		
		//this.wait(1);
		

	}
	
	public void waitById(String name) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(this.byId(name))));
	}
	public void waitByPath(String name) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(name)));
	}
	
	public void hide_key() {
		
		this.driver.hideKeyboard();
	}
	@SuppressWarnings("deprecation")
	public void goBack() {
		driver.pressKeyCode(AndroidKeyCode.BACK);
	}
	public void goHome() 
	{
		driver.startActivity(new Activity("com.sitpros.alsalam", "MainActivity"));
	}
	
	public String byId(String id) 
	{
		return id="com.sitpros.alsalam:id/"+id;
	}
	
	public void resolveOTP() throws InterruptedException 
	{
		this.waitById("pin_first_edittext");
		this.driver.findElement(By.id(this.byId("pin_first_edittext"))).sendKeys("111111");
		
	}
	
	public void permission() throws InterruptedException {
		
		this.wait(1);
		this.driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
		this.wait(1);
	}
	
	public void Login(String user,String pass) throws InterruptedException {
		
		this.waitById("et_name");
		this.driver.findElement(By.id(this.byId("et_name"))).sendKeys(user);
		this.hide_key();
		this.driver.findElement(By.id(this.byId("et_password"))).sendKeys(pass);
		this.hide_key();
		this.driver.findElement(By.id(this.byId("signInBtn"))).click();
		
		
		
		this.resolveOTP();
	}
	
	public void transfer() throws InterruptedException {
		
		
		
		//this.waitLoading();
		this.waitById("toolbar_title");
		this.driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")).click();
		
		
		this.driver.findElement(By.id(this.byId("nav_payments"))).click();
		
		this.waitLoading();
		//this.waitLoading();
		
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(new PointOption().withCoordinates(272, 161)).perform();
		
		//this.driver.findElement(By.id(this.byId("ll_payment_typeSp"))).click();
	}
	
	public void own_account() throws InterruptedException {
		
		
		this.waitByPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]");
		this.driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]")).click();
		
		
		this.waitById("sp_from_account");
		this.driver.findElement(By.id(this.byId("sp_from_account"))).click();
		this.driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]")).click();
		//this.waitLoading();
		this.driver.findElement(By.id(this.byId("sp_to_account"))).click();
		this.driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[3]")).click();
		
		//this.waitLoading();
		this.driver.findElement(By.id(this.byId("et_transferAmount_own"))).sendKeys("10");
		this.hide_key();
		this.wait(10);
		
		//this.waitLoading();
		this.driver.findElement(By.id(this.byId("btnNext"))).click();
		
		this.waitById("switchTNC");
		this.driver.findElement(By.id(this.byId("switchTNC"))).click();
		this.driver.findElement(By.id(this.byId("btnConfirm"))).click();
		this.waitLoading();
		//this.waitLoading();
	}
	public void approveOwnAccount() throws InterruptedException {
		
		this.waitById("rl_pending_auth");
		this.driver.findElement(By.id(this.byId("rl_pending_auth"))).click();
		this.wait(5);
		String visibleText="Own Account";
		this.wait(1);
		this.driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(2))");
		this.driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.RelativeLayout")).click();
		//System.out.println(check);
		this.waitById("authorizeLL");
		this.driver.findElement(By.id(this.byId("authorizeLL"))).click();
		//this.waitLoading();
		this.resolveOTP();
	}
	public String getBalance() {
		String balance;			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[2]/android.widget.RelativeLayout[1]/android.widget.TextView[2]")));
		balance=this.driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[2]/android.widget.RelativeLayout[1]/android.widget.TextView[2]")).getText();
		
		return balance;
	}
	
//	public String byClass(String clas) {
//		return clas="com.sitpros.alsalam:id/"+clas;
//	}
//	public String byPath(String path) {
//		return path="com.sitpros.alsalam:id/"+path;
//	}
}
