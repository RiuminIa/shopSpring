package sk.stuba.fei.uim.oop.assignment3.author.net.cover;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.author.material.Author;
import sk.stuba.fei.uim.oop.assignment3.book.material.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AuthorResponse {
    private final Long id;
    private final String name;
    private final String surname;
    private final List<Long> books;

    public AuthorResponse(Author a) {
        this.id = a.getId();
        this.name = a.getName();
        this.surname = a.getSurname();
        this.books = new ArrayList<>();
        if (a.getBooks() != null) {
            books.addAll(a.getBooks().stream().map(Book::getId).collect(Collectors.toList()));
        }
    }
}
