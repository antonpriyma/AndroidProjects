package ru.mail.park.lecture5;

public class OrderItem {

    public enum OrderType {TOTAL, CHEESE}

    int price;
    private OrderType type;

    public OrderItem(int price,OrderType type) {
         this.type=type;
         this.price=price;

    }



    public OrderType getType() {
        return type;
    }

    public String getPrice(){return Integer.toString(price);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem item = (OrderItem) o;

        return price!= 0 ? type.equals(item.type) : item.type == null;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
