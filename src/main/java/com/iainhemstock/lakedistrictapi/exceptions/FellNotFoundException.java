package com.iainhemstock.lakedistrictapi.exceptions;

public final class FellNotFoundException extends RuntimeException {
    private int fellId;
    private String timestamp;

    public FellNotFoundException(int fellId, String timestamp) {
        super(String.format("Fell was not found for {id=%d}", fellId));
        this.fellId = fellId;
        this.timestamp = timestamp;
    }

    public int getUnrecognizedId() {
        return this.fellId;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
