package com.azuiev.interfaces;

/**
 * Created by Lera on 21.09.2015.
 */
public interface Subject {

    void register(Observer o);
    void remove(Observer o);
    void notifyAllObservers(String message);

}
