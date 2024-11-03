package java15.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    @SequenceGenerator(name = "id_gen", sequenceName = "id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }
}
