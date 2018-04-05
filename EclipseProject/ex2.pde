import javax.swing.JOptionPane;

int FREEZE_C = 0;
int BOIL_C = 100;
int FREEZE_F = 32;
int BOIL_F = 212;

int temp;
String scale;

String answer = JOptionPane.showInputDialog("Enter the temperature in degrees: ");
temp = Integer.parseInt(answer);
scale = JOptionPane.showInputDialog("Enter C for Celsius or F for Fahrenheit: "); 


if (scale.equals("C"))
{
  if (temp <= FREEZE_C)
    println("Solid");
  else if (temp>= BOIL_C)
    println( "Gas" );
  else
    println("Liquid");
}

if (scale.equals("F"))
{
  if (temp <= FREEZE_F)
    println("Solid");
  else if (temp>= BOIL_F)
    println( "Gas" );
  else
    println("Liquid");
}