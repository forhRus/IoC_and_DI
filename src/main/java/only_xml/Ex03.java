package only_xml;

import only_xml.classes.Dog;
import only_xml.classes.Pet;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex03 {
  public static void main(String[] args) {
    // создаём контейнер для бинов
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("only_XML/applicationContext_ex03.xml");
    // прописываем путь к файлу конфигураций (находится в resources)

    // Получаем бин по идентификатору, указанному файлу.
    Pet pet = context.getBean("myPet", Pet.class);
    pet.say();
    // Можем получить класс, из которого создавался бин
    Dog dog = context.getBean("myPet", Dog.class);
    dog.say();

    // закрываем контекст
    context.close();
  }
}
