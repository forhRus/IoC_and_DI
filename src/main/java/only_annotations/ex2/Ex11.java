package only_annotations.ex2;

import only_annotations.ex2.classes.Person;
import only_annotations.ex2.classes.MyConfig2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex11 {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(MyConfig2.class);
    Person person = context.getBean("personBean", Person.class);

    System.out.println(person);
    person.callYourPet();

    context.close();
  }
}
