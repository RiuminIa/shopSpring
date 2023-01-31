package sk.stuba.fei.uim.oop.assignment3.author.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.material.Author;
import sk.stuba.fei.uim.oop.assignment3.author.material.IAuthorRepositor;
import sk.stuba.fei.uim.oop.assignment3.author.net.cover.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.book.material.Book;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundObjectException;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements IAuthorService {
    @Autowired
    private IAuthorRepositor authorRepositor;
    @Autowired
    private IBookService bookService;

    @Override
    public List<Author> getAll() {
        return authorRepositor.findAll();
    }

    @Override
    public Author create(AuthorRequest request) {
        Author a=new Author(request);
        return this.authorRepositor.save(a);
    }

    @Override
    public Author getById(Long id) throws NotFoundObjectException {
        Optional<Author> optional;
        optional=this.authorRepositor.findById(id);
        if(!optional.isPresent()){
            throw new NotFoundObjectException();
        }
        return optional.get();
    }

    @Override
    public Author changeById(Long id, AuthorRequest authorRequest) throws NotFoundObjectException {
        Author author=this.getById(id);
        if(authorRequest.getName()!=null){
            author.setName(authorRequest.getName());
        }
        if(authorRequest.getSurname()!=null){
            author.setSurname(authorRequest.getSurname());
        }
        return this.authorRepositor.save(author);
    }

    @Override
    public void deleteById(Long id) throws NotFoundObjectException{
        Author author=this.getById(id);
        this.bookService.deleteAuthorBooks(author);
        this.authorRepositor.delete(author);
    }


    @Override
    public void save(Author a) {
        this.authorRepositor.save(a);
    }

    @Override
    public void changeBook(Author from, Author to, Book book) {
        if(from!=null){
            this.authorRepositor.findById(from.getId()).get().getBooks().remove(book);
            this.save(from);
        }
        if(to!=null){
            this.authorRepositor.findById(to.getId()).get().getBooks().add(book);
            this.save(to);
        }
    }

    @Override
    public void deleteBook(Author author, Book book) throws NotFoundObjectException{
        this.getById(author.getId()).getBooks().remove(book);
        this.authorRepositor.save(author);
    }
}
