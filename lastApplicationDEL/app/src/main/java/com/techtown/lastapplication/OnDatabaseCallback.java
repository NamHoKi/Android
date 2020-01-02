package com.techtown.lastapplication;

import java.util.ArrayList;

public interface OnDatabaseCallback {
    public void insert(int number, String name, String author, String contents, double lat, double lon, byte[] photo);
    public void delete(String title);
    public ArrayList<BookInfo> selectAll();
}
