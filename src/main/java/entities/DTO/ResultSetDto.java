package entities.DTO;

import java.util.HashMap;
import java.util.Map;

public class ResultSetDto {

    private Map <String, Object> data = new HashMap<>();

    public void set(String parameter, Object value) {
        data.put(parameter, value);
    }

    public Object get(String parameter) {
        return data.get(parameter);
    }

    public String getString(String parameter) {
        return (String) data.get(parameter);
    }

    public Integer getInt(String parameter) {
        return (Integer) data.get(parameter);
    }
}
