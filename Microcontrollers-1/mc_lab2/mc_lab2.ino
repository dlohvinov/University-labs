const int buttonFirst = 34;
const int buttonSecond = 32;
const float ledDelay = (0.55*1000);
// the setup function runs once when you press reset or power the board
void setup() {
  // initialize UART0
  Serial.begin(9600);
  pinMode(buttonFirst, INPUT_PULLUP);
  pinMode(buttonSecond, INPUT_PULLUP);
  DDRK = 0xFF;
  PORTK = 0;
}

// the loop function runs over and over again forever
void loop() {
  // read from port 0:
  if (Serial.available()) {
    int inByte = Serial.read();
    if (inByte == 0xA1) {
      PORTK = B00011000;
      delay(ledDelay);
     PORTK = B00100100;
      delay(ledDelay);
      PORTK = B01000010;
      delay(ledDelay);
      PORTK = B10000001;
      delay(ledDelay);
    } else if (inByte == 0xB1) {
       PORTK = B0000001;
      delay(ledDelay);
     PORTK = B00000100;
      delay(ledDelay);
     PORTK = B00010000;
      delay(ledDelay);
     PORTK = B01000000;
      delay(ledDelay);
      PORTK = B00000010;
      delay(ledDelay);
      PORTK = B00001000;
      delay(ledDelay);
      PORTK = B0010000;
      delay(ledDelay);
      PORTK = B01000000;
      delay(ledDelay);
    }
  }


  // write to port 0:
  if(digitalRead(buttonFirst)==LOW){
    Serial.write(0xA1);
    delay(200);
  }
  
  if(digitalRead(buttonSecond)==LOW){
    Serial.write(0xB1);
    delay(200);
  }
}
