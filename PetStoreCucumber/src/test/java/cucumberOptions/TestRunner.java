package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",
        glue = "stepdefinitions", tags = "@AddPet or @UpdatePet or @GetPet" ,
        plugin = {"json:target/cucumber.json"})
public class TestRunner {

}
//"@AddPet or @UpdatePet or @GetPet"