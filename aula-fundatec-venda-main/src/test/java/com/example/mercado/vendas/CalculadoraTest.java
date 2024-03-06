package com.example.mercado.vendas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculadoraTest {

    @Test
    void somar() {
        double a = 1;
        double b = 3;
        var resultadoSoma = Calculadora.somar(a, b);
        Assertions.assertEquals(4, resultadoSoma);
    }

    @Test
    void dividir() {
        double a = 4;
        double b = 2;
        var resultadoDiv = Calculadora.dividir(a, b);
        Assertions.assertEquals(2, resultadoDiv);
    }

    @Test
    void dividirComZero() {
        double a = 4;
        double b = 0;

        Assertions.assertThrows(ArithmeticException.class,
                () -> {
                   Calculadora.dividir(a, b);
                }
        );
    }

}
