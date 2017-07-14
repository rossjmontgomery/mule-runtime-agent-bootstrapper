package uk.co.simplyhealth;

import java.io.File;
import java.nio.file.Paths;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class ServerRestartComponent implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		ProcessBuilder processBuilder = new ProcessBuilder();
		
		//processBuilder.command("nohup", "/opt/mule/bin/mule-runner.sh", "restart");
		processBuilder.command("/opt/mule/bin/mule-runner.sh", "restart");

		processBuilder.directory(new File("/opt/mule/bin"));
		//processBuilder.inheritIO();
		processBuilder.redirectOutput(Paths.get("/opt/mule/logs/mule-runner-restart.log").toFile());
		processBuilder.redirectErrorStream(true);
				
		Process process = processBuilder.start();
		
		process.waitFor();
		
		throw new RuntimeException("restart process failed!");
		
	}

}
