package uk.co.simplyhealth;

import java.util.List;
import java.util.Map;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;
import org.springframework.beans.factory.annotation.Value;

public class ExtractServerRegistrationStatusFromServerList extends AbstractTransformer {
	
	@Value ("${server.name}")
	private String serverName;

	@Override
	protected Object doTransform(Object src, String enc) throws TransformerException {
		
		@SuppressWarnings("unchecked")
		List<Map<Object, Object>> servers = ((Map<String, List<Map<Object, Object>>>) src).get("data");
		
		return servers.stream()
				.filter(server -> serverName.equals(server.get("name")))
				.findFirst().get()
				.get("status");
		
	}

}
