package java15.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id_generator")
    @SequenceGenerator(name = "task_id_generator", sequenceName = "task_seq_name")
    private Long id;
    private String description;
    private LocalDate deadLine;
    @OneToOne
    private Lesson lesson;
}
