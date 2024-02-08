package only_annotations.ex1;

import only_annotations.ex1.classes.Cat;
import only_annotations.ex1.classes.MyConfig1;
import only_annotations.ex1.classes.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex10 {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(MyConfig1.class);
    Cat cat = context.getBean("catBean", Cat.class);
    cat.say();

    Person person = context.getBean("personBean", Person.class);
    System.out.println(person);
    person.callYourPet();

    context.close();
  }
}
