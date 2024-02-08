package only_xml;

import only_xml.classes.Pet;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex07 {
  public static void main(String[] args) {
    // Пример DI с подстановкой значений из файла
    String path = "only_XML/applicationContext_ex07.xml";
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext(path);

    Pet pet = context.getBean("myPet", Pet.class);
    pet.say();

    context.close();
  }
}
