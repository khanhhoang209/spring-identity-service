package com.fpt.identityservice.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse {
    @JsonIgnore
    private boolean succeeded;
    private String status;
    @JsonIgnore
    private HttpStatus statusCode;
    private int responseCode;
    private Map<String, Object> Details;

    public ServiceResponse() {
        this.succeeded = true;
        this.status = "success";
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public String getStatus() {
        return status;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public Map<String, Object> getDetails() {
        return Details;
    }

    public ServiceResponse setSucceeded(boolean status) {
        this.status = status ? "success" : "fail";
        this.succeeded = status;
        return this;
    }

    public ServiceResponse addDetail(String key, Object value) {
        if (Details == null) {
            Details = new LinkedHashMap<>();
        }
        Details.put(toKebabCase(key), value);
        return this;
    }

    public ServiceResponse addError(String key, String value) {
        if (Details == null) {
            Details = new LinkedHashMap<>();
        }

        if (!Details.containsKey("errors")) {
            Details.put("errors", new LinkedHashMap<String, String>());
        }

        var errors = (LinkedHashMap<String, String>) Details.get("errors");
        errors.put(toKebabCase(key), value);
        return this;
    }

    public ServiceResponse setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ServiceResponse setResponseCode(int responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    private String toKebabCase(String key) {
        return key.replaceAll("([a-z])([A-Z])", "$1-$2").toLowerCase();
    }

}
