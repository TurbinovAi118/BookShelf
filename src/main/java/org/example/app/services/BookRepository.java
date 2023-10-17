package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
        if (!book.getAuthor().isBlank() || !book.getTitle().isBlank() || book.getSize() != null) {
            book.setId(book.hashCode());
            logger.info("store new book " + book);
            repo.add(book);
        }
        logger.info("invalid book data");
    }

    @Override
    public void removeItemsByRegex(String queryRegex) {
        String finalRegex = /*queryRegex.isBlank() ? "" : */("(.*)"+queryRegex+"(.*)");
        repo.removeIf(book -> {
            if(/*!queryRegex.isBlank() && */(book.getAuthor().matches(finalRegex) ||
                    book.getTitle().matches(finalRegex) ||
                    book.getSize().toString().matches(finalRegex))) {
                logger.info("remove book completed " + book);
                return true;
            }
            return false;
        });
    }

}
/*


        String finalRegex = ("(.*)"+queryRegex+"(.*)");
        repo.removeIf(book -> {
            if(finalRegex.matches(book.getAuthor()) ||
                    finalRegex.matches(book.getTitle()) ||
                    finalRegex.matches(book.getSize().toString())){
                logger.info("remove book completed " + book);
                return true;
            }
            return false;
        });
 */