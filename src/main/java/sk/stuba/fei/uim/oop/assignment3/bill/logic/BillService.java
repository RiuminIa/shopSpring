package sk.stuba.fei.uim.oop.assignment3.bill.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.bill.net.cover.BookIdRequest;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.book.material.Book;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundObjectException;
import sk.stuba.fei.uim.oop.assignment3.bill.material.Bill;
import sk.stuba.fei.uim.oop.assignment3.bill.material.IBillRepositor;

import java.util.List;
import java.util.Optional;

@Service
public class BillService implements IBillService {

    @Autowired
    private IBillRepositor billRepositor;
    @Autowired
    private IBookService bookService;

    @Override
    public List<Bill> getAll() {
        return this.billRepositor.findAll();
    }
    @Override
    public Bill create() {
        Bill bill =new Bill();
        return this.billRepositor.save(bill);
    }
    @Override
    public Bill getById(Long id) throws NotFoundObjectException {
        Optional<Bill> optional;
        optional=this.billRepositor.findById(id);
        if(!optional.isPresent()){
            throw new NotFoundObjectException();
        }
        return optional.get();
    }
    @Override
    public void deleteById(Long id) throws NotFoundObjectException{
        Bill bill=this.getById(id);
        if(bill.isLended()){
            for(Book book:bill.getLendingList()){
                book.setLendCount(book.getLendCount()-1);
                this.bookService.save(book);
            }
        }
        this.billRepositor.delete(bill);
    }

    @Override
    public Bill addBook(Long billId, BookIdRequest bookId) throws NotFoundObjectException, BadRequestException {
        Book book;
        book=this.bookService.getById(bookId.getId());
        Bill bill;
        bill=this.getById(billId);
        if(bill.isLended() || bill.getLendingList().contains(book)){
            throw new BadRequestException();
        }
        bill.getLendingList().add(book);
        return this.billRepositor.save(bill);
    }

    @Override
    public void deleteBook(Long billId, BookIdRequest  bookId) throws NotFoundObjectException{
        Bill bill;
        bill=this.getById(billId);
        bill.getLendingList().remove(this.bookService.getById(bookId.getId()));
    }

    @Override
    public void rentBooks(Long billId) throws NotFoundObjectException, BadRequestException {
        Bill bill=this.getById(billId);
        if(bill.isLended()){
            throw new BadRequestException();
        }
        for(Book book:bill.getLendingList()){
            book.setLendCount(book.getLendCount()+1);
            this.bookService.save(book);
        }
        bill.setLended(true);
        this.billRepositor.save(bill);
    }
}



