package uk.co.simplyhealth;

import java.util.List;
import java.util.Map;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

public class ExtractEnvironmentIdFromJsonResponse extends AbstractTransformer {

	@Override
	protected Object doTransform(Object src, String enc) throws TransformerException {
		
		@SuppressWarnings("unchecked")
		List<Map<Object, Object>> environments = (List<Map<Object, Object>>) src;
		
		return environments.stream()
			.filter(environment -> "Development".equals(environment.get("name")))
			.findFirst().get()
			.get("id");
	}

}
