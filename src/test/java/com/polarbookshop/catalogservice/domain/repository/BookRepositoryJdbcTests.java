package com.polarbookshop.catalogservice.domain.repository;

import com.polarbookshop.catalogservice.config.DataConfig;
import com.polarbookshop.catalogservice.domain.book.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest //This trigger spring boot to include all Spring Data JDBC entities and repos
@Import(DataConfig.class) //This is to enable auditing

//Disables default behavior so testcontainers can be used
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration") //Enable the application file from test resources application-integration.yml
class BookRepositoryJdbcTests {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate; //Lower level object to interact with db

    @Test
    void findBookByIsbnWhenExisting() {
        var bookIsbn = "1234561237";
        var book = Book.of(bookIsbn, "Title", "Author", 12.90);
        jdbcAggregateTemplate.insert(book);
        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo(book.isbn());
    }
}