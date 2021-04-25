package pl.gondzio.songApp.logic;


import org.springframework.stereotype.Service;
import pl.gondzio.songApp.domain.dto.SongInsert;
import pl.gondzio.songApp.domain.model.Song;
import pl.gondzio.songApp.domain.model.SongGroup;
import pl.gondzio.songApp.repository.SongGroupRepository;
import pl.gondzio.songApp.repository.SongRepository;

import java.util.Optional;
import java.util.Set;

import static java.lang.String.format;

@Service
public class SongGroupService {

    private final SongRepository songRepository;
    private final SongGroupRepository songGroupRepository;

    public SongGroupService(SongGroupRepository songGroupRepository, SongRepository songRepository){
        this.songGroupRepository = songGroupRepository;
        this.songRepository = songRepository;
    }

    public Optional<SongGroup> getMainSongGroup() {
        return songGroupRepository.findOneByOwnerIsNull();
    }

    public SongGroup addSongToMainGroup(SongInsert song) throws Exception {

        Optional<SongGroup> songGroup = songGroupRepository.findOneByOwnerIsNull();

        if(songGroup.isPresent()){

            SongGroup group = songGroup.get();

            Song dbSong = songRepository.save(song.toSong());

            Set<Song> songs = group.getSongs();
            songs.add(dbSong);
            group.setSongs(songs);

            songGroupRepository.save(group);

            return group;
        }

        throw new Exception("Main song group not found");

    }
}