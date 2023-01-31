package sk.stuba.fei.uim.oop.assignment3.author.logic;

import sk.stuba.fei.uim.oop.assignment3.author.material.Author;
import sk.stuba.fei.uim.oop.assignment3.author.net.cover.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.book.material.Book;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundObjectException;

import java.util.List;

public interface IAuthorService {
    List<Author> getAll();
    Author create(AuthorRequest request);
    Author getById(Long id) throws NotFoundObjectException;
    Author changeById(Long id,AuthorRequest authorRequest) throws NotFoundObjectException;
    void deleteById(Long id) throws NotFoundObjectException;
    void save(Author a);
    void changeBook(Author from, Author to, Book book);
    void deleteBook(Author author,Book book) throws NotFoundObjectException;
}

