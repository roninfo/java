package javaNine;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaNineRun {
    public static void main(String arg[]) {

//        Books.listaLivrosPorAutorWithFilter();
//        Books.listaLivrosPorAutorWithCollectors();
//        Books.categoriaPorAutor();
//        Books.categoriaPorAutorNewBefore();
//        Books.categoriaPorAutorNewAfter();
//
//        IntStream.range(0,6).forEach(i -> Books.findSimilar(Books.all().get(i)).ifPresentOrElse(System.out::println, () -> System.out.println("Nao existe similar")));

        List<Book> books = Books.newAll();
        System.out.println(books);
//        Set<Book> similars = books.stream()
//                .flatMap(b -> Books.findSimilar(b).stream())
//                .collect(Collectors.toSet());
//
//        similars.forEach(System.out::println);
    }
}
