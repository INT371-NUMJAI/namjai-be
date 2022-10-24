package int371.namjai.domain.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {
    private Long amount;
    private String paymentMethodType;
}
