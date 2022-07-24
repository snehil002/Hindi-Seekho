package com.example.hindiseekho;

/**
 * get audio using :: getAudioResourceId() ::
 * First check if image is present :: hasImage() ::
 * then get image using :: getImageResourceId() ::
 */

public class Word {
    private String defaultTranslation;
    private String hindiTranslation;
    private final int NO_IMAGE_RESOURCE = -1;
    private int imageResourceId = NO_IMAGE_RESOURCE;
    private int audioResourceId;

    /**
    ONLY for PHRASES ACTIVITY.
     This phrases activity does not contain an image.
     Phrases activity contains default translation,
     hindi translation and audio.
     */
    public Word(String fDefault, String fHindi, int fAudioId) {
        defaultTranslation = fDefault;
        hindiTranslation = fHindi;
        audioResourceId = fAudioId;
    }

    /**
     For rest of the categories which has an image
     These categories contain default translation, hindi translation,
     image and audio.
     */
    public Word(String fDefault, String fHindi, int fImageId, int fAudioId) {
        defaultTranslation = fDefault;
        hindiTranslation = fHindi;
        imageResourceId = fImageId;
        audioResourceId = fAudioId;
    }

    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public String getHindiTranslation() {
        return hindiTranslation;
    }

    public boolean hasImage() {
        return imageResourceId != NO_IMAGE_RESOURCE;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getAudioResourceId(){
        return audioResourceId;
    }

    @Override
    public String toString() {
        return "Word{" +
                "\ndefaultTranslation='" + defaultTranslation + '\'' +
                ",\nhindiTranslation='" + hindiTranslation + '\'' +
                ",\nNO_IMAGE_RESOURCE=" + NO_IMAGE_RESOURCE +
                ",\nimageResourceId=" + imageResourceId +
                ",\naudioResourceId=" + audioResourceId +
                '}';
    }
}
