package com.manpower.translations.service;

import java.io.File;

import com.manpower.translations.model.LocatedValue;
import com.manpower.translations.model.PropertyLine;
import com.manpower.translations.model.SimpleLocatedValue;
import com.manpower.translations.model.SimplePropertyFile;
import com.manpower.translations.model.SimplePropertyLine;


public class PropertyFileManagerImplTest {

   public void testCreateFile(){
      PropertyFileManagerImpl pm = new PropertyFileManagerImpl();
      LocatedValue locatedValue = null;
      PropertyLine propertyLine = null;
      File file = pm.createPropertyFile("mytemplateNewFile.properties");
      SimplePropertyFile templatePropertyFile = new SimplePropertyFile("ARG", file.getName());

      locatedValue = new SimpleLocatedValue("es");
      locatedValue.setValue("Hola Mundo");
      propertyLine = new SimplePropertyLine("page.welcome");
      propertyLine.addLocale(locatedValue);
      templatePropertyFile.addNewPropertyLine(propertyLine);

      locatedValue = new SimpleLocatedValue("eu");
      locatedValue.setValue("Hello World");
      propertyLine = new SimplePropertyLine("page.welcome");
      propertyLine.addLocale(locatedValue);
      templatePropertyFile.addNewPropertyLine(propertyLine);

      locatedValue = new SimpleLocatedValue("fr");
      locatedValue.setValue("Bonjiu");
      propertyLine = new SimplePropertyLine("page.welcome");
      propertyLine.addLocale(locatedValue);
      templatePropertyFile.addNewPropertyLine(propertyLine);

      pm.savePropertiesToFiles(templatePropertyFile);
   }

   public static void main(String[] args) {
      new PropertyFileManagerImplTest().testCreateFile();
   }
}