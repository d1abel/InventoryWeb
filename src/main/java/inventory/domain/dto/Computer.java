package inventory.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Computer {

    private int id;

    private String pcname;
    private String motherboard;
    private String chipset;
    private String memory;
    private String hdd;
    private String hddSpace;
    private String display;
    private String inetAddr;
    private String lastNames;
    private String mac;
    private Processor procname;
    private Os osname;
    private PcUser loggedUser;

}
