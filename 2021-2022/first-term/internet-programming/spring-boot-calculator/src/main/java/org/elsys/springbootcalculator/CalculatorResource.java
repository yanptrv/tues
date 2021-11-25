package org.elsys.springbootcalculator;

import org.springframework.web.bind.annotation.*;
@RestController
public class CalculatorResource {

    operations.OperationFactory operationFactory = new operations.OperationFactory();

    @GetMapping("/calculate/{operation}/{arguments}")
    public double calculate(@PathVariable String operation, @PathVariable String arguments) {
         return operationFactory.getOperation(operation + " " + String.join(" ", arguments.split(","))).perform();
    }

    @GetMapping("/calculate")
    public double calculateQuery(@RequestParam String oper, @RequestParam String args) {
        return operationFactory.getOperation(oper + " " + String.join(" ", args.split(","))).perform();
    }

    @PostMapping("/calculate")
    public double calculate(@RequestBody Operation operation) {
        return operationFactory.getOperation(operation.operation() + " " + String.join(" ", operation.arguments().toString().split(","))).perform();
    }
}
