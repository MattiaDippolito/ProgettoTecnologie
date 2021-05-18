#define Joystick_Y A0
#define Joystick_X A1
#define Joystick_Click 2


void setup() {
  Serial.begin(9600);

  pinMode(A0, INPUT);
  pinMode(A1, INPUT);
  pinMode(2, INPUT);
  delay(1000);
}

void loop() 
{
  int x = analogRead(Joystick_X);
  int y = analogRead(Joystick_Y);
  int button = digitalRead(Joystick_Click);
  if(x<505){
    Serial.print('k');
  }
  else if(x>513){
    Serial.print('i');
  }
  else if(x>=505&&x<=513&&y<513)
  {
    Serial.print('c');
  }
  if(y>513)
  {
    Serial.print('m');
  }

  
  

  
  delay(100);
}
