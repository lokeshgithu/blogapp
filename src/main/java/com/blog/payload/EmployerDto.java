package com.blog.payload;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployerDto {
    private long id;

    @NotEmpty
    @Size(min = 3 ,max =100)
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10 ,max =10)
    private String mobile;
}
