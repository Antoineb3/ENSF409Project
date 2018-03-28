package FrontEnd.FrontEndTesting;

/*
TODO make methods addHorizontalLine and addJLabel have a bigger scope bc right now they are re-implemented in different classes.
*/


public class Demo{

    public static void main(String[] args) {
        ProfGUI pg = new ProfGUI();
        ProfController controller = new ProfController(pg);
        pg.setActiveCard("VIEWSTUDENTS");
        
    }
}