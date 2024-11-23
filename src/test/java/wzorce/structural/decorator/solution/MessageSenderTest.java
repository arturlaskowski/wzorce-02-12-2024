package wzorce.structural.decorator.solution;

import org.junit.jupiter.api.Test;

class MessageSenderTest {

    @Test
    void sendEmail() {
        MessageSender messageSender = new BaseMessageSender("kiepskiFerdzio");
        messageSender.send("Wygrałeś milon!");
    }

    @Test
    void sendEmailAndSms() {
        MessageSender messageSender = new SmsSenderDecorator(new BaseMessageSender("kiepskiFerdzio"));
        messageSender.send("Wygrałeś milon!");
    }

    @Test
    void sendEmailAndWhatsApp() {
        MessageSender messageSender = new WhatsAppSenderDecorator(new BaseMessageSender("kiepskiFerdzio"));
        messageSender.send("Wygrałeś milon!");
    }

    @Test
    void sendEmailAndSmsAndWhatsApp() {
        MessageSender messageSender = new WhatsAppSenderDecorator(new SmsSenderDecorator(new BaseMessageSender("kiepskiFerdzio")));
        messageSender.send("Wygrałeś milon!");
    }
}