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
@Table(name="jenis_pelatihan")
public class JenisPelatihanModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 200)
    @Column(name = "nama", nullable = false)
    private String nama;

    @OneToMany(mappedBy = "jenis_pelatihan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PelatihanModel> listPelatihan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<PelatihanModel> getListPelatihan() {
        return listPelatihan;
    }

    public void setListPelatihan(List<PelatihanModel> listPelatihan) {
        this.listPelatihan = listPelatihan;
    }
}
