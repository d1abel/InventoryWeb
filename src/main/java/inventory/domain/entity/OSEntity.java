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
@Table(name = "os")
public class OSEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String osname;

    @OneToMany(mappedBy = "operatingSystem")
    private List<ComputerEntity> pcs = new ArrayList<>();

    public OSEntity(ComputerEntity pc) {
        this.pcs.add(pc);
    }

    public OSEntity(String osname) {
        this.osname = osname;
    }
}
