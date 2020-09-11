package org.hillmerch.player.client.handler;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(name="ErrorMessage" , description = "Errors detail response")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage implements Serializable {

	private String message;
	private String resource;
	private String status;
	private List<ErrorMessage> errorMessageList;

	public ErrorMessage() {
	}

	public ErrorMessage(String resource, String message) {
		this.resource = resource;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ErrorMessage> getErrorMessageList() {
		return errorMessageList;
	}

	public void setErrorMessageList(List<ErrorMessage> errorMessageList) {
		this.errorMessageList = errorMessageList;
	}

	@Override
	public String toString() {
		return "ErrorMessage{" +
				"message='" + message + '\'' +
				", resource='" + resource + '\'' +
				", status='" + status + '\'' +
				", errorMessageList=" + errorMessageList +
				'}';
	}
}
