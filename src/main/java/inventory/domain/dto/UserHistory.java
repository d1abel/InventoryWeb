package inventory.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserHistory {

    private Integer id;
    private String pc;
    private String currentuser;
    private LocalDateTime startDate;
    private LocalDateTime endDate;



}
