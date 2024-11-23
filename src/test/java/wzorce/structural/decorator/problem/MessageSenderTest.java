package wzorce.structural.decorator.problem;

import org.junit.jupiter.api.Test;

class MessageSenderTest {

    @Test
    void sendEmail() {
        var baseMessageSender = new BaseMessageSender("kiepskiFerdzio");
        baseMessageSender.send("Wygrałeś milon!");
    }

    @Test
    void sendEmailAndSms() {
        var baseSmsMessageSender = new BaseSmsMessageSender("kiepskiFerdzio");
        baseSmsMessageSender.send("Wygrałeś milon!");
    }

    @Test
    void sendEmailAndWhatsApp() {
        var baseWhatsAppMessageSender = new BaseWhatsAppMessageSender("kiepskiFerdzio");
        baseWhatsAppMessageSender.send("Wygrałeś milon!");
    }

    @Test
    void sendEmailAndSmsAndWhatsApp() {
        var baseSmsWhatsAppMessageSender = new BaseSmsWhatsAppMessageSender("kiepskiFerdzio");
        baseSmsWhatsAppMessageSender.send("Wygrałeś milon!");
    }
}