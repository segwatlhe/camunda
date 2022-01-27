package com.example.workflow.controller;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class HelloDelegate implements JavaDelegate {

    private final Logger LOGGER = Logger.getLogger(HelloDelegate.class.getName());

    @Autowired
    private HelloService helloService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        LOGGER.info("\n\n  ... HelloDelegate invoked by "
                + "activityName1='" + delegateExecution.getCurrentActivityName() + "'"
                + ", activityId1=" + delegateExecution.getCurrentActivityId()
                + ", processDefinitionId1=" + delegateExecution.getProcessDefinitionId()
                + ", processInstanceId1=" + delegateExecution.getProcessInstanceId()
                + ", businessKey1=" + delegateExecution.getProcessBusinessKey()
                + ", executionId1=" + delegateExecution.getId()
                + ", variables1=" + delegateExecution.getVariables()
                + " \n\n");

        Map<String, Object> variables = delegateExecution.getVariables();

        variables.put("name", true);
        variables.put("sunny", "sunny");

        if (variables.get("weatherOk").equals(true)) {
            variables.put("weatherOk", true);
        } else {
            variables.put("weatherOk", false);
        }
        delegateExecution.setVariables(variables);

    }

}
