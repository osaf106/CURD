package com.learning.curd.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurdEntity {

    private String id;

    @NonNull
    @NotBlank(message = "Name cannot be empty")
    private String fullName;


//    @Indexed(unique = true)
    @NonNull
    private String email;


    @NonNull
    private String password;
}
