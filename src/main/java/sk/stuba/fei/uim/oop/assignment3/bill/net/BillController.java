package sk.stuba.fei.uim.oop.assignment3.bill.net;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.bill.net.cover.BookIdRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundObjectException;
import sk.stuba.fei.uim.oop.assignment3.bill.logic.IBillService;
import sk.stuba.fei.uim.oop.assignment3.bill.net.cover.BillResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/list")
public class BillController {

    @Autowired
    private IBillService service;

    @GetMapping
    public List<BillResponse> getAll(){
        return this.service.getAll().stream().map(BillResponse::new).collect(Collectors.toList());
    }
    @PostMapping
    public ResponseEntity<BillResponse> create (){
        return new ResponseEntity<>(new BillResponse(this.service.create()), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public BillResponse getById(@PathVariable("id") Long id) throws NotFoundObjectException {
        return new BillResponse(this.service.getById(id));
    }
    @DeleteMapping("/{id}")
    public void delete (@PathVariable("id") Long id) throws NotFoundObjectException{
        this.service.deleteById(id);
    }
    @PostMapping("/{id}/add")
    public BillResponse addBook (@PathVariable("id") Long billId, @RequestBody BookIdRequest bookIdRequest) throws NotFoundObjectException, BadRequestException {
        return new BillResponse(this.service.addBook(billId,bookIdRequest));
    }
    @DeleteMapping("/{id}/remove")
    public void deleteBook (@PathVariable("id") Long billId,@RequestBody BookIdRequest bookIdRequest) throws NotFoundObjectException {
        this.service.deleteBook(billId,bookIdRequest);
    }
    @GetMapping("/{id}/lend")
    public void rentBooks(@PathVariable("id") Long billId) throws NotFoundObjectException, BadRequestException{
        this.service.rentBooks(billId);
    }
}
