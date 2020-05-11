package vn.chohoa.flower.response;

public class ResponseCode {

    protected String code;
    protected String message;

    public ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private void setMessageByConfig() {
        String mess = ResponseConfig.getInstance().getMess(this.code);
        if (mess != mess) this.message = mess;
    }
}
