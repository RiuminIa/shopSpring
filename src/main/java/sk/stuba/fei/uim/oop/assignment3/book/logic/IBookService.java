package sk.stuba.fei.uim.oop.assignment3.book.logic;

import sk.stuba.fei.uim.oop.assignment3.author.material.Author;
import sk.stuba.fei.uim.oop.assignment3.book.material.Book;
import sk.stuba.fei.uim.oop.assignment3.book.net.cover.BookAmountRequest;
import sk.stuba.fei.uim.oop.assignment3.book.net.cover.BookChangeRequest;
import sk.stuba.fei.uim.oop.assignment3.book.net.cover.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundObjectException;

import java.util.List;

public interface IBookService {

    List<Book> getAll();

    Book create(BookRequest bookRequest) throws NotFoundObjectException;

    Book getById(Long id) throws NotFoundObjectException;

    Book changeById(Long id, BookChangeRequest bookChangeRequest) throws NotFoundObjectException;

    void delete(Long id) throws NotFoundObjectException;

    void deleteAuthorBooks(Author author);

    int getAmount(Long id) throws NotFoundObjectException;

    int setAmount(Long id, BookAmountRequest bookAmountRequest) throws NotFoundObjectException;

    int getLendCount(Long id) throws NotFoundObjectException;
    void save(Book book);
}
