package application.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * This class represents a view controller that will process the interactions between the user and the layout.
 * @author Justin Elson (mwt954)
 */
public class GUIController {
	
	@FXML
	private TextField textInput;
	@FXML
	private Text areaCodeOutput;
	@FXML
	private Text stateNameOutput;
	@FXML
	private ImageView stateImage;
	
	Lookup lookup;
	
	/**
	 * This method initializes the data to be presented to the user.
	 */
	public void initialize()
	{
		lookup = new Lookup();
		lookup.getData();
		lookup.getImageData();
		areaCodeOutput.setText("");
		stateNameOutput.setText("");
	}
	
	/**
	 * This method will change the scene to the content loaded from Zone.fxml.
	 */
	@FXML
	private void searchButtonPressed()
	{
		if (!textInput.getText().isEmpty())	{
			String inputString = textInput.getText();
			textInput.clear();
			//if the input contains a digit look for a state if it contains a state name look for area codes
			Pattern pattern = Pattern.compile("\\d");
		    Matcher matcher = pattern.matcher(inputString);
		    boolean matchFound = matcher.find();
		    if(matchFound)
		    	showStateResults(lookup.lookupState(inputString));
		}	
	}
	
	private void showStateResults(ArrayList<String> listIn)
	{
		if (!(listIn.size() == 0))
		{
			String stateString;
			stateString = getFullName(listIn.get(0));
			stateNameOutput.setText(stateString);
			areaCodeOutput.setText(listIn.get(1));	
			for (File file : lookup.getfileNamesList())
			{
				if (file.getName().contains(listIn.get(0)))
				{
					Image img = new Image(file.toURI().toString());
					stateImage.setImage(img);
				}
			}
		}
		else
		{
			
		}
	}
	
	private String getFullName(String stringIn)
	{
		String stringOut ="";
		switch(stringIn)
		{
		case "AL":
			stringOut = "Alabama";
			break;
		case "AK":
			stringOut = "Alaska";
			break;
		case "AZ":
			stringOut = "Arizona";
			break;
		case "AR":
			stringOut = "Arkansas";
			break;
		case "CA":
			stringOut = "California";
			break;
		case "CO":
			stringOut = "Colorado";
			break;
		case "CT":
			stringOut = "Connecticut";
			break;
		case "DE":
			stringOut = "Delaware";
			break;
		case "DC":
			stringOut = "District of Columbia";
			break;
		case "FL":
			stringOut = "Florida";
			break;
		case "GA":
			stringOut = "Georgia";
			break;
		case "HI":
			stringOut = "Hawaii";
			break;
		case "ID":
			stringOut = "Idaho";
			break;
		case "IL":
			stringOut = "Illinois";
			break;
		case "IN":
			stringOut = "Indiana";
			break;
		case "IA":
			stringOut = "Iowa";
			break;
		case "KS":
			stringOut = "Kansas";
			break;
		case "KY":
			stringOut = "Kentucky";
			break;
		case "LA":
			stringOut = "Louisiana";
			break;
		case "ME":
			stringOut = "Maine";
			break;
		case "MD":
			stringOut = "Maryland";
			break;
		case "MA":
			stringOut = "Massachusetts";
			break;
		case "MI":
			stringOut = "Michigan";
			break;
		case "MN":
			stringOut = "Minnesota";
			break;
		case "MS":
			stringOut = "Mississippi";
			break;
		case "MO":
			stringOut = "Missouri";
			break;
		case "MT":
			stringOut = "Montana";
			break;
		case "NE":
			stringOut = "Nebraska";
			break;
		case "NV":
			stringOut = "Nevada";
			break;
		case "NH":
			stringOut = "New Hampshire";
			break;
		case "NJ":
			stringOut = "New Jersey";
			break;
		case "NM":
			stringOut = "New Mexico";
			break;
		case "NY":
			stringOut = "New York";
			break;
		case "NC":
			stringOut = "North Carolina";
			break;
		case "ND":
			stringOut = "North Dakota";
			break;
		case "OH":
			stringOut = "Ohio";
			break;
		case "OK":
			stringOut = "Oklahoma";
			break;
		case "OR":
			stringOut = "Oregon";
			break;
		case "PA":
			stringOut = "Pennsylvania";
			break;
		case "RI":
			stringOut = "Rhode Island";
			break;
		case "SC":
			stringOut = "South Carolina";
			break;
		case "SD":
			stringOut = "South Dakota";
			break;
		case "TN":
			stringOut = "Tennessee";
			break;
		case "TX":
			stringOut = "Texas";
			break;
		case "UT":
			stringOut = "Utah";
			break;
		case "VT":
			stringOut = "Vermont";
			break;
		case "VA":
			stringOut = "Virignia";
			break;
		case "WA":
			stringOut = "Washington";
			break;
		case "WV":
			stringOut = "West Virginia";
			break;
		case "WI":
			stringOut = "Wisconsin";
			break;
		case "WY":
			stringOut = "Wyoming";
			break;
		case "PR":
			stringOut = "Puerto Rico";
			break;
		}
		return stringOut;
	}
}
