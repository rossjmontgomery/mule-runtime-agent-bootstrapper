package uk.co.simplyhealth;

import java.util.List;
import java.util.Map;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;
import org.springframework.beans.factory.annotation.Value;

public class ExtractEnvironmentIdFromJsonResponse extends AbstractTransformer {
	
	@Value("#{systemEnvironment['ENVIRONMENT_NAME']}")
	private String environmentName;
	
	@Override
	protected Object doTransform(Object src, String enc) throws TransformerException {
		
		@SuppressWarnings("unchecked")
		List<Map<Object, Object>> environments = (List<Map<Object, Object>>) src;
		
		return environments.stream()
			.filter(environment -> environmentName.equalsIgnoreCase((String) environment.get("name")))
			.findFirst().get()
			.get("id");
	}

}
