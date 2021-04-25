package pl.gondzio.songApp.domain.dto;

import lombok.Data;
import pl.gondzio.songApp.domain.model.Song;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.URL;

@Data
public class SongInsert {

    @NotEmpty(message = "Song name can not be empty")
    private String name;

    @NotNull
    @NotEmpty(message = "Song url can not be empty")
    private URL url;

    public Song toSong() {
        Song song = new Song();
        song.setName(name);
        song.setUrl(url);
        return song;
    }
}