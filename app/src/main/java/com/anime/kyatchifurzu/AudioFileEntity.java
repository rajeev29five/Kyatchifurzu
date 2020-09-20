package com.anime.kyatchifurzu;

public class AudioFileEntity {

    private String name;
    private int resourceId;

    public AudioFileEntity() {
    }

    public AudioFileEntity(String name, int resourceId) {
        this.name = name;
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "AudioFileEntity{" +
                "name='" + name + '\'' +
                ", resourceId=" + resourceId +
                '}';
    }
}
