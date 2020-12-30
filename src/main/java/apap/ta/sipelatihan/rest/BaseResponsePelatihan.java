package apap.ta.sipelatihan.rest;

import apap.ta.sipelatihan.model.PelatihanModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponsePelatihan {
    private int status;
    private String message;
    private PelatihanModel result;

    public PelatihanModel getResult() {
        return result;
    }

    public void setResult(PelatihanModel result) {
        this.result = result;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}