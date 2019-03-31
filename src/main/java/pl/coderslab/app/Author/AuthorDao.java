package pl.coderslab.app.Author;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    EntityManager entityManager;

    public void saveAuthor(Author entity){
        entityManager.persist(entity);
    }

    public Author findById(long id){
        return entityManager.find(Author.class, id);
    }

    public void updateAuthor(Author entity){
        entityManager.merge(entity);
    }

    public void delete(long id){
        entityManager.remove(entityManager.contains(findById(id)) ?
                findById(id) : entityManager.merge(findById(id)));
    }


}
