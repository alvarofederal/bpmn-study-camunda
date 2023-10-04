package br.com.brb.bpm.listener;

import java.util.Set;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.identity.User;

public class RecuperaTaskIdListener implements TaskListener {
    
    private Expression variavelInjection;
    
    
    @Override
    public void notify(DelegateTask delegateTask) {
        
        String taskName = delegateTask.getName();
        String taskID = delegateTask.getId();
        delegateTask.getExecution().setVariable("taskId", taskID);
        
        
        
        //Pegar o ID usuário logado atual para ser o único destino do email, caso esteja em DSV
        String loggedUser = "";
        String userDatas = "";
        try {
            delegateTask.getProcessEngine().getIdentityService().getCurrentAuthentication().getUserId(); 
            
            //O objeto IdentityService serve para obter o objeto User através do ID
            IdentityService idService = delegateTask.getExecution().getProcessEngine().getIdentityService();
            
            //Ober o objeto User do usuário com o ID obtido 
            User mockUser =  idService.createUserQuery().userId(loggedUser).orderByUserId().asc().list().get(0);
            
            userDatas = mockUser.getId() + " - " + mockUser.getEmail() + " - " + mockUser.getFirstName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        String taskDefKey = delegateTask.getTaskDefinitionKey();
        
        //DefinitionID do Processo
        String processDefId = delegateTask.getProcessDefinitionId(); //
        
        //Obtenção do usuario 'responsavel' pela tarefa, primeiro testando se existe um asignee e depois verificando se existe algum grupo relacionado
        String responsibleId = "";         
        responsibleId = delegateTask.getAssignee();
        
        
        System.out.println("**-------Informações:------" );
        System.out.println("**Tarefa Atual: " + taskName);
        System.out.println("**User:" + userDatas);
        System.out.println("**ID da Tarefa Atual: " + taskID);
        System.out.println("**Definition Key: "  + taskDefKey);
        System.out.println("**Usuário Logado Atual: " + loggedUser);
        System.out.println("**Responsável pela tarefa: " + responsibleId );
        System.out.println("**Process definition id: " + processDefId);
        System.out.println("**-------------------------" );
        
        try {
            
            System.out.println("## variavel injetada: " + this.variavelInjection.getValue(delegateTask).toString() );
            Set<String> variableNames = delegateTask.getVariableNames();
            for (String variableName : variableNames) {
                System.out.println("### nome variavel: " + variableName);
            }
            
            Set<String> variableNamesLocal = delegateTask.getVariableNamesLocal();
            for (String variableNameLocal : variableNamesLocal) {
                System.out.println("### nome variavel local: " + variableNameLocal);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("---------------------------" );
    }
    
}
