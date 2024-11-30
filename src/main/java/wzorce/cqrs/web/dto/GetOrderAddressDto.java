package wzorce.cqrs.web.dto;

public record GetOrderAddressDto(
        String street,
        String postalCode,
        String city,
        String houseNo) {
}
