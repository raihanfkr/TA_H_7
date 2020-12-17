package apap.ta.sipelatihan.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse<Test>{
    private int status;
    private String message;
    private Test result;
//    private PegawaiDTO results;

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
     * @return the result
     */
    public Test getResult() {

//    public PegawaiDTO getResults() {
        return result;
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
    /**
     * @param result the result to set
     */
    public void setResult(Test result) {
        this.result = result;
    }
}
//=======
//    public void setResult(PegawaiDTO result) {
//        this.result = result;
//    }
//}