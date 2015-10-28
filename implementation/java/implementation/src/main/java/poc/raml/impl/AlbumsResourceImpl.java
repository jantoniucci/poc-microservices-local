package poc.raml.impl;

import org.springframework.stereotype.Component;
import poc.raml.jaxrs.jukebox.model.Album;
import poc.raml.jaxrs.jukebox.model.Albums;
import poc.raml.jaxrs.jukebox.resource.AlbumsResource;

@Component
public class AlbumsResourceImpl implements AlbumsResource {
    @Override
    public GetAlbumsResponse getAlbums(String query, String orderBy, Order order, long offset, long limit) throws Exception {
        Albums albums = new Albums().withSize(1);
        albums.getAlbums().add(
                new Album().withAlbumName("test")
        );
        return GetAlbumsResponse.withJsonOK(albums);
    }

    @Override
    public PostAlbumsResponse postAlbums(String accessToken, Album entity) throws Exception {
        return null;
    }

    @Override
    public GetAlbumsByAlbumIdResponse getAlbumsByAlbumId(String albumId) throws Exception {
        return null;
    }

    @Override
    public GetAlbumsByAlbumIdSongsResponse getAlbumsByAlbumIdSongs(String albumId, String orderBy, Order order) throws Exception {
        return null;
    }
}
