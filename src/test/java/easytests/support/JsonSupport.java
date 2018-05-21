package easytests.support;

import easytests.core.models.TesteeModelInterface;
import easytests.support.exceptions.CallArrayMethodOnObjectException;
import easytests.support.exceptions.CallObjectMethodOnArrayException;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;


/**
 * @author malinink
 */
public class JsonSupport {
    private static char quote = '"';

    private String json;

    private Boolean isObject;

    private Boolean notNull = false;

    public JsonSupport withArray() {
        this.verifyIsArray();
        return this;
    }

    public JsonSupport withNotNull() {
        this.notNull = true;
        return this;
    }

    public JsonSupport with(String key, String value) {
        this.verifyIsObject();
        this.prepareJson();
        this.json += this.gatherLeft(key) + this.convert(value);
        return this;
    }

    public JsonSupport with(String key, JsonSupport value) {
        this.verifyIsObject();
        this.prepareJson();
        this.json += this.gatherLeft(key) + this.convert(value.build(), false);
        return this;
    }

    public JsonSupport with(String key, Integer value) {
        this.verifyIsObject();
        this.prepareJson();
        this.json += this.gatherLeft(key) + this.convert(value);
        return this;
    }

    public JsonSupport with(String key, Boolean value) {
        this.verifyIsObject();
        this.prepareJson();
        this.json += this.gatherLeft(key) + this.convert(value);
        return this;
    }

    public JsonSupport with(String key, LocalDateTime value) {
        this.verifyIsObject();
        this.prepareJson();
        this.json += this.gatherLeft(key) + this.convert(value);
        return this;
    }


    public JsonSupport with(String value) {
        this.verifyIsArray();
        this.prepareJson();
        this.json += this.convert(value);
        return this;
    }

    public JsonSupport with(JsonSupport value) {
        this.verifyIsArray();
        this.prepareJson();
        this.json += this.convert(value.build(), false);
        return this;
    }

    public JsonSupport with(Integer value) {
        this.verifyIsArray();
        this.prepareJson();
        this.json += this.convert(value);
        return this;
    }

    public JsonSupport with(Boolean value) {
        this.verifyIsArray();
        this.prepareJson();
        this.json += this.convert(value);
        return this;
    }

    private String convert(String value) {
        return this.convert(value, true);
    }

    private String convert(String value, Boolean escape) {
        if (escape) {
            return quote + value + quote;
        }
        return value;
    }

    private String convert(Integer value) {
        return value.toString();
    }

    private String convert(Boolean value) {
        return value ? "true" : "false";
    }

    private String convert(LocalDateTime value) {
        return quote + value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z" + quote;
    }


    public String build() {
        if (this.notNull) {
            this.prepareJson();
        }
        this.finishJson();
        return this.json;
    }

    public JsonSupport empty() {
        this.json = null;
        this.isObject = null;
        return this;
    }

    private void prepareJson() {
        if (this.json == null) {
            this.json = "";
        } else {
            this.json += ", ";
        }
    }

    private void finishJson() {
        if (this.json != null) {
            this.json = (this.isObject ? '{' : '[') + this.json + (this.isObject ? '}' : ']');
        }
    }

    private void verifyIsArray() {
        this.isObject = this.isObject == null ? false : this.isObject;
        if (this.isObject) {
            throw new CallObjectMethodOnArrayException();
        }
    }

    private void verifyIsObject() {
        this.isObject = this.isObject == null ? true : this.isObject;
        if (!this.isObject) {
            throw new CallArrayMethodOnObjectException();
        }
    }

    private String gatherLeft(String key) {
        return quote + key + quote + ": ";
    }
}
