package poc.raml.dao.impl;

import jersey.repackaged.com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import poc.raml.dao.AlbumsStore;
import poc.raml.jaxrs.jukebox.model.Album;
import poc.raml.jaxrs.jukebox.model.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by frascuchon on 5/11/15.
 */
@Service
public class AlbumsStoreImpl implements AlbumsStore {

    private ConcurrentMap<String, Album> albums = Maps.newConcurrentMap();



    @Override
    public List<Album> getAll() {
        return Collections.unmodifiableList(new ArrayList<Album>(albums.values()));
    }

    @Override
    public Album findAlbumById(String albumId) {
        return (albums.containsKey(albumId)) ? albums.get(albumId) : null;
    }

    @Override
    public void addAlbum(Album entity) throws Exception {
        if (entity == null || StringUtils.isEmpty(entity.getAlbumId())) {
            throw new IllegalArgumentException();
        } else {
            albums.putIfAbsent(entity.getAlbumId(), entity);
        }
    }

    @Override
    public List<Song> findAlbumSongsById(String albumId) {
        return null;
    }
}
