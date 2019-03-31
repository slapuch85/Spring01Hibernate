package pl.coderslab.app;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    EntityManager entityManager;

    public void saveBook(Book entity) {
        entityManager.persist(entity);
    }

    public Book findById(long id){
        return entityManager.find(Book.class, id);
    }

    public void updateTitle(Book entity) {
        entityManager.merge(entity);
    }

    public void updateAuthor(Book entity) {
        entityManager.merge(entity);
    }

    public void delete(long id) {
        entityManager.remove(entityManager.contains(findById(id)) ?
                findById(id) : entityManager.merge(findById(id)));
    }
}
