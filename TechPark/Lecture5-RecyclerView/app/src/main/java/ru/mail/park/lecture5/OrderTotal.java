package ru.mail.park.lecture5;

public class OrderTotal extends OrderItem {
    public OrderTotal(int price) {
        super(price, OrderType.TOTAL);
    }
}
