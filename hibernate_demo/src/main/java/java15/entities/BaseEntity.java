package java15.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "publish_date", nullable = false, updatable = false)
    private LocalDate publishDate;

    @PrePersist
    protected void onPublish() {
        publishDate = LocalDate.now();
    }
}
