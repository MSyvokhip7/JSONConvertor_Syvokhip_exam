package json;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private final ArrayList<JsonPair> jsonPairs = new ArrayList<JsonPair>();
    private int count;

    public JsonObject(JsonPair... jsonPairs) {
        for(JsonPair p: jsonPairs) {
            this.add(p);
        }
    }

    @Override
    public String toJson() {
        StringBuffer sb = new StringBuffer();
        int count = 0;
        if (!this.jsonPairs.isEmpty()) {
            for (JsonPair jp : this.jsonPairs) {
                if (this.count == 1) {
                    return "{'" + jp.key + "': " + jp.value.toJson() + "}";
                }
                if (count == 0) {
                    sb.append("{'").append(jp.key).append("': ").append(jp.value.toJson());
                    count++;
                } else {
                    if (jsonPairs.size() - 1 > count) {
                        sb.append(", '").append(jp.key).append("': ").append(jp.value.toJson());
                        count++;
                    } else {
                        sb.append(", '").append(jp.key).append("': ").append(jp.value.toJson()).append("}");
                    }
                }
            }
            return sb.toString();
        } else {
            return "{}";
        }
    }

    public void add(JsonPair jsonPair) {
        boolean state = false;
        for (JsonPair jp : this.jsonPairs) {
            if (Objects.equals(jp.key, jsonPair.key)) {
                this.jsonPairs.remove(jp);
                JsonPair newjsP = new JsonPair(jp.key, jsonPair.value);
                this.jsonPairs.add(newjsP);
                state = true;
                break;
            }
        }
        if (state) {
            return;
        }
        this.jsonPairs.add(jsonPair);
        this.count++;
    }

    public Json find(String name) {
        for(JsonPair p: this.jsonPairs){
            if (Objects.equals(p.key, name)) return p.value;
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject obj = new JsonObject();
        for(JsonPair p : this.jsonPairs){
            for(String el: names){
                if(Objects.equals(p.key, el)) obj.add(p);
            }
        }
        return obj;
    }
}
