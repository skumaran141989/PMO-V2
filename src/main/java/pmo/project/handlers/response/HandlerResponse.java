package pmo.project.handlers.response;

import java.util.ArrayList;
import java.util.List;

public class HandlerResponse<T> {
	private List<String> _errorResponse;
	private T _object;
	
	public HandlerResponse() {
		_errorResponse = new ArrayList<String>();
	}
	
	public List<String> getErrorResponse() {
		return _errorResponse;
	}
	
	public T getObject() {
		return _object;
	}
	
	public void setObject(T object) {
		_object= object;
	}
}
