# Kyatchifurzu

Simple android app to set popular Anime CatchPhrase(Japanese pronunciation : Kyatchifurzu ) as your notification tune.

## Steps to build and install

* git clone https://github.com/rajeev29five/Kyatchifurzu.git

* Import the project in Android Studio

* Hit run button

## Contributions

### How to add new tunes?

* Add your audio file in **res/raw/** directory in **.ogg** format. Use smaller audio file with duration less than 5 secs.

* Edit **AudioDetailsEnum.java** file to add Enum for new audio file.

* Enum Convetions

    * Enum **Key** must be same as audio file name without **.extension** in **CAPS**. Ex - If audio file name is **anime.ogg** then Enum key will be **ANIME**. Check existing Enums and audio files for references.

    * Enum **Value** must be **CatchPhrase** and **Anime** name separated by **:**. Check existing Enums for references.

Rebuild and install, new audio files will be reflected in the App.