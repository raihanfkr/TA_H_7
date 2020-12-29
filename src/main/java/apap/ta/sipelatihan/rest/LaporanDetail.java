package apap.ta.sipelatihan.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LaporanDetail {
    private String status;

    @JsonProperty("username")
    private String username;

    @JsonProperty("jumlah-training")
    private Integer jumlahTraining;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getJumlahTraining() {
        return jumlahTraining;
    }

    public void setJumlahTraining(Integer jumlahTraining) {
        this.jumlahTraining = jumlahTraining;
    }
}






