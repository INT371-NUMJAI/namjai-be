package int371.namjai.domain.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import int371.namjai.domain.foundation_project.FoundationProject;
import int371.namjai.domain.foundation_project.FoundationProjectRepository;
import int371.namjai.domain.foundation_project.exceptions.FoundationProjectsNotFoundException;
import int371.namjai.domain.payment.dto.CustomerData;
import int371.namjai.domain.payment.dto.Payment;
import int371.namjai.domain.transaction.Transaction;
import int371.namjai.domain.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/view")
@CrossOrigin("*")
public class PaymentController {

    @Value("${stripe.apiKey}")
    private String stripe_key;

    @Autowired
    private FoundationProjectRepository foundationProjectRepo;

    @Autowired
    private TransactionRepository transactionRepo;

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
                        .addPaymentMethodType("card")
                        .build();

        PaymentIntent paymentIntentCreate = PaymentIntent.create(params);

        FoundationProject foundationProject = foundationProjectRepo.findById(payment.getFdnProjectUUID()).orElseThrow(FoundationProjectsNotFoundException::new);
        double amountNow = foundationProject.getReceived();
        foundationProject.setReceived(amountNow += payment.getAmount());
        Transaction transaction = new Transaction();
        transaction.setTransactionUUID(UUID.randomUUID().toString());
        transaction.setPaymentID(paymentIntentCreate.getId());
        transaction.setAmount(payment.getAmount());
        transaction.setCreateDate(new Timestamp(System.currentTimeMillis()));
        transaction.setFoundationProject(foundationProject);
        transactionRepo.save(transaction);


        Map<String, Object> paramsCard = new HashMap<>();
        paramsCard.put("payment_method", "pm_card_visa");
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentCreate.getId());
        paymentIntent.confirm(paramsCard);
        return ResponseEntity.ok(payment);
    }

}
