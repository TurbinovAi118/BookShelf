package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final Logger logger = Logger.getLogger(BookService.class);
    private final ProjectRepository<Book> bookRepo;

    @Autowired
    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks(){
        return bookRepo.retrieveAll();
    }

    public void saveBook(Book book){
        if (book.isNotBlank()) {
            logger.info("store new book " + book);
            bookRepo.store(book);
        }
        logger.info("invalid book data");
    }

    public void removeItemsByRegex(String queryRegex){
        String finalRegex = ("(.*)"+queryRegex+"(.*)");
        bookRepo.removeItemsByRegex(finalRegex);
    }

    public void removeBookById(Integer bookIdToRemove) {
        bookRepo.removeItemById(bookIdToRemove);
    }

}
