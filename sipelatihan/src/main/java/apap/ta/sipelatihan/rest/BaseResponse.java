package apap.ta.sipelatihan.rest;

import apap.ta.sipelatihan.model.PelatihanModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse {
    private int status;
    private String message;
    private PegawaiDTO result;
    private PelatihanModel resultPelatihan;
    private List<Map<String,Object>> resultPesertaPelatihan;

    public List<Map<String, Object>> getResultPesertaPelatihan() {
        return resultPesertaPelatihan;
    }

    public void setResultPesertaPelatihan(List<Map<String, Object>> resultPesertaPelatihan) {
        this.resultPesertaPelatihan = resultPesertaPelatihan;
    }

    public PelatihanModel getResultPelatihan() {
        return resultPelatihan;
    }

    public void setResultPelatihan(PelatihanModel resultPelatihan) {
        this.resultPelatihan = resultPelatihan;
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
     * @return the result
     */
    public PegawaiDTO getResult() {
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
    public void setResult(PegawaiDTO result) {
        this.result = result;
    }
}