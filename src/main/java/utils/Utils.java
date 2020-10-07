package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class Utils{
	
	public static String getUrlPatterns(String url) {
		
    	return url.split("/")[url.split("/").length - 1].split("\\?")[0];
    }
	
	public static String formatAlias(String str) {
	    try {
	        String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
	        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	        return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return "";
	}
	
	public static String getPath(String url) {
	   String root = "http://localhost:8080/";
	    return root + url;
	}
	
	public static String [] getFilter(String url) {
		String[] s = {""};
		if(url == null) return s;
    	return url.split("14082410and14082410");
    }
	
	public static String convertToVND(int money) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		return formatter.format(money)+" ₫";
	}
	
	public static String getDiscoutPercent(int price, int pricePromotion) {
		if(price == 0 || pricePromotion == 0) return "";
		int percent = (int) Math.floor(((double) price - (double) pricePromotion)  / (double) price * 100);
		
		return "- " + percent + "%";
	}
	public static String maHoaMD5(String str) {
        byte[] defaultBytes = str.getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            str = hexString + "";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }
}
