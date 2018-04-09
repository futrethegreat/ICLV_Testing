package com.ctc;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author DavidSauce
 *
 *         This class is needed to launch cucumber tests from Gradle.
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/java/Features" }, glue = { "Steps" }, plugin = { "json:target/cucumber.json",
		"html:target/site/cucumber-pretty" })

public class Runner {

}
