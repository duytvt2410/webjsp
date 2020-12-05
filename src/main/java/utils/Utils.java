package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import model.ViewsWebModel;

public class Utils{
	private static String urlRoot = "https://shopjsp.j.layershift.co.uk/";
	
	public static String getUrlPatterns(String url) {
		
    	return url.split("/")[url.split("/").length - 1].split("\\?")[0];
    }
	
	public static String getPathImage(String name) {
		String url = urlRoot + "images/" + name;
    	return url;
    }
	
	public static String getUrlUploadImage(String name) {
		String url = urlRoot + name;
    	return url;
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
	
	public static String formatDate(Date date) {
		if(date == null) return "";
	        SimpleDateFormat formatterDay = new SimpleDateFormat("dd/MM/yyyy");
	        SimpleDateFormat formatterTime = new SimpleDateFormat("kk:mm");
	        String strDay = formatterDay.format(date);
	        String strTime = formatterTime.format(date);
	        
	        if(Integer.parseInt(strTime.split(":")[0]) >= 0  && Integer.parseInt(strTime.split(":")[0]) < 12) {
	        	strTime += " AM";
	        } else {
	        	int hour = Integer.parseInt(strTime.split(":")[0]);
	        	strTime = (hour - 12) + ":"+ strTime.split(":")[1] + " PM";
	        }
	        return strDay + " " + strTime;
	}	
	
	public static String limitName(String name, int limit) {
			if(name.length() > limit) {
				char nameChar[] = name.toCharArray();
				String res = "";
				for(int i = 0; i <= limit; i++) {
					res+= nameChar[i];
				}
				
				return res +="...";
			}
	        return name;
	}
	
	public static Long getMaxViews(List<ViewsWebModel> list) {
		long maxInList = 0L;
		for(ViewsWebModel view : list) {
			if(view.getViews() > maxInList) maxInList = view.getViews();
		}
		String res = "10";
		long temp = maxInList;
		while(temp / 10 != 0) {
			res += "0";
			temp = temp / 10;
		}
		
		long maximum = Long.parseLong(res);
		long real = maxInList;
		long min = (maximum / 5) * 0;
		long max = (maximum / 5) * 1;
		int index = 1;
		while(true) {
			if(real >= min && real < max) break;
			 min = (maximum / 5) * index;
			 max = (maximum / 5) * (index + 1);
			 index++;
			 
		}
		
		return max;
	}
	
	public static String[] arrColorCode() {
		String code[] = {"#FF0000", 
				"#FFD700", 
				"#FFA500", 
				"##32CD32", 
				"#5F9EA0", 
				"#F5DEB3", 
				"#2F4F4F", 
				"#C71585", 
				"#663399", 
				"#FFA07A", 
				"#ADFF2F", 
				"#191970", 
				"#F0F8FF", 
				"#B0E0E6", 
				"#FF4500", 
				"#FFE4B5"};
		
        return code;
	}
	
	
}
