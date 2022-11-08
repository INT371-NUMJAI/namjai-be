package int371.namjai.utill.exception;

import int371.namjai.domain.article.ArticleNotfoundException;
import int371.namjai.domain.bank_account.BankAccountNotfoundException;
import int371.namjai.domain.foundation.FoundationNotFoundException;
import int371.namjai.domain.foundation_project.exceptions.FoundationProjectsNotFoundException;
import int371.namjai.domain.report_issue.ReportIssueNotFoundException;
import int371.namjai.domain.user.exceptions.UserDuplicateException;
import int371.namjai.domain.user.exceptions.UserNotFoundException;
import int371.namjai.domain.volunteer_projects.exceoptions.VolunteerProjectException;
import int371.namjai.domain.withdrawal_request.WithdrawalRequestNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FoundationNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(FoundationNotFoundException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        response.setDateTime(LocalDateTime.now());
        response.setMessage("This foundation may not exist");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(FoundationProjectsNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(FoundationProjectsNotFoundException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        response.setDateTime(LocalDateTime.now());
        response.setMessage("This entity may not exist");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(ReportIssueNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(ReportIssueNotFoundException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        response.setDateTime(LocalDateTime.now());
        response.setMessage("This report  may not exist");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(ArticleNotfoundException.class)
    public ResponseEntity<Object> handleExceptions(ArticleNotfoundException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        response.setDateTime(LocalDateTime.now());
        response.setMessage("This report  may not exist");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(VolunteerProjectException.class)
    public ResponseEntity<Object> handleExceptions(VolunteerProjectException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        response.setDateTime(LocalDateTime.now());
        response.setMessage("This volunteer project  may not exist");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(UserNotFoundException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        response.setDateTime(LocalDateTime.now());
        response.setMessage("This user  may not exist");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(BankAccountNotfoundException.class)
    public ResponseEntity<Object> handleExceptions(BankAccountNotfoundException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        response.setDateTime(LocalDateTime.now());
        response.setMessage("This account  may not exist");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(WithdrawalRequestNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(WithdrawalRequestNotFoundException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        response.setDateTime(LocalDateTime.now());
        response.setMessage("This withdrawal request  may not exist");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(UserDuplicateException.class)
    public ResponseEntity<Object> handleExceptions(UserDuplicateException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setHttpStatus(HttpStatus.BAD_REQUEST);
        response.setDateTime(LocalDateTime.now());
        response.setMessage("This user has been already registered");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        return entity;
    }

        @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleExceptions(BadCredentialsException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setHttpStatus(HttpStatus.UNAUTHORIZED);
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Invalid email or password , Please try agin");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        return entity;
    }


}