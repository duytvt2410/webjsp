package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import model.ImagesModel;
import service.InterfaceImagesService;
import service.imp.ImagesService;
 
public class DownloadImage {
	private static InterfaceImagesService imgs = ImagesService.getInstance();
 
    public static void main(String[] args) {
       List<ImagesModel> listAll = imgs.findAll();
       for(ImagesModel img : listAll) {
    	   File f = new File("e:\\backupimgweb\\" + img.getPhoto());
    	   if(!f.exists()) {
    		   download("https://shopjsp.j.layershift.co.uk/images/" + img.getPhoto(), "e:\\backupimgweb", img.getPhoto());
    	   }
       }
    }
    
    public static void download(String urlImg, String pathImg, String name) {
    	 String imageUrl = urlImg;
    	 
         InputStream inputStream = null;
         OutputStream outputStream = null;
  
         try {
             URL url = new URL(imageUrl);
             inputStream = url.openStream();
             pathImg = pathImg + "\\" + name;
             outputStream = new FileOutputStream(pathImg);
  
             byte[] buffer = new byte[2048];
             int length;
  
             while ((length = inputStream.read(buffer)) != -1) {
                 outputStream.write(buffer, 0, length);
             }
  
         } catch (MalformedURLException e) {
             System.out.println("MalformedURLException :- " + e.getMessage());
  
         } catch (FileNotFoundException e) {
             System.out.println("FileNotFoundException :- " + e.getMessage());
  
         } catch (IOException e) {
             System.out.println("IOException :- " + e.getMessage());
  
         } 
    }
 
}