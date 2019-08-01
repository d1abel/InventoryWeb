package inventory.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComputerUser {

    private Integer id;
    private String userLogin;
    private String company;
    private String department;
    private List<Computer> computers = new ArrayList<>();

}
