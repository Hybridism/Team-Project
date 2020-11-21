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
	public HashMap<String, String> hashMap;
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
					areaCode = input.next();
					areaName = input.next();
					input.useDelimiter("\n");
					areaAbbreviation = input.next();
					//instead of regex lets do this
					areaAbbreviationModified = areaAbbreviation.substring(1);
					hashMap.put(areaName + "(" + areaAbbreviationModified + ")", areaCode);				
				}
			} 
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
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
			if (item != null){
				fileNames.add(item);
			}
		}
	}
	
	/**
	 * This method parses the state name entered by the user.
	 * @param stateIn  the state input by the user passed in as a string
	 */  
	public ArrayList<String> lookupState(String codeIn)
	{
		ArrayList<String> results = new ArrayList<String>();
		//get state name and areas code(s)
		for (String code : hashMap.values())
		{
			if (codeIn.contains(code))
			{				
				for (String name : hashMap.keySet())
				{
					if (hashMap.get(name).contains(codeIn))
					{
						results.add(name);
						results.add(code);
					}
				}				
			}
		}
		return results;
	}
	
	/**
	 * This method parses the area code entered by the user.
	 * @param codeIn  the area code input by the user passed in as a string
	 */  
	public ArrayList<String> lookupCodes(String stateIn)
	{
		ArrayList<String> results = new ArrayList<String>();
		for (String state : hashMap.keySet())
		{
			if (stateIn.contains(state))
			{	
				for (String code : hashMap.values())
				{
					results.add(code);
				}
			}
		}
		return results;
	}
	
	//Getters
	
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
		areaCodes = new ArrayList<String>();
	}
	
	/**
	 * This method instantiates an array list of strings.
	 */
	public void setFileNamesList() {
		areaCodes = new ArrayList<String>();
	}
	
	/**
	 * This method initializes a hash map of the park.
	 */
	public void setHashMap()
	{
		hashMap = new HashMap<String, String>();
	}
}
