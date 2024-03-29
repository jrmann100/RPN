import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class UI {
    public static void main(String args[]) {
        final Interpreter interp = new Interpreter(5);
        Color black = new Color(44, 44, 46);
        Color white = Color.white;
        JFrame frame = new JFrame("RPN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 155);
        frame.getContentPane().setBackground(black);
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, black));

        final JLabel label = new JLabel("<html></html>");
        label.setBackground(black);
        label.setForeground(white);
        final JTextArea ta = new JTextArea();
        ta.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n'){
                    try {
						interp.cmd(ta.getText().replace("\n",""));
					} catch (RPNStackOverflowException | RPNStackUnderflowException exception) {
						JOptionPane.showMessageDialog(null, "Stack exception: " + exception.getMessage());
					}  catch (IllegalArgumentException exception) {
						JOptionPane.showMessageDialog(null, "Interpreter exception: " + exception.getMessage());
					}
                    ta.setText("");
                    label.setText("<html>"+interp.toString().replace("\n", "<br>")+"</html>");
                };
            }
            public void keyReleased(KeyEvent e) {
            }
            public void keyPressed(KeyEvent e) {
            }
        });
        ta.setBackground(black);
        ta.setForeground(white);
        ta.setCaretColor(white);
        frame.getContentPane().add(BorderLayout.SOUTH, ta);
        frame.getContentPane().add(BorderLayout.NORTH, label);

        frame.setVisible(true);
    }
}