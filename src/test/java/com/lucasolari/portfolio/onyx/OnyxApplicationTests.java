package com.lucasolari.portfolio.onyx;

import com.lucasolari.portfolio.onyx.dto.CartFormDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.Set;

//@Size(min = 1, max = 1, message = "The field must be exactly 1 character long")

//@NotEmpty
//Checks whether the annotated element is not null nor empty

/*@NotBlank
Checks that the annotated character sequence is not null and the trimmed length is greater than 0.
The difference to @NotEmpty is that this constraint can only be applied on character sequences
and that trailing white-spaces are ignored*/

//@NotNull
//Checks that the annotated value is not null

//Hibernate counts characters just like the length method from
//String. If a character is outside the bmp it is counted as "two" characters.
//Remember that String in java are always utf16 internally


@SpringBootTest
class OnyxApplicationTests {


	@Test
	void contextLoads() {


	}

}
