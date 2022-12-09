package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("----------Bootstraping Data----------");

        Author a1 = new Author("Vishnu", "Vardhan");
        Book b1 = new Book("Coding is fun", "123456");

        Author a2 = new Author("Shivendu", "Saurabh");
        Book b2 = new Book("CA is fun", "654321");

        Publisher p1 = new Publisher("Comaecod", "Antop Hill", "Mumbai", "Maharashtra", "400037");

        /* @ERROR: object references an unsaved transient instance - save the transient instance before flushing */
        publisherRepository.save(p1);

        a1.getBooks().add(b1);
        b1.getAuthors().add(a1);
        b1.setPublisher(p1);

        a2.getBooks().add(b2);
        b2.getAuthors().add(a2);
        b2.setPublisher(p1);

        p1.getBooks().add(b1);
        p1.getBooks().add(b2);

        authorRepository.save(a1);
        bookRepository.save(b1);

        authorRepository.save(a2);
        bookRepository.save(b2);

        publisherRepository.save(p1);

        System.out.println("Author Repo: " + authorRepository.count());
        System.out.println("Book Repo: " + bookRepository.count());
        System.out.println("Publisher Repo: " + publisherRepository.count());
    }
}
