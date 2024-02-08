package only_annotations.ex2.classes;

public class Cat implements Pet {
  @Override
  public void say(){
    System.out.println("Cat: Meow-meow");
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
