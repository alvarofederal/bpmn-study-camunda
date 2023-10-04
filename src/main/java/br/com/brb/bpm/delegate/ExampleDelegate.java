package br.com.brb.bpm.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.json.SpinJsonNode;

import static org.camunda.spin.Spin.*;

public class ExampleDelegate implements JavaDelegate {
    
    public void execute(DelegateExecution execution) throws Exception {
        
        SpinJsonNode json = JSON(execution.getVariable("ccgConfigJson"));
        
        SpinJsonNode enviarDemanda = json.prop("enviarDemanda");
        
        SpinJsonNode areasDemanda = enviarDemanda.prop("areasDaDemanda");
        
        execution.setVariable("areasDemanda", areasDemanda);
        
        /*
         * String someJsonString = "{\"shared\":[]}";
         * org.camunda.spin.plugin.variable.value.JsonValue sharedJson =
         * org.camunda.spin.plugin.variable.SpinValues.jsonValue(someJsonString).serializationDataFormat(
         * "application/json").create();
         * execution.setVariable("shared", sharedJson);
         */
    }
    
}
