package com.example.calculator.controller;

import com.example.calculator.model.CalculatorRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
public class CalculatorController {

    @PostMapping("/calculate")
    public ResponseEntity<Map<String, String>> calculate(@RequestBody CalculatorRequest request) {
        try {
            if (request.getNumber_1() == null || request.getNumber_2() == null || request.getOperation() == null) {
                return ResponseEntity.badRequest().body(createErrorResponse("Missing required fields. Are you trying to confuse my calculator? >.<"));
            }

            double number1 = parseNumber(request.getNumber_1());
            double number2 = parseNumber(request.getNumber_2());
            String operation = request.getOperation();

            double result;

            switch (operation) {
                case "+":
                    result = number1 + number2;
                    break;
                case "-":
                    result = number1 - number2;
                    break;
                case "*":
                    result = number1 * number2;
                    break;
                case "/":
                    if (number2 != 0) {
                        result = number1 / number2;
                    } else {
                        return ResponseEntity.badRequest().body(createErrorResponse("Division by zero"));
                    }
                    break;
                default:
                    return ResponseEntity.badRequest().body(createErrorResponse("Invalid operation"));
            }

            Map<String, String> response = new HashMap<>();
            response.put("result", formatResult(result));
            return ResponseEntity.ok(response);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(createErrorResponse("Invalid number format. Are you trying to break my calculator with such a long number? О.о"));
        }
    }

    private double parseNumber(String number) {
        // Заменяем запятую на точку, если есть
        String formattedNumber = number.replace(",", ".");

        // Проверка на допустимость значения перед его парсингом
        if (!formattedNumber.matches("-?\\d+(\\.\\d+)?")) {
            throw new NumberFormatException();
        }

        return Double.parseDouble(formattedNumber);
    }

    private String formatResult(double result) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat format = new DecimalFormat("#.############", symbols);
        return format.format(result);
    }

    private Map<String, String> createErrorResponse(String errorMessage) {
        Map<String, String> response = new HashMap<>();
        response.put("error", errorMessage);
        return response;
    }
}
