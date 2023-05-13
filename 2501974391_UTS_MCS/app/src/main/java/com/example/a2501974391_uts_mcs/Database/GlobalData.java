package com.example.a2501974391_uts_mcs.Database;

public class GlobalData {
    private static GlobalData data;

    public static GlobalData getData() {
        if(data == null){
            data = new GlobalData();
        }
        return data;
    }

    public GlobalData() {}

    public Integer currentUserId;

}
