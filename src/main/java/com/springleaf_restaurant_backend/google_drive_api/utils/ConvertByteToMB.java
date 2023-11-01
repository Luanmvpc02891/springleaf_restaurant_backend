package com.springleaf_restaurant_backend.google_drive_api.utils;

public class ConvertByteToMB {
    public static String getSize(long size) {
        long n = 1024;
        String s = "";
        double kb = size / n;
        double mb = kb / n;
        double gb = mb / n;
        double tb = gb / n;
        if(size < n) {
            s = size + "";
        } else if(size >= n && size < (n * n)) {
            s =  String.format("%.1f", kb) + "";
        } else if(size >= (n * n) && size < (n * n * n)) {
            s = String.format("%.1f", mb) + "";
        } else if(size >= (n * n * n) && size < (n * n * n * n)) {
            s = String.format("%.2f", gb) + "";
        } else if(size >= (n * n * n * n)) {
            s = String.format("%.2f", tb) + "";
        }
        return s;
    }
}