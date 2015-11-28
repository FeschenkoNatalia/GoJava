package com.gojava6.differenttasks.pattern.observer.posttask;

/**
 * @Autor Andrey Chaykin
 * @Since 26.09.2015
 */
public class MailBox3 extends MailBox {

    public MailBox3(PostOffice post) {
        super(post);
    }

    public void printMessage() {
        System.out.println("MailBox3: ");
        super.printMessage();
    }
}
