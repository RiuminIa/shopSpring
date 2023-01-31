package sk.stuba.fei.uim.oop.assignment3.book.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.logic.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.author.material.Author;
import sk.stuba.fei.uim.oop.assignment3.book.material.Book;
import sk.stuba.fei.uim.oop.assignment3.book.material.IBookRepositor;
import sk.stuba.fei.uim.oop.assignment3.book.net.cover.BookAmountRequest;
import sk.stuba.fei.uim.oop.assignment3.book.net.cover.BookChangeRequest;
import sk.stuba.fei.uim.oop.assignment3.book.net.cover.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundObjectException;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{
    @Autowired
    private IBookRepositor bookRepositor;
    @Autowired
    private IAuthorService iAuthorService;


    @Override
    public List<Book> getAll() {
        return bookRepositor.findAll();
    }

    @Override
    public Book create(BookRequest bookRequest) throws NotFoundObjectException {
        Book book=new Book(bookRequest);
        book.setAuthor(this.iAuthorService.getById(bookRequest.getAuthor()));
        book.getAuthor().getBooks().add(book);
        this.bookRepositor.save(book);
        this.iAuthorService.save(book.getAuthor());
        return book;
    }

    @Override
    public Book getById(Long id) throws NotFoundObjectException {
        Optional<Book> optional;
        if (id==null) {
            throw new NotFoundObjectException();
        }
        optional = this.bookRepositor.findById(id);
        if(!optional.isPresent()){
            throw new NotFoundObjectException();
        }
        return optional.get();
    }

    @Override
    public Book changeById(Long id, BookChangeRequest bookChangeRequest) throws NotFoundObjectException{
        Book book=getById(id);
        if(bookChangeRequest.getDescription()!=null){
            book.setDescription(bookChangeRequest.getDescription());
        }
        if(bookChangeRequest.getName()!=null){
            book.setName(bookChangeRequest.getName());
        }
        if(bookChangeRequest.getPages()!=0){
            book.setPages(bookChangeRequest.getPages());
        }
        Author author;
        if (bookChangeRequest.getAuthor() != null && bookChangeRequest.getAuthor() != 0) {
            author=this.iAuthorService.getById(bookChangeRequest.getAuthor());
            this.iAuthorService.changeBook(book.getAuthor(),author,book);
            book.setAuthor(author);
        }
        return this.bookRepositor.save(book);
    }

    @Override
    public void delete(Long id) throws NotFoundObjectException {
        Book book=this.getById(id);
        if (book.getAuthor()!=null){
            this.iAuthorService.deleteBook(book.getAuthor(),book);
        }
        this.bookRepositor.delete(book);
    }

    @Override
    public void deleteAuthorBooks(Author author) {
            this.bookRepositor.deleteAll(author.getBooks());
    }

    @Override
    public int getAmount(Long id) throws NotFoundObjectException {
        return this.getById(id).getAmount();
    }

    @Override
    public int setAmount(Long id, BookAmountRequest bookAmountRequest) throws NotFoundObjectException {
        Book book = this.getById(id);
        book.setAmount(book.getAmount()+bookAmountRequest.getAmount());
        return this.bookRepositor.save(book).getAmount();
    }

    @Override
    public int getLendCount(Long id) throws NotFoundObjectException {
        return this.getById(id).getLendCount();
    }

    @Override
    public void save(Book book) {
            this.bookRepositor.save(book);
    }
}

