package uk.co.simplyhealth;

import java.io.File;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.springframework.beans.factory.annotation.Value;

public class ServerRegistrationComponent implements Callable {

	@Value("#{systemEnvironment['MULE_HOME']}")
	private String muleHomeDirectoryPath;
	
	@Value("#{systemEnvironment['SERVER_NAME']}")
	private String serverName;
	
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		String registrationToken = eventContext.getMessage().getPayloadAsString();
		
		ProcessBuilder processBuilder = new ProcessBuilder();
		
		// ./amc_setup --encrypt --hybrid $ANYPOINT_PLATFORM_REGISTRATION_TOKEN mule-container-test
		processBuilder.command(muleHomeDirectoryPath + "/bin/amc_setup", "--encrypt", "--hybrid", registrationToken, serverName);

		processBuilder.directory(new File(muleHomeDirectoryPath + "/bin"));
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
