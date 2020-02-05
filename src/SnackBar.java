import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SnackBar extends JFrame
        implements ActionListener
{
    private static final String MY_PASSWORD = "jinx";
    private VendingMachine machine1, machine2, machine3;

    public SnackBar()
    {
        super("Snack Bar");

        Color brandColor1 = new Color(43, 239, 63, 26); // r, g, b
        Color brandColor2 = new Color(255, 180, 255, 79);
        Color brandColor3 = new Color(254, 103, 26, 81);

        // Load the coin icon for the vending machine buttons:
        ImageIcon coin = new ImageIcon("coin.gif");

        machine1 = new VendingMachine("Mist", brandColor1, 60, coin);
        machine2 = new VendingMachine("Moonlight", brandColor2, 100, coin);
        machine3 = new VendingMachine("Ether", brandColor3, 40, coin);

        Box wall = Box.createHorizontalBox();
        wall.add(Box.createHorizontalStrut(5));
        wall.add(machine1);
        wall.add(Box.createHorizontalStrut(5));
        wall.add(machine2);
        wall.add(Box.createHorizontalStrut(5));
        wall.add(machine3);
        wall.add(Box.createHorizontalStrut(5));

        JPanel service = new JPanel();
        service.add(new JLabel(" Service login: "));
        JPasswordField password = new JPasswordField("", 5);
        password.addActionListener(this);
        service.add(password);

        Container c = getContentPane();
        c.setBackground(Color.PINK);
        c.add(wall, BorderLayout.CENTER);
        c.add(service, BorderLayout.SOUTH);
    }

    /**
     *  Password field: user strikes <Enter>
     */
    public void actionPerformed(ActionEvent e)
    {
        JPasswordField password = (JPasswordField)e.getSource();
        String word = new String(password.getPassword());
        password.setText("");
        if (MY_PASSWORD.equals(word))
        {
            double amt = Vendor.getTotalSales();
            machine1.reload();
            machine2.reload();
            machine3.reload();
            JOptionPane.showMessageDialog(null,
                    String.format("Total sales: $%.2f\n", amt) +
                    "Machines reloaded",
                    "Service", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null,
                    "Login failed", "Service", JOptionPane.ERROR_MESSAGE);
        }
    }

    // *****************************************************

    public static void main(String[] args)
    {
        SnackBar window = new SnackBar();
        window.setBounds(50, 50, 520, 310);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }
}
