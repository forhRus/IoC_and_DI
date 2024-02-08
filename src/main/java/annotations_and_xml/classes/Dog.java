package annotations_and_xml.classes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("dogBean")
@Scope("prototype")
public class Dog implements Pet {
  @Override
  public void say(){
    System.out.println("Dog: Bow-wow");
  }

  @Override
  public void init() {
    System.out.println("Собака создана");
  }

  @Override
  public void destroy() {
    System.out.println("Собака уничтожена");
  }
}
