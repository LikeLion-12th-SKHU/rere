package net.skhu.likelion12thteam03be.emotion.domain;

public enum Emotions {  //  converter
    HAPPY("행복한"),
    HURRIED("괴로운"),
    DULL("암울한"),
    TOUCHED("감동적인"),
    EXCITING("신나는"),
    RELAXED("편안한"),
    FEARFUL("두려운"),
    INTERESTING("흥미로운"),
    NERVOUS("긴장한"),
    ANXIOUS("불안한"),
    JOYFUL("기쁜"),
    CONCERNED("걱정되는"),
    EXCITED("설레는"),
    WEAPON("무기력한"),
    FRUSTRATED("답답한"),
    FRESH("상쾌한");

    private final String name;

    Emotions(String name) {
        this.name = name;
    }
}
