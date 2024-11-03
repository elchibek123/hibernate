package java15.entities;

import jakarta.persistence.*;
import java15.enums.BookType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_generator")
    @SequenceGenerator(name = "book_id_generator", sequenceName = "book_seq_name", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "name", length = 50)
    private String title;

    @Column(name = "author_full_name", unique = true)
    private String authorFullName;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private BookType bookType;

    public Book(String title, String authorFullName, BigDecimal price, BookType bookType) {
        this.title = title;
        this.authorFullName = authorFullName;
        this.price = price;
        this.bookType = bookType;
    }
}
