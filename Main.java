import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    LibraryModel library = new LibraryModel();
    View view = new View(library);
    view.run();
  }
}
