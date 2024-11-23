package wzorce.structural.decorator.problem;

class BaseSmsWhatsAppMessageSender extends BaseMessageSender {

    public BaseSmsWhatsAppMessageSender(String username) {
        super(username);
    }

    @Override
    public void send(String msg) {
        super.send(msg);

        //duplikacja logiki
        String userPhone = userRepository.getPhoneNrFromUsername(username);
        System.out.println("Wysłano " + msg + " za pomocą sms na numer " + userPhone);

        //duplikacja logiki
        String whatsAppId = userRepository.getWhatsAppFromUsername(username);
        System.out.println("Wysłano " + msg + " za pomocą WhatsApp na id " + whatsAppId);
    }
}
