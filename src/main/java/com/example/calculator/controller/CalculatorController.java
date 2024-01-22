package com.example.calculator.controller;

import com.example.calculator.model.CalculatorRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CalculatorController {

    @PostMapping("/calculate")
    public ResponseEntity<Map<String, String>> calculate(@RequestBody CalculatorRequest request) {
        try {
            double number1 = Double.parseDouble(request.getNumber_1());
            double number2 = Double.parseDouble(request.getNumber_2());
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
            return ResponseEntity.badRequest().body(createErrorResponse("Invalid number format"));
        }
    }

    private String formatResult(double result) {
        DecimalFormat decimalFormat = new DecimalFormat("#.######"); // adjust the format as needed
        return decimalFormat.format(result);
    }

    private Map<String, String> createErrorResponse(String errorMessage) {
        Map<String, String> response = new HashMap<>();
        response.put("error", errorMessage);
        return response;
    }
}
