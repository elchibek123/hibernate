package java15.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mentors")
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mentor_id_generator")
    @SequenceGenerator(name = "mentor_id_generator", sequenceName = "mentor_seq_name")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(unique = true)
    private String email;
    @Column(name = "year_of_birth")
    private int yearOfBirth;
}
