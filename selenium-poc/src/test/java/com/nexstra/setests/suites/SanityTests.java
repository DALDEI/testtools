package com.nexstra.setests.suites;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.nexstra.setests.shallowtests.KLA;
import com.nexstra.setests.shallowtests.NewAdmin;



@RunWith(Suite.class)
@Suite.SuiteClasses({
		KLA.class,
	  NewAdmin.class
})
public class SanityTests {
	
	//the main method helps to generate standalone jar from eclipse
	public static void main(String[] args) throws Exception {                    
	       JUnitCore.main(
	         "com.nexstra.setests.suites.SanityTests");            
	}
}
