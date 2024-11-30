package wzorce.cqrs.trackorder;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import wzorce.cqrs.order.web.ErrorResponse;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
class TrackOrderRestEndpoint {

    private final TrackingOrderQueryRepository trackingOrderQueryRepository;

    @GetMapping("orders/{id}/track")
    public TrackingOrderProjection trackOrder(@PathVariable UUID id) {
        return trackingOrderQueryRepository.findById(id)
                .orElseThrow(() -> new TrackingOrderNotFoundException(id));
    }

    @ExceptionHandler(value = TrackingOrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(TrackingOrderNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
