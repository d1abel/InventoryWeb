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
@Table(name = "processors")
public class ProcessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String procname;

    @OneToMany(mappedBy = "processor")
    private List<ComputerEntity> pcs = new ArrayList<>();

    public ProcessorEntity(String procname) {
        this.procname = procname;
    }

    public ProcessorEntity(ComputerEntity pc) {
        this.pcs.add(pc);
    }

}
