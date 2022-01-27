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
@Service("LoggerDelegate3")
public class LoggerDelegate3 implements JavaDelegate {

    private final Logger LOGGER = Logger.getLogger(LoggerDelegate3.class.getName());

    public void execute(DelegateExecution delegateExecution) throws Exception {

        System.out.println("Send Letter");

        Map<String, Object> variables = delegateExecution.getVariables();

        LOGGER.info("\n\n  ... LoggerDelegate3 invoked by "
                + "activityName3='" + delegateExecution.getCurrentActivityName() + "'"
                + ", activityId3=" + delegateExecution.getCurrentActivityId()
                + ", processDefinitionId3=" + delegateExecution.getProcessDefinitionId()
                + ", processInstanceId3=" + delegateExecution.getProcessInstanceId()
                + ", businessKey3=" + delegateExecution.getProcessBusinessKey()
                + ", executionId3=" + delegateExecution.getId()
                + ", variables3=" + delegateExecution.getVariables()
                + " \n\n");


        delegateExecution.setVariables(variables);

    }

}

