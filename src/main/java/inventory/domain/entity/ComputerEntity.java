package inventory.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "computers")
public class ComputerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String pcname;
    private String motherboard;
    private String chipset;
    private String memory;
    private String hdd;
    @Column(name = "hdd_space")
    private String hddSpace;
    private String display;
    @Column(name = "inet_addr")
    private String inetAddr;
    @Column(name = "last_names")
    private String lastNames;
    private String mac;

    @ManyToOne
    @JoinColumn(name = "procname")
    private ProcessorEntity processor;

    @ManyToOne
    @JoinColumn(name = "osname")
    private OSEntity operatingSystem;

    @ManyToOne
    @JoinColumn(name = "userLogin")
    private ComputerUserEntity loggedUser;

}
