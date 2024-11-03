package java15.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "persons")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private int age;
    @Column(unique = true, nullable = false)
    private String email;
    @OneToMany(mappedBy = "person", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<SocialMedia> socialMedia;
}
