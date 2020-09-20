package com.anime.kyatchifurzu;

public enum AudioDetails {

    YOOOOO("Yooooo:UNKNOWN"),
    YOOOOO_FULL("Yooooo Full:UNKNOWN"),
    YOYOI("Yoyoi Kumadori:One Piece");

    private String audioDetails;

    AudioDetails(String audioDetails) {
        this.audioDetails = audioDetails;
    }

    public String getAudioDetails() {
        return audioDetails;
    }

}
