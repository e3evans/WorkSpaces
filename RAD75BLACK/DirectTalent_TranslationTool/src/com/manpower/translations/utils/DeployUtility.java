package com.manpower.translations.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Utility class used to send properties files data to the remote file service
 * utility FileDistributor.
 */
public abstract class DeployUtility {

   public static String FILENAME = "filename";
   public static String BUNDLEBASE = "bundlebasename";
   public static String REMOTEDIRECTORY = "directory";
   public static String DEPLOYSERVERS = "deployservers";
   private static String distroURI = "distroURI";
   private static String localStorage = "localstorage";

   public static String makeJarFile(List<String> includeFiles, String jarName, ResourceBundle config) {

      // File Read Buffer
      byte[] buf = new byte[1024];
      ZipOutputStream zipOut = null;
      FileInputStream fileIn = null;
      try {
         zipOut = new ZipOutputStream(
               new FileOutputStream(config.getString(localStorage) + jarName));

         for (int i = 0; i < includeFiles.size(); i++) {
            fileIn = new FileInputStream(config.getString(localStorage) + includeFiles.get(i));
            zipOut.putNextEntry(new ZipEntry(includeFiles.get(i)));
            int fileLen;
            while ((fileLen = fileIn.read(buf)) > 0) {
               zipOut.write(buf, 0, fileLen);
            }
            zipOut.closeEntry();
            fileIn.close();
         }

      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if (zipOut != null)
            try {
               zipOut.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
      }

      return "";
   }

   /**
    * Send a file to the remote service.
    *
    * @param server
    *           - Server key prefix from config file.
    * @param file
    *           - Name of file to deploy
    * @param config
    *           - Resource Bundle containing configuration.
    * @return a string message result.
    * @throws IOException
    */
   public static String deployPropertyFile(String server, String file, ResourceBundle config) {

      List<String> distServers = Arrays.asList(config.getString(server + ".url").split(","));
      String results = server + " updated successfully.";
      URLConnection servletconnection = null;
      DataInputStream inputFromClient = null;
      DataOutputStream fileStream = null;
      FileInputStream fileInput = null;
      String remoteDir = null;
      String distUrl = null;

      try {

         for (String serverURL : distServers) {
            distUrl = serverURL + config.getString(distroURI);

            URL servleturl = new URL(distUrl);
            remoteDir = config.getString(server + "." + REMOTEDIRECTORY);
            servletconnection = servleturl.openConnection();
            servletconnection.setDoInput(true);
            servletconnection.setDoOutput(true);
            servletconnection.setRequestProperty(FILENAME, file);
            servletconnection.setRequestProperty(REMOTEDIRECTORY, remoteDir);
            servletconnection.setRequestProperty(BUNDLEBASE, getBundleBaseName(file));
            servletconnection.setUseCaches(false);
            servletconnection.setDefaultUseCaches(false);

            /* FILE SENDING TO SERVLET */
            fileInput = new FileInputStream(config.getString(localStorage) + "/"
                  + getBundleBaseName(file) + "/" + file);
            byte[] fileBuff = new byte[fileInput.available()];
            int bytesread = 0;
            fileStream = new DataOutputStream(servletconnection.getOutputStream());
            while ((bytesread = fileInput.read(fileBuff)) > -1) {
               fileStream.write(fileBuff, 0, bytesread);
            }

            /* RETURN INFORMATION FROM DISTRO SERVLET.... */
            inputFromClient = new DataInputStream(servletconnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFromClient, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
               System.out.println(line);
            }

            if (inputFromClient != null) {
               inputFromClient.close();
               if (fileInput != null)
                  fileInput.close();
               if (fileStream != null) {
                  fileStream.flush();
                  fileStream.close();
               }
            }
         }

      } catch (MalformedURLException e1) {
         results = "the server URL is not well formed: " + distUrl + " is not valid. " + e1.getMessage();
         e1.printStackTrace();
      } catch (IOException e) {
         results = "the connection couldn't be established to the server: " + server + " for the URL: " + distUrl
               + ". One of the related servers/applications may be down or out of scope. Server Message: "
               + e.getMessage() + ", " + e.getCause();
         e.printStackTrace();
      }

      return results;
   }

   private static String getBundleBaseName(String propFileName) {
      int fileSuffix = propFileName.indexOf(".properties");
      if (propFileName.substring(fileSuffix - 3, fileSuffix - 2).indexOf("_") > -1) {
         propFileName = propFileName.substring(0, fileSuffix - 3);
      } else {
         propFileName = propFileName.substring(0, fileSuffix);
      }
      return propFileName;
   }

   /**
    * Remove all remote files related to the parameters.
    *
    * @param server
    *           URL + PORT of the remote file.
    * @param fileToWildCard
    *           the format is country code: 3 digits + template name.
    * @param config
    *           resurce bundle.
    * @return status string message.
    */
   public static String deleteRemoteFiles(String server, String fileToWildCard,
         ResourceBundle config) {

      List<String> distServers = Arrays.asList(config.getString(server + ".url").split(","));
      String results = server + " updated successfully.";
      URLConnection servletconnection = null;
      DataInputStream inputFromClient = null;
      DataOutputStream fileStream = null;
      FileInputStream fileInput = null;
      String remoteDir = null;
      String distUrl = null;
      try {

         for (String serverURL : distServers) {
            distUrl = serverURL + config.getString(distroURI);

            URL servleturl = new URL(distUrl);
            remoteDir = config.getString(server + "." + REMOTEDIRECTORY);
            servletconnection = servleturl.openConnection();
            servletconnection.setDoInput(true);
            servletconnection.setDoOutput(true);
            servletconnection.setRequestProperty("deleteFiles", "true");
            servletconnection.setRequestProperty("fileToWildCard", fileToWildCard);
            servletconnection.setRequestProperty(REMOTEDIRECTORY, remoteDir);
            servletconnection.setUseCaches(false);
            servletconnection.setDefaultUseCaches(false);

            // the followings 2 lines calls the servlet post method
            fileStream = new DataOutputStream(servletconnection.getOutputStream());
            inputFromClient = new DataInputStream(servletconnection.getInputStream());

            if (inputFromClient != null) {
               inputFromClient.close();
               if (fileInput != null)
                  fileInput.close();
               if (fileStream != null) {
                  fileStream.flush();
                  fileStream.close();
               }
            }
         }

      } catch (MalformedURLException e1) {
         results = "the server URL is not well formed: " + distUrl + " is not valid. " + e1.getMessage();
         e1.printStackTrace();
      } catch (IOException e) {
         results = "the connection couldn't be established to the server: " + server + " for the URL: " + distUrl
            + ". One of the related servers/applications may be down or out of scope. Server Message: "
            + e.getMessage() + ", " + e.getCause();
         e.printStackTrace();
      }

      return results;
   }

}