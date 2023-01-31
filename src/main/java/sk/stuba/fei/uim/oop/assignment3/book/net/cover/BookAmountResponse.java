package sk.stuba.fei.uim.oop.assignment3.book.net.cover;

import lombok.Getter;

@Getter
public class BookAmountResponse
{
    private final int amount;
    public BookAmountResponse(int count){
        this.amount=count;
    }
}
