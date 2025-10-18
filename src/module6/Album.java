package module6;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Album {
    private final String name;
    private final List<String> artist;
    private final List<Song> songList = new ArrayList<>();

    public Album(String name, List<String> artist) {
        this.name = name;
        this.artist = artist;
    }

    public void addSong(Song song) {
        if (song == null) {
            return;
        }

        songList.add(song);
    }

    public void removeSong(Song song) {
        songList.removeIf(element -> element.equals(song));
    }

    public int numberOfSongs() {
        return songList.size();
    }

    public List<String> getArtist() {
        return artist;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getArtist(), numberOfSongs());
    }
}