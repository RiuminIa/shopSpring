package sk.stuba.fei.uim.oop.assignment3.bill.net.cover;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.book.net.cover.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.bill.material.Bill;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BillResponse {
    private final Long id;
    private List<BookResponse> lendingList;
    private final boolean lended;
    public BillResponse(Bill bill){
        this.id=bill.getId();
        if (bill.getLendingList() != null) {
            lendingList=new ArrayList<>();
            this.lendingList.addAll(bill.getLendingList().stream().map(BookResponse::new).collect(Collectors.toList()));
        }
        this.lended=bill.isLended();
    }
}
