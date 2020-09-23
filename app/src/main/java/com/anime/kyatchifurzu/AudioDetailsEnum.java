package com.anime.kyatchifurzu;

public enum AudioDetailsEnum {

    DATTEBAYO("Dattebayo:Naruto"),
    NARUTOKUN("Naruto-kun:Naruto"),
    NICONICONII("Nico Nico Nii:Love Live! School Idol Project"),
    NIUHUHU("Niu-hu-hu:Assassination classroom"),
    TUTURU("Tuturu:Steins Gate"),
    YOOOOO("Yooooo:UNKNOWN"),
    YOOOOO_FULL("Yooooo Full:UNKNOWN"),
    YOYOI("Yoyoi:One Piece");

    private String audioDetails;

    AudioDetailsEnum(String audioDetails) {
        this.audioDetails = audioDetails;
    }

    public String getAudioDetails() {
        return audioDetails;
    }

}
