  // Copyright 2015 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
// FirebaseDemo_ESP8266 is a sample that demo the different functions
// of the FirebaseArduino API.
#include <ESP8266WiFi.h>

#include <FirebaseArduino.h>
// Set these to run example.
#define FIREBASE_HOST "xxxxxxxxxxxxx.firebaseio.com"
#define FIREBASE_AUTH "xxxxxxxxxxxxxxxxxxxxxxx"
#define WIFI_SSID "xxxxxxxx"
#define WIFI_PASSWORD "xxxxxxx"

#include "DHT.h"        // including the library of DHT11 temperature and humidity sensor
#define DHTTYPE DHT11   // DHT 11

#define dht_dpin 14
DHT dht(dht_dpin, DHTTYPE); 
 



int sensorValue;



int led_pin =D2;
int sensor_pinm =A0;
int  motor_pin =D1;
int motor =D3;

int sensorThres = 850;



String moisturel;

String moistureh;

String moisture_levell;
String moisture_levelh;




void setup() {

Serial.begin(9600);
 dht.begin();
pinMode(led_pin, OUTPUT);
pinMode(sensor_pinm, INPUT);


pinMode(D0, OUTPUT);
pinMode(D5, OUTPUT);
pinMode(D1, OUTPUT);
pinMode(D3, OUTPUT);


// connect to wifi.
WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
Serial.print("connecting");
while (WiFi.status() != WL_CONNECTED) {
Serial.print(".");
delay(500);
}
Serial.println();
Serial.print("connected: ");
Serial.println(WiFi.localIP());



Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
Firebase.set("LED_STATUS", 0);
Firebase.set("MOTOR_STATUS", 0);





}




void loop() {





  //DHT11 
 float h = dht.readHumidity();
    float t = dht.readTemperature();         
    Serial.print("Current humidity = ");
    Serial.print(h);
    Serial.print("%  ");
    Serial.print("temperature = ");
    Serial.print(t); 
    Serial.println("C  ");
     Firebase.pushInt("\Tempereature_log", t);
 Firebase.set("\Temperature", t);
  Firebase.pushInt("\Humidity_log", h);
 Firebase.set("\Humidity", h );
  delay(800);


  

//Motor Control
int m = Firebase.getInt("MOTOR_STATUS");


if (m == 1){
  
Serial.println("MOTOR ON");
digitalWrite(D3,HIGH);  

}
else {
Serial.println("MOTOR OFF");
digitalWrite(D3,LOW);  
}





  

///Soil Moisture


moisturel = "Moisture content  low";
moistureh = "Moisture content high";
moisture_levell= "Low moisture content";
moisture_levelh= "High moisture content";

int p = analogRead(sensor_pinm);
Serial.println(p);


//for motor

if(analogRead(sensor_pinm) > sensorThres){
digitalWrite(D1, HIGH);
 Firebase.pushString("\moisture_level", moisture_levell);
 Firebase.set("\Moisture", "Moisture content  low" );
 delay(1000);
} else {
digitalWrite(D1, LOW);
  Firebase.pushString("\moisture_level", moisture_levelh);
  Firebase.set("\Moisture","Moisture content high" );
  delay(1000);

}

//for light indication

if(analogRead(sensor_pinm) > sensorThres){
digitalWrite(D2, HIGH);

} else {
digitalWrite(D2, LOW);
  

}
  





// LED

int n = Firebase.getInt("LED_STATUS");

if (n==1) {
Serial.println("LED ON");
digitalWrite(D0,HIGH);  


}
else {
Serial.println("LED OFF");
digitalWrite(D0,LOW);  
}


 }


