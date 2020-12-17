package apap.ta.sipelatihan.model;

import apap.ta.sipelatihan.model.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="peserta")
public class PesertaModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 100)
    @Column(name = "nama_peserta", nullable = false)
    private String nama_peserta;

    @NotNull
    @Size(max = 20)
    @Column(name = "no_telepon", nullable = false)
    private String no_telepon;

    @NotNull
    @Size(max = 100)
    @Column(name = "alamat", nullable = false)
    private String alamat;

    @NotNull
    @Size(max = 100)
    @Column(name = "departemen", nullable = false)
    private String departemen;

    @OneToMany(mappedBy = "peserta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PesertaPelatihanModel> listPesertaPelatihan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama_peserta() {
        return nama_peserta;
    }

    public void setNama_peserta(String nama_peserta) {
        this.nama_peserta = nama_peserta;
    }

    public String getNo_telepon() {
        return no_telepon;
    }

    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDepartemen() {
        return departemen;
    }

    public void setDepartemen(String departemen) {
        this.departemen = departemen;
    }

    public List<PesertaPelatihanModel> getListPesertaPelatihan() {
        return listPesertaPelatihan;
    }

    public void setListPesertaPelatihan(List<PesertaPelatihanModel> listPesertaPelatihan) {
        this.listPesertaPelatihan = listPesertaPelatihan;
    }
}
