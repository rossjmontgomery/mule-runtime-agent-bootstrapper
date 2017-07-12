package uk.co.simplyhealth;

import java.io.File;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.springframework.beans.factory.annotation.Value;

public class ServerRegistrationComponent implements Callable {

	@Value("${server.name}")
	private String serverName;
	
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		String registrationToken = eventContext.getMessage().getPayloadAsString();
		
		ProcessBuilder processBuilder = new ProcessBuilder();
		
		// ./amc_setup --encrypt --hybrid $ANYPOINT_PLATFORM_REGISTRATION_TOKEN mule-container-test
		processBuilder.command("/opt/mule/bin/amc_setup", "--encrypt", "--hybrid", registrationToken, serverName);

		processBuilder.directory(new File("/opt/mule/bin"));
		processBuilder.inheritIO();
		
//		Map<String, String> processEnvironment = processBuilder.environment();
//		processEnvironment.put("MULE_HOME", "");
		
		Process process = processBuilder.start();
		
		if (process.waitFor() != 0) {
			throw new RuntimeException("registration process failed!");
		}
		return null;
	}

}
