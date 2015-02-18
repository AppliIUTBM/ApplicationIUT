package com.iutbm.applicationiut.Interface;


import com.iutbm.applicationiut.Mail;

import java.util.ArrayList;

import javax.mail.Message;

public interface MailsReceiverInterface {

    public void displayMails(ArrayList<Mail> mails, String user);

}
