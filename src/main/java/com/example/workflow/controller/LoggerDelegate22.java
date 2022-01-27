package com.example.workflow.controller;


import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;
import java.util.logging.Logger;

/**
 * This is an easy adapter implementation
 * illustrating how a Java Delegate can be used
 * from within a BPMN 2.0 Service Task.
 */
public class LoggerDelegate22 implements JavaDelegate {

    private final Logger LOGGER = Logger.getLogger(LoggerDelegate22.class.getName());

    public void execute(DelegateExecution delegateExecution) throws Exception {


        Map<String, Object> variables = delegateExecution.getVariables();

        LOGGER.info("\n\n  ... LoggerDelegate2 invoked by "
                + "activityName2='" + delegateExecution.getCurrentActivityName() + "'"
                + ", activityId2=" + delegateExecution.getCurrentActivityId()
                + ", processDefinitionId2=" + delegateExecution.getProcessDefinitionId()
                + ", processInstanceId2=" + delegateExecution.getProcessInstanceId()
                + ", businessKey2=" + delegateExecution.getProcessBusinessKey()
                + ", executionId2=" + delegateExecution.getId()
                + ", variables2=" + delegateExecution.getVariables()
                + " \n\n");


     //   variables.put("print_error", "LoggerDelegate2");

        delegateExecution.setVariables(variables);
        throw new BpmnError("print_error");

    }

}

