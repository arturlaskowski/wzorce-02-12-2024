package wzorce.cqrs.order.web.dto;

import java.util.UUID;

public record GetCustomerResponse(UUID id, String firstName, String lastName, String email) {
}
