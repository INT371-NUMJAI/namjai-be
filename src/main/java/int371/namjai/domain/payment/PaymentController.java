package int371.namjai.domain.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/view")
@CrossOrigin("*")
public class PaymentController {

    @Value("${stripe.apiKey}")
    private String stripe_key;


//    @GetMapping("/payment")
//    public String getKey(){
//        return "hello"+stripe_key;
//    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerData> createCustomer(@RequestBody CustomerData customerData) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        Stripe.apiKey = stripe_key;
        params.put("name", customerData.getName());
        params.put("email", customerData.getEmail());
        Customer customer = Customer.create(params);
        customerData.setCustomerId(customer.getId());
        return ResponseEntity.ok().body(customerData);

    }

    @PostMapping("/payment")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) throws StripeException {
        Stripe.apiKey = stripe_key;
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(payment.getAmount() * 100)
                        .setCurrency("thb")
                        .addPaymentMethodType(payment.getPaymentMethodType())
                        .build();

        PaymentIntent paymentIntentCreate = PaymentIntent.create(params);

        Map<String, Object> paramsCard = new HashMap<>();
        paramsCard.put("payment_method", "pm_card_visa");
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentCreate.getId());
        PaymentIntent paymentConfirm = paymentIntent.confirm(paramsCard);
        return ResponseEntity.ok(payment);
    }

}
