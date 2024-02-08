package only_xml;

import only_xml.classes.Dog;
import only_xml.classes.Person;
import only_xml.classes.Pet;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex05 {
  public static void main(String[] args) {

    // Классический пример
    Pet pet = new Dog();
    Person person1 = new Person();
    person1.setPet(pet);
    person1.setSurname("Shkaev");
    person1.setAge(35);
    System.out.println(person1);

    System.out.println("---------");

    // Пример DI
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("only_XML/applicationContext_ex05.xml");

    Person person2 = context.getBean("myPerson", Person.class);
    System.out.println(person2);

    context.close();
  }
}
