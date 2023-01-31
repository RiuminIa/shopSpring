package sk.stuba.fei.uim.oop.assignment3.author.material;

import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.assignment3.author.net.cover.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.book.material.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @OneToMany
    private List<Book> books;

    public Author(AuthorRequest authorRequest) {
        this.name=authorRequest.getName();
        this.surname=authorRequest.getSurname();
        this.books=new ArrayList<>();
    }
}
