package only_annotations.ex2.classes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource("myApp.properties")
public class MyConfig2 {
  @Bean
  @Scope("prototype")
  public Pet catBean(){
    return  new Cat();
  }
  @Bean
  @Scope("prototype")
  public Person personBean(){
    return  new Person(catBean());
  }
}
