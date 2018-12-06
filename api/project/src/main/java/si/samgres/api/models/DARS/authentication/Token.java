package si.samgres.api.models.DARS.authentication;

public class Token {
    private String access_token;
    private String token_type;
    private String expires_in;
    private String refresh_token;

    public Token() {}

    public Token(String access_token, String token_type, String expires_in, String refresh_token) {
        setAccess_token(access_token);
        setToken_type(token_type);
        setExpires_in(expires_in);
        setRefresh_token(refresh_token);
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
