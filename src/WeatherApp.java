import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.DropMode;
import javax.swing.JTextPane;

// Deeclaration of a class WeatherApp that extends the JFrame class. 
// WeatherApp class inherits all the properties and methods of the JFrame class.
// extends JFrame part indicates that WeatherApp is a graphical user interface (GUI) window

public class WeatherApp extends JFrame {
	
	// Constant field serialVersionUID is defined for serialization (e.g., saving the state of an object to a file).
	
    private static final long serialVersionUID = 1L; // 1L is an arbitrary unique identifier for the class version
    private JPanel contentPane; // A panel that holds the components of the GUI.
    private JTextField textField; // A text field where the user enters their location.
    private JLabel weatherIconLabel; // A label to display weather icons.
    
    // A constant string representing the API key for OpenWeatherMap.
    private static final String API_KEY = "ad500b5791e27e28485480f7c9a61e32";  // Replaced with my actual API key
    
    // A constant string representing the URL for the current weather API.
    private static final String CURRENT_WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=%s";
    
    // A constant string representing the URL for the forecast API.
    private static final String FORECAST_API_URL = "http://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s&units=%s";
    
    private JComboBox<String> tempUnitComboBox; // A combo box for selecting temperature units (Celsius or Fahrenheit).
    
    private JComboBox<String> windSpeedUnitComboBox; // A combo box for selecting wind speed units.
    
    private String tempUnit = "metric"; // A string representing the default temperature unit (metric or imperial).
    
    private boolean isWindSpeedInKmH = true; // A boolean indicating whether wind speed is displayed in km/h.
    
    private List<String> searchHistory = new ArrayList<>(); //A list to store search history.
    private JTextArea historyTextArea; // A text area for displaying search history.
    private JLabel weatherJLabel; // A label for displaying weather information.
    private JTextField txtTodaysWeather; // A text field for displaying today’s weather.

