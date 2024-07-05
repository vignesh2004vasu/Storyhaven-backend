package com.VIGNESH.logindatabase.repository;

import com.VIGNESH.logindatabase.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book,String> { // Use String for ID

    List<Book> findByAuthor(String author);

    List<Book> findByGenre(String genre);

    List<Book> findByPriceLessThan(String price);
}
