package games;

public class App {
  public String getGreeting() {
    return "Some games in Java/jSwing";
  }

  public static void main(String[] args) {
    System.out.println(new App().getGreeting());
    new Game_Selection();
  }
}