public class Main {
  public static void main(String[] args) {
    LibraryModel library = new LibraryModel();
    View view = new View(library);
    view.run();
  }
}
