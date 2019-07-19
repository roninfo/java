package bookstore.model;


import java.util.List;

public class Book {

    private final String book;
    private final String author;
    private final List<Category> categories;

    public Book(String book, String author, Category ...categories) {
        this.book = book;
        this.author = author;
        this.categories = List.of(categories);
    }

    public String getBook() {
        return book;
    }

    public String getAuthor() {
        return author;
    }

    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return  "[book='" + book + '\'' +
                ", author='" + author + '\'' +
                ", categories=" + categories +"]\n";
    }
}
