package com.gojava6.differenttasks.patternobserver.posttask;

import com.gojava6.differenttasks.patternobserver.Observer;

/**
 * @Autor Andrey Chaykin
 * @Since 26.09.2015
 */
public class MailBox2 extends MailBox {

    public MailBox2(PostOffice post) {
        super(post);
    }

    public void printMessage() {
        System.out.println("MailBox2: ");
        super.printMessage();
    }
}
