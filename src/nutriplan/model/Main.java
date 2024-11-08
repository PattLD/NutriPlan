package nutriplan.model;

import nutriplan.dao.ConnectionDAO;
import nutriplan.view.*;

public class Main {
    public static void main(String[] args) {

        ConnectionDAO.getConnection();

        MainFrame mainFrame = new MainFrame();

    }
}