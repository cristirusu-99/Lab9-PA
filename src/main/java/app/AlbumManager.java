package app;

import entity.Album;
import entity.Artist;
import repo.AlbumRepository;
import repo.ArtistRepository;

public class AlbumManager {
    private static Artist createArtist(String name, String country) {
        Artist newArtist = new Artist();
        newArtist.setName(name);
        newArtist.setCountry(country);
        return newArtist;
    }

    private static Album createAlbum(String name, int artistId, int releaseYear) {
        Album newAlbum = new Album();
        newAlbum.setName(name);
        newAlbum.setArtistId(artistId);
        newAlbum.setReleaseYear(releaseYear);
        return newAlbum;
    }

    private static void firstRunDbInitialization() {
        try {

            AlbumRepository albumRepository = new AlbumRepository();
            ArtistRepository artistRepository = new ArtistRepository();

            artistRepository.create(createArtist("Indila", "France"));
            artistRepository.create(createArtist("Inna", "Romania"));
            artistRepository.create(createArtist("Nicolae Guta", "Romania"));
            artistRepository.create(createArtist("Becky G", "US"));
            artistRepository.create(createArtist("Shawn Mendes", "Canada"));


            albumRepository.create(createAlbum("Mini World",ArtistRepository.findByName("Indila").get(0).getId(), 2014));

            albumRepository.create(createAlbum("Party Never Ends", ArtistRepository.findByName("Inna").get(0).getId(), 2013));
            albumRepository.create(createAlbum("Inna", ArtistRepository.findByName("Inna").get(0).getId(), 2015));
            albumRepository.create(createAlbum("Nirvana", ArtistRepository.findByName("Inna").get(0).getId(), 2017));
            albumRepository.create(createAlbum("Yo", ArtistRepository.findByName("Inna").get(0).getId(), 2019));

            albumRepository.create(createAlbum("Regele manelelor", ArtistRepository.findByName("Nicolae Guta").get(0).getId(), 2004));
            albumRepository.create(createAlbum("Bun venit în Romania", ArtistRepository.findByName("Nicolae Guta").get(0).getId(), 2004));
            albumRepository.create(createAlbum("Viața merge înainte", ArtistRepository.findByName("Nicolae Guta").get(0).getId(), 2005));

            albumRepository.create(createAlbum("Mala Santa", ArtistRepository.findByName("Becky G").get(0).getId(), 2019));

            albumRepository.create(createAlbum("Handwritten", ArtistRepository.findByName("Shawn Mendes").get(0).getId(), 2015));
            albumRepository.create(createAlbum("Illuminate", ArtistRepository.findByName("Shawn Mendes").get(0).getId(), 2016));
            albumRepository.create(createAlbum("Shawn Mendes", ArtistRepository.findByName("Shawn Mendes").get(0).getId(), 2018));

        } catch (Exception ignored) {}
    }


    public static void main(String[] args) {
        AlbumRepository albumRepository = new AlbumRepository();
        ArtistRepository artistRepository = new ArtistRepository();
        //firstRunDbInitialization();
        var queryResults = albumRepository.findByArtist(createArtist("Indila", "Romania"));
        System.out.println("Result for " + ArtistRepository.findById(queryResults.get(0).getArtistId()).getName() + ":\n" + queryResults);
        queryResults = albumRepository.findByArtist(createArtist("Inna", "US"));
        System.out.println("Result for " + ArtistRepository.findById(queryResults.get(0).getArtistId()).getName() + ":\n" + queryResults);
    }
}
