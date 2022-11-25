package int371.namjai.domain.backoffice;

import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.foundation.FoundationRepository;
import int371.namjai.domain.foundation.FoundationService;
import int371.namjai.domain.foundation.mapper.APIVerificationFDN;
import int371.namjai.domain.foundation_rejected.FoundationRejected;
import int371.namjai.domain.foundation_rejected.FoundationRejectedRepository;
import int371.namjai.domain.user.User;
import int371.namjai.domain.user.UserRepository;
import int371.namjai.utill.Constant;
import int371.namjai.utill.ResourceUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

@Service
public class BackOfficeService {
    @Autowired
    private FoundationService foundationService;

    @Autowired
    private ResourceUtilService resourceUtilService;

    @Autowired
    private FoundationRepository foundationRepository;

    @Autowired
    private FoundationRejectedRepository foundationRejectedRepository;

    @Autowired
    private UserRepository userRepository;

    public void approveFoundation(APIVerificationFDN apiVerificationFDN) throws MessagingException {
        Foundation foundation = foundationService.getFoundationById(apiVerificationFDN.getFdnUUid());
        String newStatus = ("APPROVE".equals(apiVerificationFDN.getStatus())) ? Constant.FDN_STATUS_VERIFIED : Constant.FDN_STATUS_REJECTED;
        if (Constant.FDN_STATUS_VERIFIED.equals(newStatus)) {
            User newUser = userRepository.findByEmailIgnoreCaseAndStatusDisable(foundation.getEmail());
            newUser.setStatus(Constant.USER_STATUS_ACTIVE);
            foundation.setStatus(newStatus);
            foundation.setApproval(apiVerificationFDN.getAdminApprove());
            foundationRepository.save(foundation);
            userRepository.save(newUser);
            resourceUtilService.createDirForVerifiedFoundation(foundation.getNameEn());
            sendmailForVerification(apiVerificationFDN.getFdnUUid(), foundation.getEmail(), newStatus, apiVerificationFDN.getMessage());
        } else {
            foundation.setStatus(apiVerificationFDN.getStatus());
            foundation.setApproval(apiVerificationFDN.getAdminApprove());
            foundationRepository.save(foundation);
            sendmailForVerification(apiVerificationFDN.getFdnUUid(), foundation.getEmail(), newStatus, apiVerificationFDN.getMessage());
        }
    }


    private void sendmailForVerification(String fdnUUid, String fdnEmail, String status, String message) throws javax.mail.MessagingException {
        Message msg = new MimeMessage(setCredential());
        msg.setFrom(new InternetAddress("namjai.service@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(fdnEmail));
        msg.setSubject("Namjai - Verification Result");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Your foundation account have been verified", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        if (Constant.FDN_STATUS_VERIFIED.equals(status)) {
            messageBodyPart.setContent("Your foundation account have been verified , Please Log in to Namjai to start your project", "text/html");
        } else {
            messageBodyPart.setContent("Your foundation account have been rejected  due to " + message + ". Please contact admin for further information (namjai.service@gmail.com)", "text/html");
            FoundationRejected foundationRejected = new FoundationRejected(UUID.randomUUID().toString(), fdnUUid, message, new Timestamp(System.currentTimeMillis()));
            foundationRejectedRepository.save(foundationRejected);
        }
        msg.setContent(multipart);
        Transport.send(msg);
    }

    public void sendmailForApprovalWithdrawal(String withdrawalUUID, String fdnEmail, String status, String message) throws javax.mail.MessagingException {
        Message msg = new MimeMessage(setCredential());
        msg.setFrom(new InternetAddress("namjai.service@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(fdnEmail));
//        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(fdnEmail));
        msg.setSubject("Namjai - Withdrawal Request Result #" + withdrawalUUID);
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Your withdrawal request have been verified", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        if (Constant.PROJECT_APPROVED.equals(status)) {
            messageBodyPart.setContent("Your  withdrawal request have been verified , Please check your bank account and balance", "text/html");
        } else {
            messageBodyPart.setContent("Your withdrawal request have been rejected  due to " + message + ". Please contact admin for further information (namjai.service@gmail.com)", "text/html");
        }
        msg.setContent(multipart);
        Transport.send(msg);
    }


    private Session setCredential() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("namjai.service@gmail.com", "lbpghkkcpjhuwtfw");
            }
        });
        return session;
    }


}
