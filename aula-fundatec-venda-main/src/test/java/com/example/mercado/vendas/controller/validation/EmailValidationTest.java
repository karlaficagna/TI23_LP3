package com.example.mercado.vendas.controller.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidationTest {

    @Test
    void validateInvalidEmail() {
        String email = "teste@.com";
        String email2 = "@gmail.com";
        String email3 = "teste@outlook.br.1.2.3.3;@";
        String email4 = "teste@outlook;br";

        EmailValidation emailValidation = new EmailValidation();
        //caso1
        boolean result = emailValidation.validate(email);
        Assertions.assertFalse(result);
        //caso2
        result = emailValidation.validate(email2);
        Assertions.assertFalse(result);
        //caso3
        result = emailValidation.validate(email3);
        Assertions.assertFalse(result);
        //caso4
        result = emailValidation.validate(email4);
        Assertions.assertFalse(result);
    }

    @Test
    void validateValidEmail() {
        String email = "teste@teste.com";
        String email2 = "teste@gmail.com";
        String email3 = "teste@outlook.br";

        EmailValidation emailValidation = new EmailValidation();
        //caso1
        boolean result = emailValidation.validate(email);
        Assertions.assertTrue(result);
        //caso2
        result = emailValidation.validate(email2);
        Assertions.assertTrue(result);
        //caso3
        result = emailValidation.validate(email3);
        Assertions.assertTrue(result);
    }
}