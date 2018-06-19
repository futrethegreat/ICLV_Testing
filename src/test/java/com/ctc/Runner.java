package com.ctc;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * This class is needed to launch cucumber tests from Gradle.
 *
 * @author David Sauce
 *
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/java/Features" }, glue = { "Steps" }, plugin = { "json:target/cucumber.json",
		"html:target/site/cucumber-pretty" })

// features: Path to features files
// glue: Path to steps implementation
// plugin: Path to reports

public class Runner {

}
