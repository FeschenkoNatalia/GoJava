package airbnb;

/**
 * Created by slavik on 21.09.2015.
 */
public interface Observable {
    void add(User user);
    void remove(String surname);
    void notifyAll(String data);
}