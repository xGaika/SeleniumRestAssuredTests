import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/UI/specifications/", glue = {"UI/implementations"},
        plugin = {"pretty"},
        tags = "@TestTaskTag")
public class TestTaskTR {

}
