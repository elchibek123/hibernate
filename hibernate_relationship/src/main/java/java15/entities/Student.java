package java15.entities;

import jakarta.persistence.*;
import java15.enums.Gender;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student extends BaseEntity {
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "year_of_birth", nullable = false)
    private int yearOfBirth;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private Set<Course> courses;
}
