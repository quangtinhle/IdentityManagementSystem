package com.example.egovernmentportal.model;


import lombok.Setter;

@Setter
public class InformationResponse {
    public String result;
    public Boolean value;

    public InformationResponse(String result, Boolean value) {
        this.result = result;
        this.value = value;
    }

    public String getResult() {
        return result;
    }

    public Boolean getValue() {
        return value;
    }
}
