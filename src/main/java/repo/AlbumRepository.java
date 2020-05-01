package repo;

import entity.Album;
import entity.Artist;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

// Implementarea efectiva a metodelor cerute, realizata conform exemplelor din curs

public class AlbumRepository {
    public void create(Album album) {
        PersistenceUtil persistenceUtil = PersistenceUtil.getInstance();
        EntityManager entityManager = persistenceUtil.getEntityManagerFactory().createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(album);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public Album findById(int albumId) {
        PersistenceUtil persistenceUtil = PersistenceUtil.getInstance();
        EntityManager entityManager = persistenceUtil.getEntityManagerFactory().createEntityManager();

        entityManager.getTransaction().begin();

        Album album = entityManager.find(Album.class, albumId);

        entityManager.close();
        return album;
    }

    public List<Album> findByName(String albumName) {
        PersistenceUtil persistenceUtil = PersistenceUtil.getInstance();
        EntityManager entityManager = persistenceUtil.getEntityManagerFactory().createEntityManager();

        entityManager.getTransaction().begin();

        List<Album> queryResult = entityManager.createNamedQuery("Album.findByName", Album.class)
                .setParameter("name", albumName).getResultList();

        entityManager.close();
        return queryResult;
    }

    public List<Album> findByArtist(Artist artist) {
        ArtistRepository artistRepository = new ArtistRepository();
        List<Artist> artistsFoundInDB = artistRepository.findByName(artist.getName());
        if (artistsFoundInDB.size() > 0) {
            PersistenceUtil persistenceUtil = PersistenceUtil.getInstance();
            EntityManager entityManager = persistenceUtil.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();

            List<Album> queryResult = entityManager.createNamedQuery("Album.findByArtist", Album.class)
                    .setParameter("artistId", artistsFoundInDB.get(0).getId()).getResultList();

            entityManager.close();
            return queryResult;
        } else {
            return null;
        }
    }
}
