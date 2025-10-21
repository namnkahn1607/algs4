package module6;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Objects;

public record Song(Album album, String name, int duration) implements Comparable<Song> {
    private static final int SECONDS_IN_HOUR = 3600;
    private static final int SECONDS_IN_MINUTE = 60;

    private static final Comparator<Song> BY_ALBUM = new ByAlbum();
    private static final Comparator<Song> BY_NAME = new ByName();
    private static final Comparator<Song> BY_DURATION = new ByDuration();

    private static class ByName implements Comparator<Song> {
        @Override
        public int compare(Song o1, Song o2) {
            return o1.name().compareTo(o2.name());
        }
    }

    private static class ByDuration implements Comparator<Song> {
        @Override
        public int compare(Song o1, Song o2) {
            return Integer.compare(o1.duration(), o2.duration());
        }
    }

    private static class ByAlbum implements Comparator<Song> {
        @Override
        public int compare(Song o1, Song o2) {
            return o1.album().getName().compareTo(o2.album().getName());
        }
    }

    public String formatDuration() {
        int seconds = duration();

        int hours = seconds / SECONDS_IN_HOUR;
        seconds -= hours * SECONDS_IN_HOUR;
        int minutes = seconds / SECONDS_IN_MINUTE;
        seconds -= minutes * SECONDS_IN_MINUTE;

        return String.format("%s:%s:%s",
                hours == 0 ? "" : String.valueOf(hours),
                minutes == 0 ? "00" : String.valueOf(minutes),
                seconds == 0 ? "00" : String.valueOf(seconds)
        );
    }

    @Override
    public int compareTo(@NotNull Song other) {
        int compareName = name.compareTo(other.name);

        if (compareName == 0) {
            int compareDuration = Integer.compare(duration, other.duration);

            if (compareDuration == 0) {
                return album.getName().compareTo(other.album().getName());
            }

            return compareDuration;
        }

        return compareName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, duration, album.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Song other) {
            return compareTo(other) == 0;
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format(
                "Song[name=%s, duration=%s, album=%s]",
                name(), formatDuration(), album().getName()
        );
    }
}