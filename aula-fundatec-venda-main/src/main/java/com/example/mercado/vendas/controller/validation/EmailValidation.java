package com.example.mercado.vendas.controller.validation;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.REGEX;

public class EmailValidation {
    String regex = "/^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$/gm";

    public boolean validate(String email) {
        return email.matches(String.valueOf(REGEX));

    }
}

