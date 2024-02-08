package annotations_and_xml;

import annotations_and_xml.classes.Cat;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex08 {
  public static void main(String[] args) {
    String path = "08";
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext(
                    String.format("annotations_and_xml/applicationContext_ex%s.xml", path));
    Cat cat = context.getBean("catBean", Cat.class);
    cat.say();
  }
}
