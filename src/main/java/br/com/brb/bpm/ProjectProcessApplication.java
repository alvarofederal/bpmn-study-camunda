package br.com.brb.bpm;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.repository.DeploymentBuilder;

/**
 * Process Application exposing this application's resources the process engine.
 */
@ProcessApplication
public class ProjectProcessApplication extends ServletProcessApplication {


}
