package uk.co.simplyhealth;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.springframework.beans.factory.annotation.Value;

public class UndeploymentComponent implements Callable {

	@Value("#{systemEnvironment['MULE_HOME']}")
	private String muleHomeDirectoryPath;
	
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		//TODO can we undeploy the app in a 'better' way? via the platform APIs or via Mule APIs?
		return Files.deleteIfExists(Paths.get(muleHomeDirectoryPath + "/apps/mule-runtime-agent-bootstrapper-anchor.txt"));
		
	}

}
