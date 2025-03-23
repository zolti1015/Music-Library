/* This code is for the song tracking section of the spec
*  will not be on seperate file in final just pasting it here for now
*  as "example code"
*/

import java.io.*;
import java.util.*;

class SongTracker {
    private final Map<String, Integer> playCount;
    private final LinkedList<String> recentPlays;
    private static final int MAX_TRACKED = 10;
    private static final String SAVE_FILE = "";
    
    public SongTracker() {
        this.playCount = new HashMap<>();
        this.recentPlays = new LinkedList<>();
        // need to load from file
    }
    
    public void playSong(String song) {
        System.out.println("Now playing: " + song);
        playCount.put(song, playCount.getOrDefault(song, 0) + 1);
        recentPlays.remove(song);
        recentPlays.addFirst(song);
        if (recentPlays.size() > MAX_TRACKED) {
            recentPlays.removeLast();
        }
        saveToFile();
    }
    
    public List<String> getMostRecentlyPlayed() {
        return new ArrayList<>(recentPlays);
    }

    public List<String> getMostFrequentlyPlayed() {
        return playCount.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(MAX_TRACKED)
                .map(Map.Entry::getKey)
                .toList();
