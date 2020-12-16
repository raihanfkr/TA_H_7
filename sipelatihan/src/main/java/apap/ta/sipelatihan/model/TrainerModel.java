package apap.ta.sipelatihan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import apap.ta.sipelatihan.model.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="trainer")
public class TrainerModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 16)
    @Column(name = "noKtp", nullable = false)
    private String noKtp;

    @NotNull
    @Size(max = 200)
    @Column(name = "nama_trainer", nullable = false)
    private String nama_trainer;

    @NotNull
    @Size(max = 200)
    @Column(name = "no_telepon", nullable = false)
    private String no_telepon;

    @NotNull
    @Size(max = 100)
    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PelatihanModel> listPelatihan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getNama_trainer() {
        return nama_trainer;
    }

    public void setNama_trainer(String nama_trainer) {
        this.nama_trainer = nama_trainer;
    }

    public String getNo_telepon() {
        return no_telepon;
    }

    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PelatihanModel> getListPelatihan() {
        return listPelatihan;
    }

    public void setListPelatihan(List<PelatihanModel> listPelatihan) {
        this.listPelatihan = listPelatihan;
    }
}
