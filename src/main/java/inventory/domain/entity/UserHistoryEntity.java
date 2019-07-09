package inventory.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_history")
public class UserHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String pc;
    private String currentuser;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    public UserHistoryEntity(String pc, String currentuser, LocalDate startDate, LocalDate endDate) {
        this.pc = pc;
        this.currentuser = currentuser;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
