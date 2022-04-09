package pmo.project.handlers.response;

import java.util.HashMap;
import java.util.Map;

public class ProjectFeasibilityResponse extends HandlerResponse {

	private Map<String, String> _failureResons;
	
	public ProjectFeasibilityResponse() {
		_failureResons = new HashMap<String, String>();
	}
	
	public Map<String, String> getFailureResons() {
		return _failureResons;
	}
	
}
