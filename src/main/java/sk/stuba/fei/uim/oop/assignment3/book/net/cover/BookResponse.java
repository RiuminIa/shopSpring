package sk.stuba.fei.uim.oop.assignment3.book.net.cover;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.book.material.Book;

@Getter
public class BookResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final Long author;
    private final int pages;
    private final int amount;
    private final int lendCount;

    public BookResponse(Book b) {
        this.id = b.getId();
        this.name = b.getName();
        this.description=b.getDescription();
        if(b.getAuthor()!=null){
            this.author=b.getAuthor().getId();
        }
        else{
            this.author= Long.valueOf(0);
        }
        this.pages=b.getPages();
        this.amount=b.getAmount();
        this.lendCount=b.getLendCount();
    }

}


