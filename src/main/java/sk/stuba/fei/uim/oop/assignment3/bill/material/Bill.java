package sk.stuba.fei.uim.oop.assignment3.bill.material;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.book.material.Book;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Book> lendingList;
    private boolean lended;

    public Bill() {
        this.lended=false;
        this.lendingList=new ArrayList<>();
    }
}
