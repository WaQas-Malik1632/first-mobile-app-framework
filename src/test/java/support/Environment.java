package support;

public final class Environment {
    private Environment() {}

    public static String required(String name) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Set the required environment variable: " + name);
        }
        return value;
    }

    public static String orDefault(String name, String defaultValue) {
        String value = System.getenv(name);
        return value == null || value.isBlank() ? defaultValue : value;
    }
}

