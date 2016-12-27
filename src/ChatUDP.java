import javax.swing.*;
import javax.swing.tree.ExpandVetoException;
import java.awt.*;

/**
 * Created by Denis on 27.12.2016.
 */
public class ChatUDP extends JFrame{
    private JTextArea taMain;
    private JTextField tfMsg;

    private final String FRM_TITLE = "Our Tiny Chat";
    private final int FRM_LOC_X = 100;
    private final int FRM_LOC_Y = 100;
    private final int FRM_WIDTH = 400;
    private final int FRM_HEIGTH = 400;

    private final int PORT = 9876;
    private final String IP_BROADCAST = "192.168.30.255"; // InetAdress.getLocalHost()


    private class thdReceiver extends Thread {
        @Override
        public void start(){
            super.start();
            System.out.println("hello from thread");
        }
    }

    private void btnSend_Handler() throws Exception{

    }

    private void frameDraw(JFrame frame){
        tfMsg = new JTextField();
        taMain = new JTextArea(FRM_HEIGTH/19, 50);
        JScrollPane spMain = new JScrollPane(taMain);
        spMain.setLocation(0,0);
        taMain.setLineWrap(true);
        taMain.setEditable(false);

        JButton btnSend = new JButton();
        btnSend.setText("Send");
        btnSend.setToolTipText("Broadcast a message");
        btnSend.addActionListener(e -> {
            try {
                btnSend_Handler();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(FRM_TITLE);
        frame.setLocation(FRM_LOC_X, FRM_LOC_Y);
        frame.setSize(FRM_WIDTH, FRM_HEIGTH);
        frame.setResizable(false);
        frame.getContentPane().add(BorderLayout.NORTH, spMain);
        frame.getContentPane().add(BorderLayout.CENTER, tfMsg);
        frame.getContentPane().add(BorderLayout.EAST, btnSend);
        frame.setVisible(true);
    }

    private void antistatic(){
        frameDraw(new ChatUDP());
        new thdReceiver().start();
    }

    public static void main(String[] args) {
        new ChatUDP().antistatic();
    }
}
