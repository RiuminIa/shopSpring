package sk.stuba.fei.uim.oop.assignment3.book.material;

import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.assignment3.author.material.Author;
import sk.stuba.fei.uim.oop.assignment3.book.net.cover.BookRequest;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Author author;
    private String name;

    public Book(BookRequest b) {
        this.name=b.getName();
        this.description=b.getDescription();
        this.amount=b.getAmount();
        this.lendCount=b.getLendCount();
        this.pages=b.getPages();
    }

    private String description;
    private int pages;
    private int amount;
    private int lendCount;


}
