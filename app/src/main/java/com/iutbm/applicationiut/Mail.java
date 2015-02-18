package com.iutbm.applicationiut;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;


public class Mail implements Serializable {

    private String subject;
    private String sender;
    private String date;

    public Mail(Message message) {

        int style = DateFormat.MEDIUM;
        DateFormat df = DateFormat.getDateInstance(style, Locale.FRANCE);

        try {
            this.subject = message.getSubject();
            this.sender = message.getFrom()[0].toString();
            this.date = df.format(message.getReceivedDate());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String getSubject() {
        return subject;
    }

    public String getSender() {
        return sender;
    }

    public String getDate() {
        return date;
    }
}
