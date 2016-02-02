package tools.datagen;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.FilterReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class PopulateFeatureExamples {

	
	
    // Insert logic for processing found files here.
    private static void ProcessExample(BufferedWriter processedFeatureFileTempBufferedWriter, String data, String scenarioTitle) throws FileNotFoundException, IOException
    {
    	processedFeatureFileTempBufferedWriter.write("\t anything" + data);
    	
    }
	
    // Insert logic for processing found files here.
    private static void ProcessFile(String path) throws FileNotFoundException, IOException
    {
        // Read the file and display it line by line.
        String originalFeatureFilePath = path;
        String processedFeatureFileTempPath = path + ".temp";

        File originalFeatureFile = new File(originalFeatureFilePath);
        File processedFeatureFileTemp = new File (processedFeatureFileTempPath);

        if(processedFeatureFileTemp.exists() && !processedFeatureFileTemp.isDirectory()) { 
        	processedFeatureFileTemp.delete();
        }
        
        FileInputStream originalFileInput = new FileInputStream(originalFeatureFile);
        BufferedReader originalFileInputBufferedReader = new BufferedReader(new InputStreamReader(originalFileInput));

        FileWriter processedFeatureFileTempWriter = new FileWriter(processedFeatureFileTempPath, true);
        BufferedWriter processedFeatureFileTempBufferedWriter = new BufferedWriter(processedFeatureFileTempWriter);
        
        String featureFileName = originalFeatureFile.getName();
        		
        System.out.println(featureFileName); 		
        System.out.println(String.format("Processed file %1$s", originalFeatureFilePath));
        
        String scenarioTitle = "";

        String line = null;
        while ((line = originalFileInputBufferedReader.readLine()) != null)
        {
        	System.out.println(line);
            if (!line.contains("#autodatagen#")) {
            	
                if (line.contains("Scenario Outline: ")) {
                    int position = ("Scenario Outline: ").length() + 1;
                    scenarioTitle = line.substring(position).trim();
                }
                processedFeatureFileTempBufferedWriter.write(line);
                processedFeatureFileTempBufferedWriter.newLine();
            }
            else
            {
            	String jsonText = new String(Files.readAllBytes(Paths.get(String.format("features_json/%1$s.json", featureFileName))), StandardCharsets.UTF_8);
            	 
            	ProcessExample(processedFeatureFileTempBufferedWriter, jsonText, scenarioTitle);
                scenarioTitle = "";
            }
        }
        
        originalFileInput.close();
        originalFileInputBufferedReader.close();
        processedFeatureFileTempWriter.flush();
        processedFeatureFileTempBufferedWriter.flush();
        processedFeatureFileTempWriter.close();
        processedFeatureFileTempBufferedWriter.close();
        

        //repalce files (temp with feature)
        Files.delete(originalFeatureFile.toPath());
        Files.copy(processedFeatureFileTemp.toPath(), originalFeatureFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(processedFeatureFileTemp.toPath());
    }

    public static void ProcessDirectory(final File folder) throws IOException {
            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isDirectory()) {
                	ProcessDirectory(fileEntry);
                } else {
                    System.out.println(String.format("Processing feature file %1s", fileEntry.getName()));
                	ProcessFile(fileEntry.getName());
           }
       }
    }

    private static File[] GetFilesInFolderWithSpecificExtension(String folderPath, String extension){
    	File folder = new File(folderPath);

    	return folder.listFiles(new FilenameFilter() { 
    	         public boolean accept(File dir, String filename)
    	              { return filename.endsWith(extension); }
    	} );
    }
        
        
    public static void main(String[] args) throws IOException
    {
        String feature_files_location = "src/test/resources/feature/fueling/";

        File[] featureFiles = GetFilesInFolderWithSpecificExtension(feature_files_location, ".feature");

        for (int i = 0; i < featureFiles.length; i++)
        {
            ProcessFile(featureFiles[i].getPath());
        }
    }
}



	
	
	
	
