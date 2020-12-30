package apap.ta.sipelatihan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import apap.ta.sipelatihan.model.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="pelatihan")
public class PelatihanModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama_pelatihan", nullable = false)
    private String nama_pelatihan;

    @NotNull
    @Size(max = 200)
    @Column(name = "deskripsi", nullable = false)
    private String deskripsi;

    @NotNull
    @Column(name = "kapasitas", nullable = false)
    private Integer kapasitas;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggal_mulai", nullable = false)
    private Date tanggal_mulai;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggal_selesai", nullable = false)
    private Date tanggal_selesai;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "waktu_mulai", nullable = false)
    private Date waktu_mulai;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "waktu_selesai", nullable = false)
    private Date waktu_selesai;

    @NotNull
    @Column(name = "status_persetujuan", nullable = false)
    private Integer status_persetujuan = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_trainer", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TrainerModel trainer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_jenis_pelatihan", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JenisPelatihanModel jenis_pelatihan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uuid_penyetuju", referencedColumnName = "uuid", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel userPenyetuju;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uuid_pengaju", referencedColumnName = "uuid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel userPengaju;

    @OneToMany(mappedBy = "pelatihan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PesertaPelatihanModel> listPesertaPelatihan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama_pelatihan() {
        return nama_pelatihan;
    }

    public void setNama_pelatihan(String nama_pelatihan) {
        this.nama_pelatihan = nama_pelatihan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(Integer kapasitas) {
        this.kapasitas = kapasitas;
    }

    public Date getTanggal_mulai() {
        return tanggal_mulai;
    }

    public void setTanggal_mulai(Date tanggal_mulai) {
        this.tanggal_mulai = tanggal_mulai;
    }

    public Date getTanggal_selesai() {
        return tanggal_selesai;
    }

    public void setTanggal_selesai(Date tanggal_selesai) {
        this.tanggal_selesai = tanggal_selesai;
    }

    public Date getWaktu_mulai() {
        return waktu_mulai;
    }

    public void setWaktu_mulai(Date waktu_mulai) {
        this.waktu_mulai = waktu_mulai;
    }

    public Date getWaktu_selesai() {
        return waktu_selesai;
    }

    public void setWaktu_selesai(Date waktu_selesai) {
        this.waktu_selesai = waktu_selesai;
    }

    public Integer getStatus_persetujuan() {
        return status_persetujuan;
    }

    public void setStatus_persetujuan(Integer status_persetujuan) {
        this.status_persetujuan = status_persetujuan;
    }

    public TrainerModel getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerModel trainer) {
        this.trainer = trainer;
    }

    public JenisPelatihanModel getJenis_pelatihan() {
        return jenis_pelatihan;
    }

    public void setJenis_pelatihan(JenisPelatihanModel jenis_pelatihan) {
        this.jenis_pelatihan = jenis_pelatihan;
    }

    public UserModel getUserPenyetuju() {
        return userPenyetuju;
    }

    public void setUserPenyetuju(UserModel userPenyetuju) {
        this.userPenyetuju = userPenyetuju;
    }

    public UserModel getUserPengaju() {
        return userPengaju;
    }

    public void setUserPengaju(UserModel userPengaju) {
        this.userPengaju = userPengaju;
    }

    public List<PesertaPelatihanModel> getListPesertaPelatihan() {
        return listPesertaPelatihan;
    }

    public void setListPesertaPelatihan(List<PesertaPelatihanModel> listPesertaPelatihan) {
        this.listPesertaPelatihan = listPesertaPelatihan;
    }
}
