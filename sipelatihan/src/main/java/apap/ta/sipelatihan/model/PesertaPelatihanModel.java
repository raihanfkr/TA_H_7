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
@Table(name="peserta_pelatihan")
public class PesertaPelatihanModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 200)
    @Column(name = "no_peserta", nullable = false)
    private String no_peserta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pelatihan", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PelatihanModel pelatihan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_peserta", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PesertaModel peserta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo_peserta() {
        return no_peserta;
    }

    public void setNo_peserta(String no_peserta) {
        this.no_peserta = no_peserta;
    }

    public PelatihanModel getPelatihan() {
        return pelatihan;
    }

    public void setPelatihan(PelatihanModel pelatihan) {
        this.pelatihan = pelatihan;
    }

    public PesertaModel getPeserta() {
        return peserta;
    }

    public void setPeserta(PesertaModel peserta) {
        this.peserta = peserta;
    }
}
