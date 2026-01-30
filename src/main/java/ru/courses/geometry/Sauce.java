package ru.courses.geometry;

public class Sauce {
   public enum Sharpness{
        VERY_SHARP("Очень острый"),
        SHARP("Острый"),
        NOT_SHARP("Не острый");
        private final String displayText;

        Sharpness(String displayText) {
            this.displayText = displayText;
        }

        @Override
        public String toString() {
            return  displayText;
        }
    }
    private final String name;
    private final Sharpness sharpness;

    public Sauce(String name, Sharpness sharpness) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название соуса не может быть пустым или null");
        }
        if (sharpness == null) {
            throw new IllegalArgumentException("Уровень остроты не может быть null");
        }
        this.name = name.trim();
        this.sharpness = sharpness;
    }

    public String getName() {
        return name;
    }

    public Sharpness getSharpness() {
        return sharpness;
    }

    @Override
    public String toString() {
        return "Соус " + name + ": " +
                sharpness;
    }
}


