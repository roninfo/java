package javaNine;

import javaNine.http.NewHttpConnection;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Books {
    public static List<Book> all() {
        return List.of(
                new Book("Explorando APIs e bibliotecas Java", "Rodrigo Turini", Category.PROGRAMMING),
                new Book("Desbravando Java","Rodrigo Turini", Category.PROGRAMMING),
                new Book("APIs Java","Rodrigo Turini", Category.PROGRAMMING),
                new Book("Certificação Java","Guilherme Silveira", Category.PROGRAMMING, Category.CERTIFICATION),
                new Book("TDD","Mauricio Aniche", Category.PROGRAMMING, Category.AGILE),
                new Book("SOLID","Mauricio Aniche", Category.PROGRAMMING),
                new Book("Guia da Startup","Joaquim Torres", Category.BUSINESS),
                new Book("Grokking algorithms", "Aditya Y. Bhargava", Category.PROGRAMMING),
                new Book("Spring Batch Pro", "Michel T Minella", Category.PROGRAMMING),
                new Book("Clean Code","Robert C. Martin", Category.PROGRAMMING),
                new Book("Working Effectively with Legacy code", "Robert C. Martin", Category.PROGRAMMING)
        );
    }

    public static List<Book> newAll() {
        Stream<String> booksCSV = Stream.of(NewHttpConnection.returnAsyncCSV().split("\n"));
        List<Book> collect =
                booksCSV.map(Books::create)
                        .collect(Collectors.toList());

        collect.addAll(all());

        return collect;
    }

    public static Book create(String line) {
        String[] split = line.split(",");
        String name = split[0];
        String author = split[2];
        Category category = Category.valueOf(split[3]);

        return new Book(name, author, category);
    }

    public static void listaLivrosPorAutorWithFilter() {
        Map<String, List<Book>> collect = all().stream()
                .filter(e -> e.getCategories().contains(Category.PROGRAMMING))
                .collect(Collectors.groupingBy(Book::getAuthor)
                );

        collect.entrySet().forEach(e -> System.out.println(e.getValue()));
    }

    public static void listaLivrosPorAutorWithCollectors() {
        Map<String, List<Book>> collect = all().stream()
                .collect(Collectors.groupingBy(Book::getAuthor,
                        Collectors.filtering(
                                e -> e.getCategories().contains(Category.PROGRAMMING),
                                Collectors.toList()
                        )
                ));

        collect.entrySet().forEach(e -> System.out.println(e.getValue()));
    }

    public static void categoriaPorAutor() {
        Map<String, Set<Category>> categoriasPorAutor = new HashMap<>();
        for (Book book : all()) {
            if (categoriasPorAutor.containsKey(book.getAuthor())) {
                categoriasPorAutor.get(book.getAuthor()).addAll(book.getCategories());
            } else {
                Set<Category> categorias = new HashSet<>();
                categorias.addAll(book.getCategories());
                categoriasPorAutor.put(book.getAuthor(), categorias);
            }
        }
        categoriasPorAutor.entrySet().forEach(b -> System.out.println(b));
    }

    // lista de lista
    public static void categoriaPorAutorNewBefore() {
        Map<String, Set<List<Category>>> collect = all().stream().collect(
                Collectors.groupingBy(Book::getAuthor,
                        Collectors.mapping(Book::getCategories, Collectors.toSet())
                )
        );
        System.out.println("Novo agrupamento:\n");
        collect.entrySet().forEach(b -> System.out.println(b));
    }

    //FlatMapping agrupando as listas em um set
    public static void categoriaPorAutorNewAfter() {
        Map<String, Set<Category>> collect = all().stream()
                .collect(
                        Collectors.groupingBy(Book::getAuthor,
                                Collectors.flatMapping(b -> b.getCategories().stream(),
                                        Collectors.toSet()
                                )
                        )
                );
        System.out.println("\nNovo agrupamento:\n");
        collect.entrySet().forEach(b -> System.out.println(b));
    }

    public static Optional<Book> findSimilar(Book book) {
        return all().stream()
                .filter(b -> b.getCategories().equals(book.getCategories()))
                .filter(b -> !b.getAuthor().equals(book.getAuthor()))
                .findAny();
    }
}
