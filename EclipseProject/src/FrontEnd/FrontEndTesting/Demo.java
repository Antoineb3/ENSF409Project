package FrontEnd.FrontEndTesting;


public class Demo{

    public static void main(String[] args) {
        ProfGUI pg = new ProfGUI();
        ProfController controller = new ProfController(pg);
        pg.setActiveCard("VIEWSTUDENTS");
        
    }
}