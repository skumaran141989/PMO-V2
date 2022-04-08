package pmo.project.handlers.response;

import java.util.ArrayList;
import java.util.List;

public class HandlerResponse {
	private List<String> _errorResponse;
	
	public HandlerResponse() {
		_errorResponse = new ArrayList<String>();
	}
	
	public List<String> getErrorResponse() {
		return _errorResponse;
	}
}
