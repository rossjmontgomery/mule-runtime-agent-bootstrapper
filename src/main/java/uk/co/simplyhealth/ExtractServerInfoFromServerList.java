package uk.co.simplyhealth;

import java.util.List;
import java.util.Map;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;
import org.springframework.beans.factory.annotation.Value;

public class ExtractServerInfoFromServerList extends AbstractTransformer {
	
	@Value("#{systemEnvironment['SERVER_NAME']}")
	private String serverName;

	@Override
	protected Object doTransform(Object src, String enc) throws TransformerException {
		
		@SuppressWarnings("unchecked")
		List<Map<Object, Object>> servers = ((Map<String, List<Map<Object, Object>>>) src).get("data");
		
		return servers.stream()
				.filter(server -> serverName.equalsIgnoreCase((String) server.get("name")))
				.findFirst().orElseGet(() -> null);
		
	}

}
