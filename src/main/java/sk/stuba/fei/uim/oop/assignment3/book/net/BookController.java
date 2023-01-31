package sk.stuba.fei.uim.oop.assignment3.book.net;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.book.net.cover.*;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundObjectException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService bookService;
    @GetMapping
    public List<BookResponse> getAll(){
        return bookService.getAll().stream().map(BookResponse::new).collect(Collectors.toList());
    }
    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody BookRequest bookRequest)throws NotFoundObjectException{
            return new ResponseEntity<>(new BookResponse(this.bookService.create(bookRequest)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public BookResponse getById(@PathVariable("id") Long id) throws NotFoundObjectException {
        return new BookResponse(this.bookService.getById(id));
    }
    @PutMapping("/{id}")
    public BookResponse changeById(@PathVariable("id") Long id, @RequestBody BookChangeRequest bookChangeRequest) throws NotFoundObjectException{
        return new BookResponse(this.bookService.changeById(id,bookChangeRequest));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) throws NotFoundObjectException {
        this.bookService.delete(id);
    }
    @GetMapping("/{id}/amount")
    public BookAmountResponse getAmount(@PathVariable("id")Long id) throws NotFoundObjectException{
        return new BookAmountResponse(this.bookService.getAmount(id));
    }

    @PostMapping("/{id}/amount")
    public BookAmountResponse setAmount(@PathVariable("id")Long id, @RequestBody BookAmountRequest bookAmountRequest) throws NotFoundObjectException{
        return new BookAmountResponse(this.bookService.setAmount(id,bookAmountRequest));
    }
    @GetMapping("/{id}/lendCount")
    public BookAmountResponse getLendCount(@PathVariable("id")Long id) throws NotFoundObjectException{
        return new BookAmountResponse(this.bookService.getLendCount(id));
    }

}
