package wzorce.oop.domain.problem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class OrderItemId implements Serializable {

    private Integer id;
    private Order order;
}
