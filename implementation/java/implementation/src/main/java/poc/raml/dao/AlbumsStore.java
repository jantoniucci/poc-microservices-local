package poc.raml.dao;

import poc.raml.jaxrs.jukebox.model.Album;
import poc.raml.jaxrs.jukebox.model.Song;

import java.util.List;

/**
 * Created by frascuchon on 5/11/15.
 */
public interface AlbumsStore {
    List<Album> getAll();

    Album findAlbumById(String albumId);

    void addAlbum(Album entity) throws Exception;

    List<Song> findAlbumSongsById(String albumId);
}
