package pmo.project.handlers.query;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.models.DocumentInfo;

public class QueryDocumentExecutor extends Handler {
	
	@Override
	public HandlerResponse<DocumentInfo> process(Object request) {
		HandlerResponse<DocumentInfo> response = new HandlerResponse<DocumentInfo>();
		try {
		
			String reportId = (String) request;
		
			DocumentInfo report = _documentRepo.get(reportId);
			response.setObject(report);
		}
		catch(Exception ex) {
			response.getErrorResponse();
		}
		
		return response;
	}
}
