package com.clone.digg.exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.clone.digg.model.GenericServiceResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author Manjeer
 *
 * Created on Jun 10, 2017
 * 
 * Exception handler for all services written in this codebase
 */
@ControllerAdvice({"com.clone.digg","org.springframework"})
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

	/**
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * 
	 *             Loads all configured error responses form errorConfig.json.
	 */
	public ExceptionControllerAdvice() throws JsonParseException, JsonMappingException, IOException {
		super();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("errorConfig.json");
		HashMap<String, LinkedHashMap> errorHashMapMap = (HashMap<String, LinkedHashMap>) new ObjectMapper()
				.readValue(in, HashMap.class);

		for (String key : errorHashMapMap.keySet()) {
			LinkedHashMap<String, Object> errorMessageHashMap = errorHashMapMap.get(key);
			errorCodeMap.put(key, new DiggCloneServiceError((Integer) errorMessageHashMap.get("status"),
					(String) errorMessageHashMap.get("errorMessage")));
		}
	}

	Map<String, DiggCloneServiceError> errorCodeMap = new HashMap<>();

	/**
	 * @param request
	 * @param ex
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(Exception.class)
	@ResponseBody
	ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
		DiggCloneServiceError diggCloneServiceError = null;
		if (ex != null) {
			ex.printStackTrace();
			diggCloneServiceError = errorCodeMap.get(ex.getMessage());
			if (diggCloneServiceError == null) {
				diggCloneServiceError = DiggCloneServiceError.getGenericError();
			} else if (ex instanceof DiggCloneServiceException) {
				if (ex.getMessage() == null) {
					diggCloneServiceError = DiggCloneServiceError.getGenericError();
				} else {
					diggCloneServiceError = errorCodeMap.get(ex.getMessage());
					if (diggCloneServiceError == null) {
						diggCloneServiceError = DiggCloneServiceError.getGenericError();
					}
				}

			} else {
				diggCloneServiceError = DiggCloneServiceError.getGenericError();
			}
		} else {
			diggCloneServiceError = DiggCloneServiceError.getGenericError();
		}
		return new ResponseEntity<GenericServiceResponse>(
				new GenericServiceResponse("FAIL", diggCloneServiceError.errorMessage, null),
				HttpStatus.valueOf(diggCloneServiceError.status));
	}
}