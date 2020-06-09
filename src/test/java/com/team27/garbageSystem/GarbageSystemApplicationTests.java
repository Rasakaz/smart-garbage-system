package com.team27.garbageSystem;

import com.team27.garbageSystem.Entities.Administrator;
import com.team27.garbageSystem.Entities.GarbageBin;
import com.team27.garbageSystem.controllers.AboutController;
import com.team27.garbageSystem.controllers.ContactController;
import com.team27.garbageSystem.controllers.HomeController;
import com.team27.garbageSystem.controllers.LoginController;
import org.assertj.core.error.ShouldBeSymbolicLink;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class GarbageSystemApplicationTests {

	@Test
	void GarbageContextLoads(){
		Garbage_FIle_Tester Bins = new Garbage_FIle_Tester();
		Assertions.assertEquals(true, BinsCoordinatesTest(Bins.getGarbageFile()));
		Assertions.assertEquals(true, BinsIdTest(Bins.getGarbageFile()));
	}

	@Test
	void LoginTest(){
		Login_Tester login_tester = new Login_Tester();
		Map<String, String> user = new HashMap<String, String>(){
			{
				put("UserType", "adminn");
				put("UserName", "admin1");
				put("Password", "1234admin1");
			}
		};
//		Assertions.assertEquals(true, LoginRoutesTest(login_tester.getLogin()));
//		Assertions.assertEquals(true, AdministratorLoginTest(login_tester.getLogin(), user));
	}

	@Test
	void AboutTest(){
		Assertions.assertEquals(true, AboutRoutesTest(new AboutController()));
	}

	@Test
	void HomeTest(){
		Assertions.assertEquals(true, HomeRoutesTest(new HomeController()));
	}

	@Test
	void ContactTest(){
		Assertions.assertEquals(true, ContactRoutesTest(new ContactController()));
	}

	private Boolean ContactRoutesTest(ContactController contact_tester){
		return contact_tester.home().equals("contact");
	}

	private Boolean HomeRoutesTest(HomeController home_tester){
		return home_tester.home().equals("home");
	}

	private Boolean AboutRoutesTest(AboutController about_tester){
		return about_tester.home().equals("about");
	}

	private Boolean AdministratorLoginTest(LoginController login_tester, Map<String, String> test_user) {
		return login_tester.loginCheck(test_user).equals("failed");
	}

//	private Boolean LoginRoutesTest(LoginController login_tester){
//		return (login_tester.login().equals("login"));
//	}

	private Boolean BinsIdTest(List<GarbageBin> bins) {
		for(GarbageBin bin: bins){
			if(bin.getId() < 0){
				return false;
			}
		}
		return true;
	}

	private Boolean BinsCoordinatesTest(List<GarbageBin> bins) {
		for(GarbageBin bin: bins){
			if(bin.getLatitude() < 0 || bin.getLongitude() < 0){
				return false;
			}
		}
		return true;
	}
}
