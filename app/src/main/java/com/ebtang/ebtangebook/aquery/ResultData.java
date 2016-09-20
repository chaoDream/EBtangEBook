package com.ebtang.ebtangebook.aquery;

import java.io.Serializable;

/**
 * Created by xunice on 7/4/15.
 */
public class ResultData implements Serializable {
    private Task ts;
    private String message;

    public ResultData(Task ts, String message){
        this.ts = ts;
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Task getTs() {
        return ts;
    }

    public void setTs(Task ts) {
        this.ts = ts;
    }
}
