**Weather Information App**

***Introduction***
This Java-based Weather Information App is a simple GUI application that allows users to retrieve current weather data and short-term forecasts for a specified location based on the user's location input. It utilizes the OpenWeatherMap API to fetch weather information and presents it in a graphical user interface (GUI) format. Users can input a city name or coordinates, and the app displays relevant weather information such as temperature, humidity, wind speed, and conditions. Additionally, it features icon representation, unit conversion, error handling, and dynamic backgrounds. It also tracks and displays a history of searched locations.

***Features***
•	*Current Weather*: Display current weather conditions including temperature, humidity, wind speed, and description.
•	*Short-term Forecast*: Show forecast data for the next two time intervals.
•	*Search History Tracking*: Keep track of previously searched locations and timestamps.
•	*Dynamic Background*: Automatically change the background image based on the time of day.
•	*Temperature and Wind Speed Units*: Allow users to choose between Celsius/Fahrenheit and km/h/m/s respectively.

***Requirements***
•	Java Development Kit (JDK) 8 or higher
•	Internet connection (for fetching weather data from the API)

***Installation***
•	Download and install the JDK from the Oracle website.
•	Clone or download the repository to your local machine.
•	Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).

***Configuration***
•	Obtain an API key from OpenWeatherMap.
•	Replace the API_KEY value in the WeatherApp class with your OpenWeatherMap API key.
•	Compile and run the WeatherApp.java file.

***Usage***
1.	*Compiler*: Compile and run the WeatherApp class in Java application.
2.	*Enter Location*: Type the desired location in the text field labeled "ENTER YOUR LOCATION."
3.	*Select Unit Conversion*: Choose the preferred temperature and wind speed units from the respective dropdown menus.
4.	*Get Weather*: Click the "GET WEATHER" button to fetch weather information for the specified location.
5.	*View Results*: The weather information and forecast will be displayed in the main window. The weather icon will change accordingly.
6.	*Search History*: The search history will be updated with each new search, showing the location and timestamp.

**IMPLEMENTATION DETAILS**
•	*GUI Components*: The app utilizes Swing components for building the graphical interface.
•	*API Integration*: It interacts with the OpenWeatherMap API to fetch weather data using HTTP requests.
•	*Background Image*: The background image changes dynamically based on the time of day.
•	*Data Parsing*: JSON data returned by the API is parsed to extract relevant weather information.
•	*Multithreading*: Network operations are performed in separate threads to prevent blocking the UI thread.
•	*Error Handling*: Various exceptions, such as network errors or JSON parsing errors, are handled gracefully.

***1. API Integration***
Utilizing a Weather API (OpenWeatherMap) to Fetch Real-Time Weather Data
Implementation Steps:
•	Integrate with the OpenWeatherMap to obtain an API , so as to  fetch real-time weather data. (Sign up on their website).
•	Construct API URLs to fetch weather data for a given location.
•	Use Java's HttpURLConnection or a third-party library like HttpClient to make HTTP GET requests to the API using the user-specified location.

***2. GUI Design***
a) Designing a User-Friendly GUI Using Java Swing
Implementation Steps:
•	Create a Swing-based GUI.
•	Components:
o	JFrame as the main window
o	JPanel for organizing components
o	JTextField for user location input
o	JLabel for displaying weather information
o	JComboBox for unit selection.
o	JTextArea for search history.
o	JButton to trigger weather data fetch
•	Use layout managers (e.g., FlowLayout, BorderLayout)
b.) Including Components for Users to Input the Location
Implementation Steps:
•	Create a JavaFX-based graphical user interface (GUI).
•	Components:
•	Use a JTextField for the location input.
•	Labels to display weather information
•	Buttons for actions (e.g., fetch weather)
•	Organize components using layouts (e.g., VBox, HBox).
•	Apply CSS styling for visual appeal.
•	Validate the input to ensure it's not empty before making the API request

***3. Displaying Weather Information***
Displaying Relevant Weather Information
Implementation Steps:
•	Retrieve weather data from the API.
•	Parse the JSON response from the API to extract temperature, humidity, wind speed, and weather conditions.
•	Update the JLabel components or text fields dynamically with relevant weather details.
•	Example: “Temperature: 25°C, Humidity: 70%, Wind Speed: 5 m/s, Conditions: Cloudy”

***4. Icon Representation***
Using Appropriate Icons or Images
Implementation Steps:
•	 Map weather conditions (e.g., “clear sky,” “clouds”) to corresponding icons (e.g., sun, clouds) to icon file names.
•	Use JLabel with ImageIcon to display the appropriate weather icon.
•	Use appropriate icons or images to visually represent weather conditions.

