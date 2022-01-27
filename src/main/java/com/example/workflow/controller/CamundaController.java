package com.example.workflow.controller;

import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cammunda")
public class CamundaController {

    @Autowired
    private RuntimeService runtimeService;

    private final HelloService helloService;

    public CamundaController(HelloService helloService) {
        this.helloService = helloService;
    }

    @PostMapping(value="/hello3")
    public String helloWorld(@RequestBody Weather weather) {
        return helloService.helloWorld3(weather);
    }

    @PostMapping("/hello4/{process-instance-id}")
    public ResponseEntity<String> cookedNoodles(
            @PathVariable(name = "process-instance-id") String processInstanceId
    ) {


        try {
            if (StringUtils.isEmpty(processInstanceId)) {
                return ResponseEntity.badRequest().body("Process Instance Id cannot be null or empty");
            }

            runtimeService
                    .createMessageCorrelation("SimpleDemo")
                    .processInstanceId(processInstanceId)
                    .correlate();

            return ResponseEntity.ok().body(processInstanceId + " is ready to eat.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown Exception. Message: " + e.getMessage());
        }

    }


}
