package com.example.anton.liberies_practice;

public class ListenerHandler<T> {
    T listener;

    public T getListener() {
        return listener;
    }

    public void setListener(T listener) {
        this.listener = listener;
    }
    public void unregister(){
        listener=null;
    }

    public ListenerHandler(T listener){
        this.listener=listener;
    }
}
