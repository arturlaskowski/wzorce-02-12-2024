package wzorce.structural.decorator.problem;

class BaseSmsMessageSender extends BaseMessageSender {

    public BaseSmsMessageSender(String username) {
        super(username);
    }

    public void send(String msg) {
        super.send(msg);
        String userPhone = userRepository.getPhoneNrFromUsername(username);
        System.out.println("Wysłano " + msg + " za pomocą sms na numer " + userPhone);
    }

}
