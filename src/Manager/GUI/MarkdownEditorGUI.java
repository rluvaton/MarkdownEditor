package Manager.GUI;

import Manager.MarkdownParser.MarkdownParser;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MarkdownEditorGUI
{
    private JPanel panel1;
    private JEditorPane editorPane1;
    private JEditorPane editorPane2;
    private JTextPane textPane1;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("MarkdownEditorGUI");
        frame.setContentPane(new MarkdownEditorGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public MarkdownEditorGUI()
    {
        String html="<html><head><title>Simple Page</title></head>"; html+="<body bgcolor='#777779'><hr/><font size=50>This is Html content</font><hr/>"; html+="</body></html>";


        editorPane1.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                super.keyTyped(e);
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
                editorPane2.setContentType("text/html");
                editorPane2.setText(new MarkdownParser(editorPane1.getText()).getTextFormat());
            }
        });
    }
}
