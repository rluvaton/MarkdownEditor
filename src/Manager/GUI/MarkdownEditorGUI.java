package Manager.GUI;

import Manager.MarkdownParser.MarkdownParser;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MarkdownEditorGUI
{
    private JPanel panel1;
    private JEditorPane editorPane1;
    private JTextPane textPane1;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("MarkdownEditorGUI");
        frame.setContentPane(new MarkdownEditorGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public MarkdownEditorGUI()
    {
        textPane1.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                super.keyTyped(e);

                editorPane1.setText(new MarkdownParser(textPane1.getText()).getTextFormat());
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                super.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                super.keyReleased(e);
            }
        });
    }
}
