package org.evwhite.algorithmapi;

public class Answer {

    private long id;
    private String stringResult;

    public Answer(long id, String stringResult) {
        this.id = id;
        this.stringResult = stringResult;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStringResult() {
        return stringResult;
    }

    public void setStringResult(String stringResult) {
        this.stringResult = stringResult;
    }
}
