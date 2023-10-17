package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();


    @Override
    public List<Book> retrieveAll() {
        return repo;
    }

    @Override
    public void store(Book book) {
        book.setId(book.hashCode());
        repo.add(book);
    }

    @Override
    public void removeItemById(Integer bookIdToRemove) {
        for (Book book : retrieveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                repo.remove(book);
            }
        }
    }

    @Override
    public void removeItemsByRegex(String queryRegex) {
        Pattern pattern = Pattern.compile(queryRegex);
        repo.removeIf(book -> {
            Matcher authorMatcher = pattern.matcher(book.getAuthor());
            Matcher titleMatcher = pattern.matcher(book.getTitle());
            Matcher sizeMatcher = pattern.matcher(book.getSize().toString());
            return authorMatcher.matches() || titleMatcher.matches() || sizeMatcher.matches();
        });
    }

}