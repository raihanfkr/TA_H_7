package apap.ta.sipelatihan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
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
@Table(name="user")
public class UserModel implements Serializable {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer uuid;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;

    @NotNull
    @Size(max = 50)
    @Column(name = "username", nullable = false)
    private String username;

    @NotNull
    @Size(max = 200)
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "userPenyetuju", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PelatihanModel> listPelatihanDisetujui;

    @OneToMany(mappedBy = "userPengaju", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PelatihanModel> listPelatihanDiajukan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role", referencedColumnName = "id_role")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RoleModel role;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PelatihanModel> getListPelatihanDisetujui() {
        return listPelatihanDisetujui;
    }

    public void setListPelatihanDisetujui(List<PelatihanModel> listPelatihanDisetujui) {
        this.listPelatihanDisetujui = listPelatihanDisetujui;
    }

    public List<PelatihanModel> getListPelatihanDiajukan() {
        return listPelatihanDiajukan;
    }

    public void setListPelatihanDiajukan(List<PelatihanModel> listPelatihanDiajukan) {
        this.listPelatihanDiajukan = listPelatihanDiajukan;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }
}
