package wzorce.oop.vo.solution1;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customerServiceVo1")
@RequiredArgsConstructor
class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public void addPoints(Long customerId, LoyaltyPoints pointsToAdd) {
        var customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        customer.addPoints(pointsToAdd);
    }

    @Transactional
    public void subtractPoints(Long customerId, Integer pointsToSubtract) {
        var customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        customer.subtractPoints(new LoyaltyPoints(pointsToSubtract));
    }
}
