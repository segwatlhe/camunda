package com.example.workflow.controller;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

/**
 * This is an easy adapter implementation
 * illustrating how a Java Delegate can be used
 * from within a BPMN 2.0 Service Task.
 */
@Service
public class ErrorHandlingDelegate implements JavaDelegate {

    private final Logger LOGGER = Logger.getLogger(ErrorHandlingDelegate.class.getName());

    public void execute(DelegateExecution delegateExecution) throws Exception {

        LOGGER.info("in error");

        Map<String, Object> variables = delegateExecution.getVariables();

        String error = variables.get("error").toString();
        LOGGER.info("errorHandling " + error)
        ;
        LOGGER.info("\n\n  ... ErrorHandlingDelegate invoked by "
                + "activityName='" + delegateExecution.getCurrentActivityName() + "'"
                + ", activityId=" + delegateExecution.getCurrentActivityId()
                + ", processDefinitionId=" + delegateExecution.getProcessDefinitionId()
                + ", processInstanceId=" + delegateExecution.getProcessInstanceId()
                + ", businessKey=" + delegateExecution.getProcessBusinessKey()
                + ", executionId=" + delegateExecution.getId()
                + ", variables=" + delegateExecution.getVariables()
                + " \n\n");

        delegateExecution.setVariables(variables);

    }

    // https://stackoverflow.com/questions/22932730/how-to-query-scheduled-processes-timer-events
}

