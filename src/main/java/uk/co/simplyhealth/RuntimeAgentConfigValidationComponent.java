package uk.co.simplyhealth;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.springframework.beans.factory.annotation.Value;

public class RuntimeAgentConfigValidationComponent implements Callable {

	@Value("#{systemEnvironment['MULE_HOME']}")
	private String muleHomeDirectoryPath;
	
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		return Files.exists(Paths.get(muleHomeDirectoryPath + "/conf/mule-agent.yml"));
	}

}
