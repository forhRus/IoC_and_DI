package only_xml;

import only_xml.classes.Dog;
import only_xml.classes.Person;
import only_xml.classes.Pet;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex04 {
  public static void main(String[] args) {
    // Классический пример создания объектов
    System.out.println("Классический пример");
    Pet pet = new Dog();
    Person person = new Person(pet);
    person.callYourPet();

    System.out.println("-------------------");

    // Пример с использованием контекста
    System.out.println("Пример с бином");
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("only_XML/applicationContext_ex04.xml");
    Pet petBean = context.getBean("myPet", Pet.class);

    // В качестве аргумента передаём полученный из контекста бин
    Person person1 = new Person(petBean);
    person1.callYourPet();

    System.out.println("-------------------");

    // Пример DI
    System.out.println("Пример с внедрением зависимостей");

    // Создаём Person из бина, в который автоматически подставится
    // бин Pet из контейнера
    Person personBean = context.getBean("myPerson", Person.class);
    personBean.callYourPet();

    System.out.println("-------------------");

    context.close();
  }
}
