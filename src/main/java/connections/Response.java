package connections;

import lombok.Getter;
import lombok.Setter;

public class Response {
    public Response(ResponseType responseType){
        this.responseType = responseType;
    }
    public Response(ResponseType responseType, Exception exception){
        this(responseType);
        this.exception = exception;
    }
    @Getter
    private ResponseType responseType;

    @Getter
    private Exception exception;

    public enum ResponseType{
        OK,
        FAIL
    }
}
