# FetchFood 🐶🍖

**FetchFood** is a mobile application designed for dog owners to easily browse, explore, and purchase high-quality dog food and related products. The app provides a seamless shopping experience, personalized product recommendations, and helpful insights on dog nutrition.

## 📱 Features

- **Product Recommendations**: Users can browse curated dog food products based on their preferences and needs.
- **Explore Products**: A dedicated section to explore various categories of dog food and related products.
- **Cart and Checkout**: A smooth cart experience for adding items and completing purchases.
- **User Profile and Orders**: Users can view their profile, manage their orders, and track delivery status.
- **Latest News**: Provides updates on the latest trends in dog nutrition and health.
- **Firebase Integration**: Uses Firebase Firestore for product data storage and user management.

## 🔧 Tech Stack

- **Frontend**: Kotlin, Android SDK
- **Backend**: Firebase Firestore for real-time database
- **Design**: Material Design for consistent UI/UX
- **Tools**: Android Studio, Git, GitHub



## 🚀 Getting Started

### Prerequisites

- Android Studio installed (recommended version Arctic Fox or later)
- Firebase account and project set up for Firestore integration

### Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/yourusername/FetchFood.git
   cd FetchFood
   ```

2. **Open the project in Android Studio**:
   - Launch Android Studio.
   - Select "Open an existing project" and choose the `FetchFood` directory.

3. **Set up Firebase**:
   - Add your `google-services.json` file to the `app/` directory.
   - Ensure Firebase Firestore is enabled in your Firebase console.

4. **Build the project**:
   - Click on "Build" > "Rebuild Project" in Android Studio.
   - Run the app on an emulator or a physical device.

## 🛠 Configuration

Ensure you have added your Firebase configuration file (`google-services.json`) to the `app` folder and updated your `build.gradle` with Firebase dependencies:

```gradle
implementation platform('com.google.firebase:firebase-bom:32.0.0')
implementation 'com.google.firebase:firebase-firestore-ktx'
```

## 📋 Features Breakdown

- **Dashboard**: Displays a personalized feed of recommended products.
- **Explore**: Allows users to browse different categories of dog food (e.g., dry food, wet food, grain-free).
- **Cart**: Users can add products to the cart and proceed to checkout.
- **User Profile**: Users can view their profile details and order history.
- **News Section**: Provides the latest updates on dog nutrition and trends in the industry.

## 🐞 Known Issues

- **Network Connectivity**: The app requires a stable internet connection to fetch data from Firebase Firestore.
- **UI Adjustments**: Minor UI issues may occur on devices with different screen sizes.

## 🤝 Contributing

We welcome contributions! If you want to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch:

   ```bash
   git checkout -b feature/your-feature-name
   ```

3. Make your changes and commit them:

   ```bash
   git commit -m 'Add some feature'
   ```

4. Push your changes:

   ```bash
   git push origin feature/your-feature-name
   ```

5. Open a Pull Request.

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 💬 Contact

For any questions or feedback, please reach out to:

- **Dwayne De Hoedt**: [Your Email](mailto:dwaynedehoedt@gmail.com)
- GitHub: [yourusername](https://github.com/rdwaynedehoedt)
```
