package java15.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_id_generator")
    @SequenceGenerator(name = "lesson_id_generator", sequenceName = "lesson_seq_name")
    private Long id;
    private String title;
    private String description;
    private LocalDate publishedDate;
    private String videoLink;
    private boolean isPresentation;
    @ManyToOne
    private Course course;
    @OneToOne
    private Task task;
}