***5. Forecast Display***
Implementing a Section to Display Short-Term Weather Forecast
Implementation Steps:
•	Fetch forecast data from the OpenWeatherMap API (e.g., hourly or daily forecasts).
•	Implement a section to display a short-term weather forecast.
•	Parse and display the forecast information in a designated section.
•	Display relevant information (e.g., temperature trends, precipitation).

***6. Unit Conversion***
Option to Switch Between Different Units for Temperature and Wind Speed
Implementation Steps:
•	Use JComboBox to select temperature and wind speed units.
•	Convert values based on the selected units before displaying.
•	Provide an option for users to switch between different units:
o	Temperature: Celsius ↔ Fahrenheit
o	Wind speed: m/s ↔ mph

***7. Error Handling***
 Proper Error Handling for Invalid Location or Failed API Requests 
•	Handle scenarios like invalid location input or failed API requests.
•	Display user-friendly error messages (e.g., “Invalid location. Please try again.”)

***8. History Tracking***
 Allow Users to View a History of Their Recent Weather Searches with Timestamps
Implementation Steps:
•	Maintain a history of recent weather search queries with timestamps.
•	Store timestamps along with location queries
•	Update the list each time a new search is performed.
•	Display the history to users in a JTextArea or similar component.

***9. Dynamic Backgrounds***
 Implementation of Dynamic Background Changes Based on the Time of Day
Implementation Steps:
•	Determine the current time of day.
•	Change the background image or color based on the time period (morning, afternoon, evening, night).
•	Change the app’s background image or color based on the time of day:
o	Morning: Bright background (e.g., blue sky)
o	Afternoon (e.g. sky with partial cloud)
o	Evening: Sunset or twilight background
o	Night: Dark background
•	Use JPanel background color or set an image as the background.
________________________________________
**Summary of Steps for Full Implementation**
***1.	API Integration:***
o	Get API key and integrate with OpenWeatherMap API.
o	Fetch weather data and parse JSON responses.
***2. GUI Design:***
o	Create the main frame and layout with Java Swing.
o	Include components for user input and displaying weather data.
***3. Displaying Weather Information:***
o	Extract and display temperature, humidity, wind speed, and weather conditions.
***4.	Icon Representation:***
o	Map weather conditions to icons and display them appropriately.
***5.	Forecast Display:***
o	Fetch and display short-term forecasts.
***6.	Unit Conversion:***
o	Implement unit conversion for temperature and wind speed.
***7.	Error Handling:***
o	Handle invalid inputs and failed API requests gracefully.
***8.	History Tracking:***
o	Maintain and display a list of recent searches with timestamps.
***9.	Dynamic Backgrounds:***
o	Change the application's background based on the time of day.
________________________________________
**REFERENCES**
**1.	OpenWeatherMap API Documentation:**
o	OpenWeatherMap API
o	Current Weather Data API
o	Forecast 5 days/3 hours API
•	OpenWeatherMap. (n.d.). Current weather data. Retrieved from https://openweathermap.org/current
•	OpenWeatherMap. (n.d.). 5 day / 3 hour forecast. Retrieved from https://openweathermap.org/forecast5
**2.	Java Swing Documentation:**
o	Java Swing Tutorial
o	JTextField Class
o	JButton Class
o	JLabel Class
•	Oracle. (n.d.). Java Swing tutorial. Retrieved from https://docs.oracle.com/javase/tutorial/uiswing/
•	Oracle. (n.d.). JTextField (Java Platform SE 7). Retrieved from https://docs.oracle.com/javase/7/docs/api/javax/swing/JTextField.html
•	Oracle. (n.d.). JButton (Java Platform SE 7). Retrieved from https://docs.oracle.com/javase/7/docs/api/javax/swing/JButton.html
•	Oracle. (n.d.). JLabel (Java Platform SE 7). Retrieved from https://docs.oracle.com/javase/7/docs/api/javax/swing/JLabel.html

**3.	Java Networking and JSON Parsing:**
o	HttpURLConnection
o	JSON in Java (org.json)
o	Using Java HttpClient
•	Oracle. (n.d.). HttpURLConnection (Java Platform SE 8). Retrieved from https://docs.oracle.com/javase/8/docs/api/java/net/HttpURLConnection.html
•	Stleary, S. (n.d.). JSON in Java (org.json). Retrieved from https://stleary.github.io/JSON-java/
•	OpenJDK. (n.d.). Using Java HttpClient. Retrieved from https://openjdk.java.net/groups/net/httpclient/intro.html

**Acknowledgments**
This project utilizes the following resources:
•	OpenWeatherMap API: https://openweathermap.org/
•	IconArchive for weather icons: https://www.iconarchive.com/
