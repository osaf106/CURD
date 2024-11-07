package com.learning.curd.ExceptionHandler;

import com.learning.curd.Entity.CurdEntity;
import com.learning.curd.Service.CurdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.regex.Pattern;
public class HandleUserException {


    @Autowired
    private CurdService curdService;

    String regexp = "^[A-Za-z\\s]+$";
    public ResponseEntity<?> isValid(CurdEntity curdEntity)
    {
        String name = curdEntity.getFullName();
        String email = curdEntity.getEmail();
        String password = curdEntity.getPassword();

        if(name.isEmpty() || email.isEmpty() || password.isEmpty())
        {
            return new ResponseEntity<>("Each Field must Requires", HttpStatus.BAD_REQUEST);
        }
        if(!isValidName(name))
        {
            return new ResponseEntity<>("Name must contain Alphabets", HttpStatus.BAD_REQUEST);
        }
        if(name.length()<3)
        {
            return new ResponseEntity<>("Name must contain 3 letters", HttpStatus.BAD_REQUEST);
        }
        if(!isValidEmail(email))
        {
            return new ResponseEntity<>("email Pattern is incorrect", HttpStatus.BAD_REQUEST);
        }
        if(password.length()<8)
        {
            return new ResponseEntity<>("Password must contain 8 letters", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Valid", HttpStatus.OK);
    }

    public ResponseEntity<?> isValidForUpdate(CurdEntity curdEntity)
    {
        String name = curdEntity.getFullName();
        String email = curdEntity.getEmail();
        String password = curdEntity.getPassword();

//        if(name.isEmpty() || email.isEmpty() || password.isEmpty())
//        {
//            return new ResponseEntity<>("Each Field must Requires", HttpStatus.BAD_REQUEST);
//        }
        if (!name.isEmpty())
        {
            if(!isValidName(name))
            {
                return new ResponseEntity<>("Name must contain Alphabets", HttpStatus.BAD_REQUEST);
            }
            if(name.length()<3)
            {
                return new ResponseEntity<>("Name must contain 3 letters", HttpStatus.BAD_REQUEST);
            }
        }

        if(!email.isEmpty())
        {
            if(!isValidEmail(email))
            {
                return new ResponseEntity<>("email Pattern is incorrect", HttpStatus.BAD_REQUEST);
            }
        }
        if(!password.isEmpty())
        {
            if(password.length()<8)
            {
                return new ResponseEntity<>("Password must contain 8 letters", HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("Valid", HttpStatus.OK);
    }

    private boolean isValidName(String name) {
        return Pattern.compile(regexp).matcher(name).matches();
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

}
