package pl.camp.it.library.dao.impl.test;

import org.springframework.stereotype.Repository;
import pl.camp.it.library.dao.IAuthorDAO;
import pl.camp.it.library.model.Author;

import java.util.List;

@Repository
public class AuthorDAOStub implements IAuthorDAO {

    @Override
    public List<Author> findAuthors(String pattern) {
        return null;
    }
}
