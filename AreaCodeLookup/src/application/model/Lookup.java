package application.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class represents a park zone with a name, zone code, and threat level.
 * @author Justin Elson (mwt954)
 */
public class Lookup {
	public ArrayList<String> areaCodes, stateNames;
	public ArrayList<File> fileNames;
	public HashMap<ArrayList<String>, String> hashMap;
	public Scanner input;
	
	/**
	 * Class constructor
	 */
	public Lookup()
	{
		setAreaCodesList();
		setStateNamesList();
		setFileNamesList();
		setHashMap();
	}
	
	/**
	 * This method loads locale data from a file.
	 */
	public void getData()
	{
		ArrayList<String> temp = new ArrayList<String>();
		File file = new File("data/us_area_codes.csv");
		//while the file exists and we can read it
		if (file.canRead())
		{
			try
			{
				input = new Scanner(file);
				//while the file exists and we can read it
				while (input.hasNext())
				{
					input.useDelimiter(",");
					//student object properties
					String areaCode, areaName, areaAbbreviation, areaAbbreviationModified;
					areaCode = input.next().replaceAll("\\D+","");
					areaName = input.next();
					input.useDelimiter("\r\n");
					areaAbbreviation = input.next();
					//instead of regex lets do this
					areaAbbreviationModified = areaAbbreviation.substring(1).trim();
					areaName += " (" + areaAbbreviationModified + ")";
					areaCodes.add(areaCode);
				}
				input.close();
				input = new Scanner(file);
				
				while (input.hasNextLine())
				{
					input.useDelimiter("\r\n");
					String infoLine;
					infoLine = input.nextLine();
					temp.add(infoLine);
				}
			} 
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			for (String code : areaCodes)
			{
				for (String line : temp)
				{
					if (line.contains(code))
					{
						ArrayList<String> tempList = new ArrayList<String>();
						line = line.substring(line.length() - 2);
						tempList.add(code);
						hashMap.put(tempList, line);
					}
				}
			}
		}
	}
	
	/**
	 * This method loads picture files from a directory to a list
	 */
	public void getImageData()
	{
		File file = new File("images/");		
		for (File item : file.listFiles())
		{
				fileNames.add(item);
		}
	}
	
	/**
	 * This method parses the state name entered by the user.
	 * @param stateIn  the state input by the user passed in as a string
	 */  
	public ArrayList<String> lookupState(String codeIn)
	{
		ArrayList<String> results = new ArrayList<String>();
		for (ArrayList<String> codeList : hashMap.keySet())
		{
			for (String code : codeList)
			{
				if (codeIn.contains(code))
				{
					results.add(hashMap.get(codeList));
				}
			}
		}
		results.add(codeIn);
		return results;
	}
	
	//Getters
	
	/**
	 * This method returns an array list of files.
	 */
	public ArrayList<File> getfileNamesList(){
		return fileNames;
	}
	
	// Setters
	
	/**
	 * This method instantiates an array list of strings.
	 */
	public void setAreaCodesList() {
		areaCodes = new ArrayList<String>();
	}
	
	/**
	 * This method instantiates an array list of strings.
	 */
	public void setStateNamesList() {
		stateNames = new ArrayList<String>();
	}
	
	/**
	 * This method instantiates an array list of files.
	 */
	public void setFileNamesList() {
		fileNames = new ArrayList<File>();
	}
	
	/**
	 * This method initializes a hash map.
	 */
	public void setHashMap()
	{
		hashMap = new HashMap<ArrayList<String>, String>();
	}
}
