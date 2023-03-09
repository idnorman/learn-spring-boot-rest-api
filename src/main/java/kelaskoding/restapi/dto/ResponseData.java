package kelaskoding.restapi.dto;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//<T> tipenya boleh apa aja
public class ResponseData<T> {
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private String message = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private T payload;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public T getPayload() {
        return payload;
    }
    public void setPayload(T payload) {
        this.payload = payload;
    }
}
