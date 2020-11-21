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
		    if(matchFound){
		    	showStateResults(lookup.lookupState(inputString));
		    }else{
		    	showCodeResults(lookup.lookupCodes(inputString));
		    }	  
		}
		
	}
	
	private void showStateResults(ArrayList<String> listIn)
	{
		stateNameOutput.setText(listIn.get(0));
		areaCodeOutput.setText("");
		for (int i = 1; i < listIn.size(); i++)
		{
			areaCodeOutput.setText(areaCodeOutput.getText() + listIn.get(i));
		}
		for (File file : lookup.getfileNamesList())
		{
			if (file.getName().contains(listIn.get(0)))
			{
				Image img = new Image(file.toURI().toString());
				stateImage.setImage(img);
			}
		}
	}
	
	private void showCodeResults(ArrayList<String> listIn)
	{
		
	}
}
