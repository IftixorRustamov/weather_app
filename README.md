# Weather App

This repository contains the source code for a simple weather application designed for Android. It allows users to search for a city and view current weather information.

## Features

*   **City Search:** Users can search for cities using the search bar.
*   **Weather Display:** Displays the current temperature, weather condition, local time, latitude, humidity, and wind speed for the selected city.
*   **Visual Representation:** Uses icons to visually represent the current weather conditions (e.g., sunny, partly cloudy).
*   **Error Handling:** Provides error messages and retry functionality if weather data fails to load for a city.

## Screenshots

### Tashkent
<img src="app/src/main/res/tashkent.png" alt="Alt text" width="250" style="display: inline-block;" />

### London
<img src="app/src/main/res/london.png" alt="Alt text" width="250" style="display: inline-block;" />

### Error
<img src="app/src/main/res/onerror.png" alt="Alt text" width="250" style="display: inline-block;" />


## Technologies Used

*   **Android Development:** Developed using Android SDK.
*   **Kotlin:** Written using the Kotlin programming language.
*  **Retrofit:** Used for making HTTP network requests to fetch weather data from an API.
*   **GPS:** Utilized for determining the user's current location to fetch weather information.
  
## Setup Instructions

1.  **Clone the repository:**
    ```bash
    git clone [repository URL]
    ```
2.  **Open with Android Studio:** Open the project in Android Studio.
3.  **Build and Run:** Build and run the application on an emulator or a physical Android device.

## Future Enhancements

*   **Daily/Hourly Forecast:** Display daily/hourly weather forecasts for the selected city.
*   **Location Services:** Implement location-based weather fetching.
*   **Improved UI/UX:** Enhance the user interface for better user experience.
*   **Settings:** Allow users to configure settings like units of measurement.
*   **More Weather Conditions:** Add more images to cover more conditions.

## Contributing

Contributions are welcome! If you'd like to contribute to this project, please follow these steps:

1.  Fork the repository.
2.  Create a new branch for your feature or bug fix.
3.  Make your changes and commit them.
4.  Push your branch to your forked repository.
5.  Submit a pull request.
