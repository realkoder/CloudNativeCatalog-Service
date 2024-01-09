package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.book.Book;
import com.polarbookshop.catalogservice.domain.repository.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testData")
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();
        var book1 = Book.of("1234567890", "Northern Lights", "Lyra Silverstar", 9.90, "PolarPub");
        var book2 = Book.of("1234567891", "The Plague", "ALbert Camus", 10.90, "PolarPub");
        bookRepository.saveAll(List.of(book1, book2));
    }
}
