package only_annotations.ex1.classes;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration // класс является конфигурацией
@ComponentScan("only_annotations/ex1/classes") // пакет с компонентами
@PropertySource("myApp.properties")
public class MyConfig1 {
}
