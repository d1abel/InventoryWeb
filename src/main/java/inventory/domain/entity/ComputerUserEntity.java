package inventory.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class ComputerUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "login")
    private String userLogin;
    private String company;
    private String department;

    @OneToMany(mappedBy = "loggedUser")
    private List<ComputerEntity> pcs = new ArrayList<>();

    public ComputerUserEntity(ComputerEntity pc) {
        this.pcs.add(pc);
    }

    public ComputerUserEntity(String userLogin) {
        this.userLogin = userLogin;
    }
}
