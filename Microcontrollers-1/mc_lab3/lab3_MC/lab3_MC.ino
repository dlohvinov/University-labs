#include <LiquidCrystal.h>

#define DDR_KEYPAD  DDRC
#define PORT_KEYPAD PORTC
#define PIN_KEYPAD  PINC
#include "keypad4x4.h"

const int buzzerPin = 21;

// initialize the library by associating any needed LCD interface pin
// with the arduino pin number it is connected to

const int rs = 49, rw = 48, en = 47, d4 = 46, d5 = 45, d6 = 44, d7 = 43;
LiquidCrystal lcd(rs, rw, en, d4, d5, d6, d7);

const PROGMEM  char sixty[60][3] = {
  {"00"}, {"01"}, {"02"}, {"03"}, {"04"}, {"05"}, {"06"}, {"07"}, {"08"}, {"09"},
  {"10"}, {"11"}, {"12"}, {"13"}, {"14"}, {"15"}, {"16"}, {"17"}, {"18"}, {"19"},
  {"20"}, {"21"}, {"22"}, {"23"}, {"24"}, {"25"}, {"26"}, {"27"}, {"28"}, {"29"},
  {"30"}, {"31"}, {"32"}, {"33"}, {"34"}, {"35"}, {"36"}, {"37"}, {"38"}, {"39"},
  {"40"}, {"41"}, {"42"}, {"43"}, {"44"}, {"45"}, {"46"}, {"47"}, {"48"}, {"49"},
  {"50"}, {"51"}, {"52"}, {"53"}, {"54"}, {"55"}, {"56"}, {"57"}, {"58"}, {"59"}
};

struct Time
{
  unsigned char second, minute, hour;
};

Time T1 = {20, 1, 0};
Time T2 = {10, 1, 0};
char buffer1;
char buffer2;
boolean allowTimer1 = true;
boolean allowTimer2 = true;
boolean isRinged1 = false;
boolean isRinged2 = false;

void LCD_WriteStrPROGMEM(char *str, int n)  //вивід масиву символів,
{ //записаних у флеші
  for (int i = 0; i < n; i++)
    lcd.print( (char)pgm_read_byte( &(str[i]) ) );
}

ISR(TIMER1_COMPA_vect)  // Таймер Т1 по співпадінню А, кожної 1 сек.
{
 if(allowTimer2){
        if (T2.hour == 0 && T2.minute == 0 && (T2.second - 1) == 0){
              T2 = {0,0,0};
              allowTimer2 = false;
        } else {
              if (--T2.second == 0){
                T2.second = 59;
                  if (--T2.minute == 0){
                    if (T2.hour != 0){
                      T2.minute = 59;
                    }
                    else T2.minute = 0;
                      if ((T2.hour - 1) == 0) {
                          T2.hour = 0;
                      }
                  }
              }
        }
 }

 
 if(allowTimer1) {
      if (T1.hour == 0 && T1.minute == 0 && (T1.second - 1) == 0){
              T1 = {0,0,0};
              allowTimer1 = false;
        } else {

              if (--T1.second == 0){
                T1.second = 59;
                  if (--T1.minute == 0){
                    if (T1.hour != 0){
                      T1.minute = 59;
                    }
                    else T1.minute = 0;
                      if ((T1.hour - 1) == 0) {
                          T1.hour = 0;
                         }
                      }
                }
          
          }
      }      
  

  lcd.setCursor(5, 1);
  LCD_WriteStrPROGMEM(sixty[T2.hour], 2);
  lcd.write(':');
  LCD_WriteStrPROGMEM(sixty[T2.minute], 2);
  lcd.write(':');
  LCD_WriteStrPROGMEM(sixty[T2.second], 2);

  lcd.setCursor(5, 0);
  LCD_WriteStrPROGMEM(sixty[T1.hour], 2);
  lcd.write(':');
  LCD_WriteStrPROGMEM(sixty[T1.minute], 2);
  lcd.write(':');
  LCD_WriteStrPROGMEM(sixty[T1.second], 2);
}

void setup() {
  noInterrupts();           // disable all interrupts

  // Таймер#1: Скид при співпадінні OCR1A (1sec) + дільник=256
  TCCR1A = 0x00;
  TCCR1B = (1 << WGM12) | (1 << CS12) | (1 << CS10); //CTC mode & Prescaler @ 1024
  TIMSK1 = (1 << OCIE1A); // дозвіл на переривання по співпадінню
  OCR1A = 0x3D08;// compare value = 1 sec (16MHz AVR)
  
  //KeyPad 4x4
  initKeyPad();

  //LCD 16x2
  // set up the LCD's number of columns and rows:
  lcd.begin(16, 2);
  interrupts();  // Enable global interrupts

  //Buzzer
  pinMode(buzzerPin, OUTPUT);
  digitalWrite(buzzerPin, LOW);
}

void loop() {
    if(T1.hour == 0 && T1.minute == 0 && T1.second == 0 && !isRinged1) {
//            T1 = {0,0,0};   
            isRinged1 = true;
            digitalWrite(buzzerPin, HIGH);
            delay(100);
            digitalWrite(buzzerPin, LOW);
      }   
    if(T2.hour == 0 && T2.minute == 0 && T2.second == 0 && !isRinged2) {
//            T2 = {0,0,0};
            isRinged2 = true;
            digitalWrite(buzzerPin, HIGH);
            delay(100);
            digitalWrite(buzzerPin, LOW);
      }   

    if ( isButtonPressed() ) {
      lcd.setCursor(1, 1);
      lcd.write(readKeyFromPad4x4());
      if(isButtonPressed()|| readKeyFromPad4x4() == 'A') {
          buffer1, buffer2 = 0;
          for(;;) {
          if(isButtonPressed()) {
            buffer1 = readKeyFromPad4x4();
            for(;;) {
                if (isButtonPressed()&& readKeyFromPad4x4() != 'E') {
                buffer2 = readKeyFromPad4x4();
                for(;;) {           
                  if(isButtonPressed() || readKeyFromPad4x4() == 'E') {
                      int minutes = (int) buffer1 - 48;
                      minutes *= 10;
                      minutes += (int) buffer2 - 48;
                      T1.minute =  minutes;
                      allowTimer1 = false;
                      break;
                    }
                  }
                  break;
                }
              }
              break;
            }
          }
    }

    else if(isButtonPressed()|| readKeyFromPad4x4() == 'B'){
        buffer1, buffer2 = 0;
          for(;;) {
          if(isButtonPressed()) {
            buffer1 = readKeyFromPad4x4();
            for(;;) {
                if (isButtonPressed()&& readKeyFromPad4x4() != 'E') {
                buffer2 = readKeyFromPad4x4();
                for(;;) {           
                  if(isButtonPressed() || readKeyFromPad4x4() == 'E') {
                      int minutes = (int) buffer1 - 48;
                      minutes *= 10;
                      minutes += (int) buffer2 - 48;
                      T2.minute =  minutes;
                      allowTimer2 = false;
                      break;
                    }
                  }
                  break;
                }
              }
              break;
            }
          }
      }
      else if(isButtonPressed()|| readKeyFromPad4x4() == 'C'){
          if(allowTimer1) {
              allowTimer1 = false;
              T1 = {0,0,0};
            }
            else {
                allowTimer1 = true;
              }
        }
        else if(isButtonPressed()|| readKeyFromPad4x4() == 'D') {
             if(allowTimer2) {
            allowTimer2 = false;
              T2 = {0,0,0};
            }
            else {
                allowTimer2 = true;
              }
          }
        }
}
