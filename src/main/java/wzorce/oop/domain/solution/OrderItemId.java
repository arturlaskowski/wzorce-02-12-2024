package wzorce.oop.domain.solution;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
class OrderItemId implements Serializable {

    private Integer id;
    private Order order;
}
