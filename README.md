Travelpayouts Travel App For Android
=================

Travelpayouts Travel App is a application using flight and hotel search engines. When your customer books a flight or hotel, we pay you a [commission fee](https://www.travelpayouts.com). Framework is based on leading flight search engines [Aviasales](http://www.aviasales.ru), [JetRadar](http://www.jetradar.com) and hotel search engine [Hotellook](http://www.hotellook.ru).

Travelpayouts Travel App supports all Android devices with Android API 21 and higher.

<br>To get your API key, track statistics and payments please sign up to [Travelpayouts Travel Affiliate Network](https://www.travelpayouts.com/?utm_source=github&utm_medium=android_sdk).
<br>Learn more about earnings in [Travelpayouts FAQ](https://support.travelpayouts.com/hc/en-us/articles/203955613-Commission-and-payments).
<br>Video [instruction](https://www.youtube.com/watch?v=dQw4w9WgXcQ) 

## Example of creating TravelPayouts App

### Privacy Policy

Privacy Policy is **REQUIRED** for your app. If it is missing, your app may be removed from the Google Play at anytime.  
We've included a default Privacy Policy in the template app, but you should create YOUR OWN.  
(you can use online services like [privacypolicytemplate.net](https://privacypolicytemplate.net) or [App Privacy Policy Generator](https://app-privacy-policy-generator.firebaseapp.com)).  
NEXT STEPS:
1) Upload privacy policy to your website and copy a link to it.
2) Use this link for a Privacy Policy field in Google Play Console.
3) Put policy link to `strings.xml` file.
```xml
   <string name="com_travelpayouts_privacy_policy" tools:ignore="MissingTranslation">Put policy link here</string>
```

### Sdk initialization

Change `com_travelpayouts_marker` and `com_travelpayouts_api_token` in `strings.xml` file to your marker and api token params. You can find them at [Travelpayouts.com](https://www.travelpayouts.com/developers/api).

```xml
  <string name="com_travelpayouts_marker" tools:ignore="MissingTranslation">put marker here</string>
  <string name="com_travelpayouts_api_token" tools:ignore="MissingTranslation">put api token here</string>
```

### Application id

To publish your application on google play you must set unique application id. All you need to do is change applicationId in `build.gradle` file.

```groovy
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'

android {
  compileSdkVersion versions.compileSdk
  
    defaultConfig {
        applicationId "com.example.myapp"
        
        minSdkVersion 21
        targetSdkVersion versions.targetSdk
        
        versionCode 1
        versionName "1.0"
        ...
    }
    ...
}
```

### Application name

Change application name in `strings.xml` file.

```xml
<string name="app_name" tools:ignore="MissingTranslation">Put appname here</string>
```

### Google maps initialization

Google maps is **necessary** for your app. To initialize google maps you need to:

1) Go to the Google [Cloud Platform Console](https://cloud.google.com/console/google/maps-apis/overview).
2) From the Project drop-down menu, select or create the project for which you want to add an API key.
3) From the  Navigation menu, select `APIs & Services > Credentials`.
4) On the `Credentials` page, click `Create credentials > API key`. 
   `The API key created` dialog displays your newly created API key (an encrypted string).
6) Copy your `API key` and Click `Close`. 
7) Put `API Key` to manifestPlaceholders in `build.gradle` file.
```groovy
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'

android {
    compileSdkVersion versions.compileSdk

    defaultConfig {
       ...
        manifestPlaceholders = [
            GOOGLE_API_KEY: "Put API key here"
        ]
        ...
    }
   ...
}
```

### App icons creation

Best way to create application icon is using Asset Studio tool. Steps:
1) In the `Project` window, select the Android view.
2) Right-click the `res` folder and select `New > Image Asset`.
3) Set up foreground and background layers.
`IMPORTANT!` Do not change icon name.
4) Click `Next > Finish`.

For more information – [Create app icons with Image Asset Studio](https://developer.android.com/studio/write/image-asset-studio)

### Firebase analytics integration

To track events you need to:
1) Create new or open existing firebase project in [firebase console](https://console.firebase.google.com/)
2) Add new android applicaton (Add app -> Android)
3) Download `google-services.json` and place it into the `app` folder
4) Uncomment `//apply plugin: 'com.google.gms.google-services'` in `app/build.gradle`

### Colors customization (OPTIONAL)
To customize colors of your app change `primary`, `primaryButton`, `positiveButton` and `primary_dark` in `colors.xml`. This is main TravelPayouts Travel App colors

```xml
  <color name="primary">#5A6D87</color>
  <color name="primary_dark">#475972</color>
  <color name="primaryButton">#F48C6B</color>
  <color name="positiveButton">#35C772</color>
```

### Crashlytics integration (OPTIONAL)

To receive crashes you need to:
1) Login to [Fabric](https://fabric.io/)
2) Copy `API Key` (Setting -> Organizatons -> `Your organization` -> API Key)
3) Open `app/build.gradle`
4) Paste your `API Key` to `FABRIC_API_KEY`. (Code should look like this: `FABRIC_API_KEY: "your_api_key"`)
5) Uncomment `//apply plugin: 'io.fabric'`