    /**
     * Launch the application.
     */
    //This main method is the entry point of the Java application where the program execution begin.
    //It create an instance of the WeatherApp class and display the GUI window to the user.
    public static void main(String[] args) { 
    	
    	// This method schedules a task (specified as a Runnable) to be executed on the event dispatch thread (EDT).
    	// The EDT is responsible for handling GUI events and updating the UI components.
        EventQueue.invokeLater(new Runnable() { // Runnable is An Anonymous inner class created to define the task that will run on the EDT.
            public void run() {
                try {
                	//Instance of the WeatherApp class is created 
                    WeatherApp frame = new WeatherApp(); // It initializes the GUI components and sets up the application window
                  
                    //Make the frame (window) visible.
                    frame.setVisible(true);  //displays the GUI window to the user
                    
                } catch (Exception e) { //Handle any exceptions that might occur during initialization
                    e.printStackTrace(); //If an exception occurs, it will be printed to the console.
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    // This is the constructor for the WeatherApp class
    // It initializes the GUI components and sets up the layout.
    // It sets the frame size, adds labels, text fields, buttons, and other components to the content pane
    public WeatherApp() {
    	
    	// This line sets the default close operation for the frame (window).
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //terminates the entire application when the user closes the window.
        
        //This line sets the position and size of the frame.
        setBounds(100, 100, 750, 600);  // Increased height for additional components.
        
        contentPane = new JPanel(); // Creates a new JPanel (a container for other components) and assigns it to the contentPane field
        contentPane.setToolTipText(""); // Clears the tooltip text for the content pane (no tooltip will be displayed when hovering over it).
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Sets an empty border with 5 pixels of padding on each side for the content pane
        setContentPane(contentPane); // Sets the content pane of the frame to the newly created JPanel.
        contentPane.setLayout(null); // Disables any layout manager for the content pane. 

       // Labels for entering the user’s location 
        JLabel LocationTextField = new JLabel("ENTER YOUR LOCATION");
        LocationTextField.setBounds(183, 46, 186, 47); //setBounds used to position labels, text fields and button 
        LocationTextField.setFont(new Font("Tahoma", Font.BOLD, 15)); //styled with fonts
        contentPane.add(LocationTextField);

       // Textfield for entering user's location
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.BOLD, 13)); //styled with fonts
        textField.setBounds(396, 55, 186, 31); // Position textfield
        contentPane.add(textField);
        textField.setColumns(10);

       // Buttton for searching weather information
        JButton searchButton = new JButton("GET WEATHER");
        searchButton.setBounds(24, 123, 167, 21);
        searchButton.setFont(new Font("Verdana", Font.BOLD, 14));
        contentPane.add(searchButton);

       // A label that will display weather icons
        weatherIconLabel = new JLabel(); 
    
        weatherIconLabel.setBounds(419, 402, 259, 118);  // Adjust position and size as needed
        contentPane.add(weatherIconLabel);
        
       // A label and a combo box for selecting temperature units (Celsius or Fahrenheit).
        JLabel tempUnitLabel = new JLabel("Temperature Unit:");
        tempUnitLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        tempUnitLabel.setBounds(54, 490, 130, 15);
        contentPane.add(tempUnitLabel);

       //  Combo box is populated with the specified options.
        tempUnitComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit"});
        tempUnitComboBox.setBounds(200, 488, 130, 20);
        contentPane.add(tempUnitComboBox);

       // A label and a combo box for selecting wind speed units (km/h or m/s).
        JLabel windSpeedUnitLabel = new JLabel("Wind Speed Unit:");
        windSpeedUnitLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        windSpeedUnitLabel.setBounds(54, 521, 130, 15);
        contentPane.add(windSpeedUnitLabel);

        //This Combo Box allows the user to select the wind speed unit (either “km/h” or “m/s”).
        windSpeedUnitComboBox = new JComboBox<>(new String[]{"km/h", "m/s"});
        windSpeedUnitComboBox.setBounds(200, 519, 130, 20);
        contentPane.add(windSpeedUnitComboBox);
        
        // Used for displaying search history.
        historyTextArea = new JTextArea();
        historyTextArea.setText("History Tracking:");
        historyTextArea.setFont(new Font("Calibri", Font.PLAIN, 13));
        historyTextArea.setEditable(false); // It’s not editable by the user (read-only).
        historyTextArea.setBounds(494, 123, 230, 207); // Adjust position and size as needed
        contentPane.add(historyTextArea);
        
        // used to display weather information (e.g., temperature, conditions)
        weatherJLabel = new JLabel("");
        weatherJLabel.setBounds(362, 402, 345, 148);
        contentPane.add(weatherJLabel);
        
        // This label serves as a title for the weather information section
        JLabel TitleTextField = new JLabel("TODAY'S WEATHER");
        TitleTextField.setBackground(Color.LIGHT_GRAY);
        TitleTextField.setBounds(241, 11, 193, 24); // Position and size adjusted using setBounds.
        TitleTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
        contentPane.add(TitleTextField);

        
       // Set up event handling for the temperature unit combo box
       // adds an action listener to the tempUnitComboBox component.
        tempUnitComboBox.addActionListener(new ActionListener() { // It respond to events related to the temperature unit selection
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tempUnitComboBox.getSelectedItem().equals("Celsius")) {
                    tempUnit = "metric";
                } else {
                    tempUnit = "imperial";
                }
                fetchAndUpdateWeatherData(); // Retrieves and updates weather data based on the chosen temperature unit.
            }
        });
        
        
        // Set up event handling for the wind speed unit combo box
        windSpeedUnitComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isWindSpeedInKmH = windSpeedUnitComboBox.getSelectedItem().equals("km/h");
                fetchAndUpdateWeatherData();
            }
        });

        // Set up event handling for the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchAndUpdateWeatherData();
                updateSearchHistory(textField.getText());
            }
        });

        // Set dynamic background based on time of day
        updateBackground();
    }

    // This method is responsible for fetching weather data based on the user-entered location
    private void fetchAndUpdateWeatherData() {
        String location = textField.getText(); // Extracting the location from the textField.

        // Perform network operations in a separate thread
        new Thread(() -> {
        	
        	//It calls fetchWeatherData() twice: once for current weather data and once for forecast data
            String currentWeatherResponse = fetchWeatherData(String.format(CURRENT_WEATHER_API_URL, location, API_KEY, tempUnit));
            String forecastResponse = fetchWeatherData(String.format(FORECAST_API_URL, location, API_KEY, tempUnit));
            SwingUtilities.invokeLater(() -> {
            	
            	// It starts by extracting the location from the textField
            	// Next, it creates a new thread to perform network operations (to avoid blocking the UI thread).
                JTextArea txtWeatherDisplay = new JTextArea();
                txtWeatherDisplay.setBackground(SystemColor.inactiveCaption);
                txtWeatherDisplay.setBounds(70, 146, 272, 310);
                txtWeatherDisplay.setFont(new Font("Bodoni MT", Font.BOLD, 13));
                contentPane.add(txtWeatherDisplay);
                
                // If both responses are not null, it updates the weather information in the txtWeatherDisplay component 
                // using the updateWeatherInfo() method.
                if (currentWeatherResponse != null && forecastResponse != null) {
                    updateWeatherInfo(currentWeatherResponse, forecastResponse, txtWeatherDisplay);
                    
                //Otherwise, if either response is null, it displays an error message in the txtWeatherDisplay.
                } else {
                    txtWeatherDisplay.setText("Could not fetch weather data. Please try again.");
                }
            });
        }).start();
    }

    // This private method performs the actual data retrieval from a given URL (in this case weather API)
    private String fetchWeatherData(String urlString) {
        try {
        	
        	// It constructs a URL object from the provided urlString.
            URL url = new URL(urlString);
            
            // It opens an HttpURLConnection to the URL 
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); //sets the request method to “GET.”

            int responseCode = connection.getResponseCode();
         // Check if the response code is 200 (OK)
            if (responseCode == HttpURLConnection.HTTP_OK) {
            	
            	// It reads the response content line by line and appends it to a StringBuilder.
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                connection.disconnect();
             // It finally returns the content as a string or null if an exception occurs.
                return content.toString();
            } else {
                // Handle non-200 response codes here
                System.err.println("Error fetching weather data: " + connection.getResponseMessage());
                return null;
            }
        } catch (FileNotFoundException e) {
            // Handle file not found (404) error
            System.err.println("Location not found: " + e.getMessage());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // The method starts by parsing the currentWeatherResponse, 
    // which likely contains JSON data from a weather API.
    private void updateWeatherInfo(String currentWeatherResponse, String forecastResponse, JTextArea txtWeatherDisplay) {
        try {
            // Parse current weather data
            JSONObject currentWeatherJson = new JSONObject(currentWeatherResponse);
            String city = currentWeatherJson.getString("name");
            JSONObject main = currentWeatherJson.getJSONObject("main");
            double temperature = main.getDouble("temp");
            int humidity = main.getInt("humidity");
            JSONObject wind = currentWeatherJson.getJSONObject("wind");
            double windSpeed = wind.getDouble("speed");
            String weatherDescription = currentWeatherJson.getJSONArray("weather").getJSONObject(0).getString("description");
            String weatherIcon = currentWeatherJson.getJSONArray("weather").getJSONObject(0).getString("icon");

            String currentWeatherText = String.format("Location: %s\nTemperature: %.2f %s\nHumidity: %d%%\nWind Speed: %.2f %s\nDescription: %s\n\n",
                    city, temperature, tempUnit.equals("metric") ? "°C" : "°F", humidity, windSpeed, isWindSpeedInKmH ? "km/h" : "m/s", weatherDescription);

            // Parse forecast data, which likely contains forecast data
            JSONObject forecastJson = new JSONObject(forecastResponse);
            JSONArray forecastArray = forecastJson.getJSONArray("list");
            
           // It processes the forecast for the next two time intervals (for brevity).
            StringBuilder forecastText = new StringBuilder("Short-term Forecast:\n");
            for (int i = 0; i < 2 && i < forecastArray.length(); i++) {  // Limit to 2 forecast entries for brevity
                JSONObject forecastObj = forecastArray.getJSONObject(i);
                JSONObject mainForecast = forecastObj.getJSONObject("main");
                double tempForecast = mainForecast.getDouble("temp"); //Extract temperature
                int humidityForecast = mainForecast.getInt("humidity"); // Extracts Humidity
                JSONObject windForecast = forecastObj.getJSONObject("wind"); //Extracts Windspeed
                double windSpeedForecast = windForecast.getDouble("speed");
                String weatherDescriptionForecast = forecastObj.getJSONArray("weather").getJSONObject(0).getString("description");
                
                // Formats this data into a string called forecastEntry.
                // Appends forecastEntry to the forecastText.
                String forecastEntry = String.format("Forecast #%d:\nTemperature: %.2f %s\nHumidity: %d%%\nWind Speed: %.2f %s\nDescription: %s\n\n",
                        i + 1, tempForecast, tempUnit.equals("metric") ? "°C" : "°F", humidityForecast, windSpeedForecast, isWindSpeedInKmH ? "km/h" : "m/s", weatherDescriptionForecast);
                forecastText.append(forecastEntry);
            }
            
            
            // This method set appropriate weather icon
            // It determines the appropriate weather icon based on the weatherDescription
            String icon = getWeatherIcon(weatherDescription);
            java.net.URL imgUrl = getClass().getResource("/icons/weather/" + icon + ".png"); //It constructs a URL for the icon image.
            
            if (imgUrl != null) {
            	//If the image URL is valid, it creates an ImageIcon and sets it as the weather icon.
                ImageIcon weatherIconImg = new ImageIcon(new ImageIcon(imgUrl).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH));
                weatherIconLabel.setIcon(weatherIconImg);
            } else {
            	//Otherwise, it displays “Icon not found.”
                weatherIconLabel.setText("Icon not found");
            }

            // This method update GUI with current weather and forecast information
            // It sets the txtWeatherDisplay component’s text to 
            //  include both currentWeatherText and forecastText.
            txtWeatherDisplay.setText(currentWeatherText + forecastText.toString());
        } catch (JSONException e) {
        	// If any exception occurs during parsing, it displays an error message
            e.printStackTrace();
            txtWeatherDisplay.setText("Error processing weather data.");
        }
    }

    // This method maps a weather description to an appropriate icon name.
    // Example “clear,” “cloudy,” “rain,” etc.) 
    private String getWeatherIcon(String description) {
        String icon;
        description = description.toLowerCase();
        if (description.contains("clear")) {
            icon = "sun";
        } else if (description.contains("cloud")) {
            icon = "cloud";
        } else if (description.contains("rain") || description.contains("drizzle")) {
            icon = "rain";
        } else if (description.contains("thunderstorm")) {
            icon = "storm";
        } else if (description.contains("snow")) {
            icon = "snow";
        } else {
            icon = "default";
        }
        return icon;
    }

    // This method updates the search history with the current location and timestamp
    private void updateSearchHistory(String location) {
    	
    	//It constructs an entry string by combining the location and the formatted timestamp
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String timestamp = formatter.format(date);
        String entry = location + " - " + timestamp;
        searchHistory.add(entry); // The entry is then added to the searchHistory.
        updateHistoryTextArea(); //  It t calls updateHistoryTextArea() to refresh the display of search history.
    }

    // This method constructs a formatted text representation of the search history.
    private void updateHistoryTextArea() {
        StringBuilder historyText = new StringBuilder();
        
     // It iterates through each entry in searchHistory and appends it to the historyText. 
        for (String entry : searchHistory) {
            historyText.append(entry).append("\n");
        }
        historyTextArea.setText(historyText.toString()); // Update historyTextArea
    }

    // This method dynamically sets the background image based on the time of day.
    private void updateBackground() {
    	
    // It extracts the current hour using SimpleDateFormat and Date.
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        Date date = new Date();
        int hour = Integer.parseInt(formatter.format(date));
        
        // Depending on the hour, it selects an appropriate background image path (backgroundPath).
        String backgroundPath;
        
     // If the image URL is valid, it creates an ImageIcon 
        if (hour >= 6 && hour < 12) {
            backgroundPath = "/backgrounds/morning.png";
        } else if (hour >= 12 && hour < 18) {
            backgroundPath = "/backgrounds/afternoon.png";
        } else if (hour >= 18 && hour < 21) {
            backgroundPath = "/backgrounds/evening.png";
        } else {
            backgroundPath = "/backgrounds/night.png";
        }

        // and adds it as the background label to the content pane.
        java.net.URL backgroundUrl = getClass().getResource(backgroundPath);
        
        // If the image URL is not found, it prints an error message.
        if (backgroundUrl != null) {
            ImageIcon backgroundIcon = new ImageIcon(new ImageIcon(backgroundUrl).getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
            JLabel backgroundLabel = new JLabel(backgroundIcon);
            backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
            contentPane.add(backgroundLabel); // Add the background label to the content pane
            contentPane.setComponentZOrder(backgroundLabel, 0); // Set background label as the bottom-most component
        } else {
            System.out.println("Background image not found.");
        }
    }
}



