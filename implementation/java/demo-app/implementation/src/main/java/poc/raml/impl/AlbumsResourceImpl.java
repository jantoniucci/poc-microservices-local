package poc.raml.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import poc.raml.dao.AlbumsStore;
import poc.raml.jaxrs.jukebox.model.*;
import poc.raml.jaxrs.jukebox.resource.AlbumsResource;

import java.util.Arrays;
import java.util.List;

@Component
public class AlbumsResourceImpl implements AlbumsResource {

    private final AlbumsStore albumsStore;

    @Autowired
    public AlbumsResourceImpl(AlbumsStore albumsStore) {
        this.albumsStore = albumsStore;
    }

    @Override
    public GetAlbumsResponse getAlbums(String query, String orderBy, Order order, long offset, long limit) throws Exception {
        List<Album> allAlbums = albumsStore.getAll();
        return GetAlbumsResponse.withJsonOK(new Albums().withAlbums(allAlbums));
    }


    @Override
    public PostAlbumsResponse postAlbums(String accessToken, Album entity) throws Exception {
        albumsStore.addAlbum(entity);
        return PostAlbumsResponse.withJsonOK(new MessageResponse().withMessage("The album has been properly entered"));

    }

    @Override
    public GetAlbumsByAlbumIdResponse getAlbumsByAlbumId(String albumId) throws Exception {
        Album album = albumsStore.findAlbumById(albumId);
        if (album != null) {
            return GetAlbumsByAlbumIdResponse.withJsonOK(album);
        } else {
            return GetAlbumsByAlbumIdResponse.withJsonNotFound(new ErrorMessage().withMessage("Album not found"));
        }

    }

    @Override
    public GetAlbumsByAlbumIdSongsResponse getAlbumsByAlbumIdSongs(String albumId, String orderBy, Order order) throws Exception {
        List<Song> songs = albumsStore.findAlbumSongsById(albumId);
        if (CollectionUtils.isEmpty(songs)) {
            return GetAlbumsByAlbumIdSongsResponse
                    .withJsonNotFound(new ResponseMessage().withMessage("Songs not found"));
        } else {
            return GetAlbumsByAlbumIdSongsResponse.withJsonOK(new Songs().withSongs(Arrays.<Song>asList(new Song(), new Song(), new Song(), new Song())));
        }

    }
}
