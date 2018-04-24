package easytests.support;


/**
 * @author malinink
 */
public class JsonSupport {
    private static char quote = '"';

    private String json;

    public JsonSupport with(String key, String value) {
        this.prepareJsonObject();
        this.json += this.gatherLeft(key) + quote + value + quote;
        return this;
    }

    public JsonSupport with(String key, Integer value) {
        this.prepareJsonObject();
        this.json += this.gatherLeft(key) + value;
        return this;
    }

    public JsonSupport with(String key, Boolean value) {
        this.prepareJsonObject();
        this.json += this.gatherLeft(key) + (value ? "true" : "false");
        return this;
    }

    public String build(Boolean notNull) {
        if (notNull) {
            this.prepareJsonObject();
        }
        return this.build();
    }

    public String build() {
        this.finishJsonObject();
        return this.json;
    }

    public JsonSupport empty() {
        this.json = null;
        return this;
    }

    private void prepareJsonObject() {
        if (this.json == null) {
            this.json = "{";
        } else {
            this.json += ", ";
        }
    }

    private void finishJsonObject() {
        if (this.json != null) {
            this.json += '}';
        }
    }

    private String gatherLeft(String key) {
        return quote + key + quote + ": ";
    }
}
