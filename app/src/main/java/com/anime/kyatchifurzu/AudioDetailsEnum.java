package com.anime.kyatchifurzu;

public enum AudioDetailsEnum {

    DATTEBAYO("Dattebayo Naruto:Naruto"),
    NARUTOKUN("Naruto-kun Hinata:Naruto"),
    NIUHUHU("Niu-hu-hu Koro-sensei:Assassination classroom"),
    TUTURU("Tuturu:Steins Gate"),
    YOOOOO("Yooooo:UNKNOWN"),
    YOOOOO_FULL("Yooooo Full:UNKNOWN"),
    YOYOI("Yoyoi Kumadori:One Piece");

    private String audioDetails;

    AudioDetailsEnum(String audioDetails) {
        this.audioDetails = audioDetails;
    }

    public String getAudioDetails() {
        return audioDetails;
    }

}
