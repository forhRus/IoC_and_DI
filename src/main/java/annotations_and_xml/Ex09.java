package annotations_and_xml;

import annotations_and_xml.classes.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex09 {
  public static void main(String[] args) {
    String path = "08";
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext(
                    String.format("annotations_and_xml/applicationContext_ex%s.xml", path));
    Person person = context.getBean("personBean", Person.class);
    person.callYourPet();
    System.out.println(person);

    context.close();
  }
}
