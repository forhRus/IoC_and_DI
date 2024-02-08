package only_xml;

import only_xml.classes.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex06 {
  public static void main(String[] args) {
    // Пример DI с подстановкой значений из файла
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("only_XML/applicationContext_ex06.xml");

    Person person2 = context.getBean("myPerson", Person.class);
    System.out.println(person2);

    context.close();
  }
}
