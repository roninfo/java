package bookstore;

import bookstore.data.Books;
import bookstore.model.Book;
import bookstore.service.NFEmissor;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Main {
    public static void main(String arg[]) {

        List<Book> books = Books.newAll();
        IntStream.range(0, books.size())
                .forEach(i -> System.out.println(i + " - " + books.get(i).getBook()));

        System.out.println("\nInforme o código do livro:");

        Scanner scanner = new Scanner(System.in);
        int indice = scanner.nextInt();

        Book book = null;
        if (Optional.of(books.get(indice)).isPresent()) {
            book = books.get(indice);
        } else {
            System.out.println("Código não encontrado!");
            System.exit(1);
        }

        System.out.println("Livro escolhido: " + book.getBook());

        System.out.println("Informe seu nome para a Nota Fiscal: ");

        scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        NFEmissor nfEmissor = new NFEmissor();
        nfEmissor.emit(name, book);

        System.out.println("Obrigado!");
        Books.findSimilar(book)
                .ifPresentOrElse(showSimilar, noSuggestions);

        System.out.println("Aperte o enter para sair");
        new Scanner(System.in).nextLine();
        nfEmissor.close();
    }
    private static Consumer<Book> showSimilar = similar -> {
        System.out.println(
                "\nTalvez você também goste do livro: "
                        + similar.getBook());
    };
    private static Runnable noSuggestions = () -> {
        System.out.println(
                "\nNão temos nenhuma sugestão de livro similar no momento")
        ;
    };
}
