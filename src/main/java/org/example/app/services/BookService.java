package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final ProjectRepository<Book> bookRepo;

    @Autowired
    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks(){
        return bookRepo.retrieveAll();
    }

    public void saveBook(Book book){
        bookRepo.store(book);
    }

    public void removeItemsByRegex(String queryRegex){
        bookRepo.removeItemsByRegex(queryRegex);
    }

}
