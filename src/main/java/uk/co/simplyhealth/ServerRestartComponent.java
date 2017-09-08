package uk.co.simplyhealth;

import java.io.File;
import java.nio.file.Paths;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.springframework.beans.factory.annotation.Value;

public class ServerRestartComponent implements Callable {

	@Value("#{systemEnvironment['MULE_HOME']}")
	private String muleHomeDirectoryPath;
	
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		ProcessBuilder processBuilder = new ProcessBuilder();
		
		//processBuilder.command("nohup", muleHomeDirectoryPath + "/bin/mule-runner.sh", "restart");
		processBuilder.command(muleHomeDirectoryPath + "/bin/mule-runner.sh", "restart");

		processBuilder.directory(new File(muleHomeDirectoryPath + "/bin"));
		//processBuilder.inheritIO();
		processBuilder.redirectOutput(Paths.get(muleHomeDirectoryPath + "/logs/mule-runner-restart.log").toFile());
		processBuilder.redirectErrorStream(true);
				
		Process process = processBuilder.start();
		
		process.waitFor();
		
		throw new RuntimeException("restart process failed!");
		
	}

}
