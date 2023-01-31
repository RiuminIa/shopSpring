package sk.stuba.fei.uim.oop.assignment3.bill.logic;

import sk.stuba.fei.uim.oop.assignment3.bill.net.cover.BookIdRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundObjectException;
import sk.stuba.fei.uim.oop.assignment3.bill.material.Bill;

import java.util.List;

public interface IBillService {
    List<Bill> getAll();
    Bill create();

    Bill getById(Long id) throws NotFoundObjectException;

    void deleteById(Long id) throws  NotFoundObjectException;

    Bill addBook(Long billId, BookIdRequest bookId) throws NotFoundObjectException, BadRequestException;

    void deleteBook(Long billId,BookIdRequest bookId) throws NotFoundObjectException;

    void rentBooks(Long billId) throws NotFoundObjectException, BadRequestException;
}
