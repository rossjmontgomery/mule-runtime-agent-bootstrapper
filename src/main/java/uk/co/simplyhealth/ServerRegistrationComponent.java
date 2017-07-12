package uk.co.simplyhealth;

import java.util.Map;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class ServerRegistrationComponent implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("", "", "");
		processBuilder.inheritIO();
		
		Map<String, String> processEnvironment = processBuilder.environment();
		processEnvironment.put("MULE_HOME", "");
		
		Process process = processBuilder.start();
		
		if (process.waitFor() != 0) {
			throw new RuntimeException("registration process failed!");
		}
		return null;
	}

}
