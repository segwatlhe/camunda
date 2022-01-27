package com.example.workflow.controller;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HelloServiceBean implements HelloService {

    public static final String PROCESS_DEFINITION_KEY = "SimpleDemo";

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ManagementService managementService;

//    @Autowired
//    private ProcessEngine processEngine1;

    @Override
    public String helloWorld3(Weather weather) {

        Map<String, Object> variables = new HashMap<>();
        variables.put("name", weather.getName());
        variables.put("weatherOk", weather.getWeatherOk());

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        runtimeService = processEngine.getRuntimeService();
        // query table SELECT * FROM ACT_RU_JOBDEF
        //             SELECT * FROM ACT_HI_JOB_LOG 
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY,"SimpleDemo", variables);

        return instance.getId();
    }
}
