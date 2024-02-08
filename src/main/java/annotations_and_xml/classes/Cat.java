package annotations_and_xml.classes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("catBean")
@Scope("prototype")
public class Cat implements Pet {
  @Override
  public void say(){
    System.out.println("Cat: Meow-meow");
  }

  @Override
  public void init() {
    System.out.println("Кошка создана");
  }

  @Override
  public void destroy() {
    System.out.println("Кошка уничтожена");
  }
}
