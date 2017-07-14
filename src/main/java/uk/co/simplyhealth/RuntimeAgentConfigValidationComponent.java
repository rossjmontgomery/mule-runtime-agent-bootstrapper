package uk.co.simplyhealth;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class RuntimeAgentConfigValidationComponent implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		return Files.exists(Paths.get("/opt/mule/conf/mule-agent.yml"));
	}

}
