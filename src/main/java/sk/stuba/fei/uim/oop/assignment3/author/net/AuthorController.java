package sk.stuba.fei.uim.oop.assignment3.author.net;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.author.logic.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.author.net.cover.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.net.cover.AuthorResponse;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundObjectException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private IAuthorService service;

    @GetMapping
    public List<AuthorResponse> getAll(){
        return service.getAll().stream().map(AuthorResponse::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> addAuthor (@RequestBody AuthorRequest request){
        return new ResponseEntity<>(new AuthorResponse(this.service.create(request)), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public AuthorResponse getById(@PathVariable("id") Long id) throws NotFoundObjectException {
        return new AuthorResponse(this.service.getById(id));
    }
    @PutMapping("/{id}")
    public AuthorResponse changeById(@PathVariable("id") Long id,@RequestBody AuthorRequest author) throws NotFoundObjectException{
       return new AuthorResponse(this.service.changeById(id,author));
    }
    @DeleteMapping("/{id}")
    public void delete (@PathVariable("id") Long id) throws NotFoundObjectException{
        this.service.deleteById(id);
    }
}
