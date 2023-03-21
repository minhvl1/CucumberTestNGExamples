package models;


import io.restassured.http.Header;

public interface RequestCapability {
    Header defaultHeader = new Header("Content-type","application/json; charset=UTF-8");

    static Header getAuthenticatedHeader(String encodedCreStr){
        if(encodedCreStr == null){
            throw new IllegalArgumentException("encodedCreStr can't be null");
        }
        return new Header("Authorization","Basic "+encodedCreStr);
    }

}
