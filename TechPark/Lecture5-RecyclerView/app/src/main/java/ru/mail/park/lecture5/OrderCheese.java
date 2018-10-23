package ru.mail.park.lecture5;

public class OrderCheese extends OrderItem {
    private String title;
    private int imageResId;

    public OrderCheese(int price,String title, int imgResID) {
        super(price,OrderType.CHEESE);
        this.title = title;
        imageResId= imgResID;

    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }
}
