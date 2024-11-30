package wzorce.cqrs.web.dto;

import java.util.UUID;

public record GetCustomerListResponse(UUID id, String email) {
}
