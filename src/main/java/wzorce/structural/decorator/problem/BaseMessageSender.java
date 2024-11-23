package wzorce.structural.decorator.problem;

class BaseMessageSender {

    protected final String username;
    protected final UserRepository userRepository;

    public BaseMessageSender(String username) {
        this.username = username;
        userRepository = new UserRepository();
    }

    public void send(String msg) {
        String mail = userRepository.getMailFromUsername(username);
        System.out.println("Wysłano " + msg + " za pomocą email na adres " + mail);
    }
}
