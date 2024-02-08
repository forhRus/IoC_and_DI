package only_xml.classes;

public class Cat implements Pet {
  @Override
  public void say() {
    System.out.println("Meow-meow");
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
