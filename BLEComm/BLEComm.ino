#include <BLEDevice.h>
#include <BLEServer.h>

// Working sketch for BLE Communication!

BLEServer *pServer = NULL;
BLEService *pService = NULL;
BLECharacteristic pCharacteristic("beb5483e-36e1-4688-b7f5-ea07361b26a8", BLECharacteristic::PROPERTY_READ | BLECharacteristic::PROPERTY_NOTIFY);
BLEDescriptor pDescriptor("beb5483e-36e1-4688-c7f5-ea07361b26a9");

bool deviceConnected = false;

//Setup callbacks onConnect and onDisconnect
class MyServerCallbacks: public BLEServerCallbacks {
  void onConnect(BLEServer* pServer) {
    deviceConnected = true;
  };
  void onDisconnect(BLEServer* pServer) {
    deviceConnected = false;
  }
};

void setup() {
// Start serial communication 
  Serial.begin(115200);

  // create the BLE Device
  BLEDevice::init("My ESP32 Device");

  // create the BLE Server
  pServer = BLEDevice::createServer();
  pServer->setCallbacks(new MyServerCallbacks());

  // create the BLE Service
  pService = pServer->createService("4fafc201-1fb5-459e-8fcc-c5c9c331914b");

  // create a BLE Characteristic
  pService->addCharacteristic(&pCharacteristic);
  pDescriptor.setValue("Charakteristik ESP");
  pCharacteristic.addDescriptor(&pDescriptor);

  // add data to the characteristic
  pCharacteristic.setValue("Hello World!");

  // start the service
  pService->start();

  // start advertising
  BLEAdvertising *pAdvertising = BLEDevice::getAdvertising();
  pAdvertising->addServiceUUID(pService->getUUID());
  pAdvertising->start();
  pServer->getAdvertising()->start();
  Serial.println("Waiting a client connection to notify...");

  BLECharacteristic *test = pService->getCharacteristic("beb5483e-36e1-4688-b7f5-ea07361b26a8");
  Serial.println(pDescriptor.getUUID().toString().c_str());  
}

void loop() {
  if(deviceConnected){
    Serial.println("Connected to device");
    delay(2000);
    pCharacteristic.setValue("Lukas");    
    pCharacteristic.notify();
    
  }else{
    Serial.println("Disconnected");
    delay(5000);
  }
}
