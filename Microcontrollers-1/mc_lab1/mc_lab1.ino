const int button = 34;
const float ledDelay = (0.55*1000);
// the setup function runs once when you press reset or power the board
void setup() {
  pinMode(button, INPUT_PULLUP);
  DDRK = 0xFF;
  PORTK = 0;
}

// the loop function runs over and over again forever
void loop() {
  if(digitalRead(button) == LOW) {
    PORTK = B00011000;
    delay(ledDelay);
    PORTK = B00100100;
    delay(ledDelay);
    PORTK = B01000010;
    delay(ledDelay);
    PORTK = B10000001;
    delay(ledDelay);
    PORTK = B00000000;
  }
}
