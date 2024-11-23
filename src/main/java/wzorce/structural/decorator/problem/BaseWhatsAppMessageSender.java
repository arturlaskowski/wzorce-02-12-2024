package wzorce.structural.decorator.problem;

class BaseWhatsAppMessageSender extends BaseMessageSender {

    public BaseWhatsAppMessageSender(String username) {
        super(username);
    }

    @Override
    public void send(String msg) {
        super.send(msg);
        String whatsAppId = userRepository.getWhatsAppFromUsername(username);
        System.out.println("Wysłano " + msg + " za pomocą WhatsApp na id " + whatsAppId);
    }
}
