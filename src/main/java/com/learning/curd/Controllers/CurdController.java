package com.learning.curd.Controllers;


import com.learning.curd.Entity.CurdEntity;
import com.learning.curd.ExceptionHandler.HandleUserException;
import com.learning.curd.Service.CurdService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class CurdController {

    @Autowired
    private CurdService curdService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsersData()
    {
         List<CurdEntity> all = curdService.getAllEntities();
         if(!all.isEmpty())
         {
             return new ResponseEntity<>(all, HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> AddDataIntoDb( @RequestBody CurdEntity myEntity)
    {
            System.out.println(myEntity);
            HandleUserException handleUserException = new HandleUserException();
            ResponseEntity<?> valid = handleUserException.isValid(myEntity);

            if(Objects.equals(valid.getBody(), "Valid"))
            {
                Optional<CurdEntity> exists = curdService.findUserByEmail(myEntity.getEmail());
                if(exists.isPresent())
                {
                    return new ResponseEntity<>("Email Already Exists",HttpStatus.BAD_REQUEST);
                }
                CurdEntity curdEntity = curdService.createEntity(myEntity);
                if(curdEntity!=null)
                {
                    return new ResponseEntity<>("Successfully Add", HttpStatus.CREATED);
                }
            }
        return new ResponseEntity<>(valid.getBody(),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> GetUserById(@PathVariable String id)
    {
        Optional<CurdEntity> OneUser =  curdService.getById(id);
        return new ResponseEntity<>(OneUser,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> DeleteUser(@PathVariable String id)
    {
        curdService.deleteById(id);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> UpdateUser(@PathVariable String id, @RequestBody CurdEntity myEntity)
    {
        CurdEntity old = curdService.getById(id).orElse(null);

        if(old!=null)
        {
            HandleUserException handleUserException = new HandleUserException();
            ResponseEntity<?> valid = handleUserException.isValidForUpdate(myEntity);
            if(Objects.equals(valid.getBody(), "Valid"))
            {
                old.setFullName(myEntity.getFullName()!=null && !myEntity.getFullName().isEmpty() ? myEntity.getFullName() : old.getFullName());
                old.setEmail(myEntity.getEmail()!=null && !myEntity.getEmail().isEmpty() ? myEntity.getEmail() : old.getEmail());
                old.setPassword(myEntity.getPassword()!=null && !myEntity.getPassword().isEmpty() ? myEntity.getPassword() : old.getPassword());
                CurdEntity curdEntity = curdService.createEntity(old);
                if(curdEntity!=null)
                {
                    return new ResponseEntity<>(curdEntity, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(valid.getBody(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
