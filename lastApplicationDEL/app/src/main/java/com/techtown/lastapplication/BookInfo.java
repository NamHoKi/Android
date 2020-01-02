package com.techtown.lastapplication;

public class BookInfo {

    int number;
    String name;
    String author;
    String contents;
    String time;
    double lat;
    double lon;
    byte[] photoimage;

    public BookInfo(int number, String name, String author, String contents, String time, double lat, double lon, byte[] photoimage) {
        this.number = number;
        this.name = name;
        this.author = author;
        this.contents = contents;
        this.time = time;
        this.lat = lat;
        this.lon = lon;
        this.photoimage = photoimage;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getTime() {return time;}
    public void setTime() {this.time=time;}

    public int getNumber() {return number;}
    public void setNumber() {this.number = number;}

    public double getLat() {return lat;}
    public void setLat() {this.lat = lat;}

    public double getLon() {return lon;}
    public void setLon() {this.lon = lon;}

    public byte[] getphotoimage() {
        return photoimage;
    }
    public void setphotoimage(byte[] photoimage) {
        this.photoimage = photoimage;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", contents='" + contents + '\'' +
                ", time ='" + time + '\'' +
                ", lat ='" + lat + '\'' +
                ", lon ='" + lon + '\'' +
                ", photoimage='" + bytearrayToString(photoimage) + '\'' +
                '}';
    }
    public String bytearrayToString(byte[] arr){
        String str = new String(arr);
        return str;
    }

}
