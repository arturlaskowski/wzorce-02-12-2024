package wzorce.oop.vo.solution1;

class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long customerId) {
        super("Customer not found. ID: " + customerId);
    }
}
