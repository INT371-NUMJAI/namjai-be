package int371.namjai.domain.backoffice;

import int371.namjai.utill.Constant;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

@Service
public class BackOfficeService {

    public void sendmail(String fdnEmail, String status, String messge) throws javax.mail.MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("namjai.service@gmail.com", "abc@-456.qq");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("namjai.service@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("khonjai.3994@gmail.com"));
//        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(fdnEmail));
        msg.setSubject("Namjai - Verification Result");
        msg.setContent("Tutorials point email", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Your foundation account have been verified", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        if (Constant.FDN_STATUS_VERIFIED.equals(status)) {
            messageBodyPart.setContent("Your foundation account have been verified , Please Log in to Namjai ", "text/html");
        } else {
            messageBodyPart.setContent("Your foundation account have been rejected  due to " + messge + ". Please try again", "text/html");
        }

        msg.setContent(multipart);
        Transport.send(msg);
    }
}
