package repo;

import entity.Artist;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ArtistRepository {

    public void create(Artist artist) {
        PersistenceUtil persistenceUtil = PersistenceUtil.getInstance();
        EntityManager entityManager = persistenceUtil.getEntityManagerFactory().createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(artist);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public static Artist findById(int artistId) {
        PersistenceUtil persistenceUtil = PersistenceUtil.getInstance();
        EntityManager entityManager = persistenceUtil.getEntityManagerFactory().createEntityManager();

        entityManager.getTransaction().begin();

        Artist artist = entityManager.find(Artist.class, artistId);

        entityManager.close();
        return artist;
    }

    public static List<Artist> findByName(String artistName) {
        PersistenceUtil persistenceUtil = PersistenceUtil.getInstance();
        EntityManager entityManager = persistenceUtil.getEntityManagerFactory().createEntityManager();

        entityManager.getTransaction().begin();

        List<Artist> queryResult = entityManager.createNamedQuery("Artist.findByName", Artist.class)
                .setParameter("name", artistName).getResultList();

        entityManager.close();
        return queryResult;
    }
}
