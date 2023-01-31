package sk.stuba.fei.uim.oop.assignment3.book.net.cover;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private String name;
    private String description;
    private Long author;
    private int pages;
    private int amount;
    private int lendCount;
}
