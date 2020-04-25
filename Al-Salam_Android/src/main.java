import java.net.MalformedURLException;

public class main {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		//dependencies
		String androidVer="10.0";
		String user="inputter.c1@abcd.com";
		String user1="authorizer@abcd.com";
		String pass="Alsalam2019";
		
		
		//functionality
		Appium app=new Appium();
		app.setCapabilities(androidVer);
		app.setDriver();
		
		System.out.println("Giving Permission...");
		app.permission();
		app.wait(2);
		
		System.out.println("Logging In Inputter...");
		app.Login(user, pass);
		app.wait(2);
		String startBalance;
		startBalance=app.getBalance();
		
		System.out.println("Making Own Account Transaction...");
		app.transfer();
		app.wait(2);
		app.own_account();
		System.out.println("Done Own Account Transaction...");
		
		
		
		System.out.println("Restarting App to Change User...");
		app.reset();
		
		
		System.out.println("Giving Permission...");
		app.permission();
		app.wait(2);
		
		System.out.println("Logging In Authorizer...");
		app.Login(user1, pass);
		
		System.out.println("Approving Own Account Transaction...");
		app.approveOwnAccount();
		System.out.println("Approved Own Account Transaction of BHD 3.76...");
		app.wait(5);
		
		
		System.out.println("Restarting App to Change User...");
		app.reset();
		
		System.out.println("Giving Permission...");
		app.permission();
		
		System.out.println("Logging In Inputter...");
		app.Login(user, pass);
		
		String currentBalance;
		currentBalance=app.getBalance();
		
		
		System.out.println("Beginning Balance: "+startBalance+" Current Balance: "+currentBalance +" After BHD 3.760 Transaction");
		System.out.println("Scenerio Completed...");
	}

}
