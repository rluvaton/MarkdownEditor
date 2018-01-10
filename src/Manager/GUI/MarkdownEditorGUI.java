package Manager.GUI;

import Manager.MarkdownParser.MarkdownParser;

import javax.swing.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// For GitHub Theme
// Link: https://gist.github.com/rluvaton/8b0145ad3670d1878b35008d06be24c9

public class MarkdownEditorGUI
{
    private JPanel panel1;
    private JEditorPane editor;
    private JEditorPane preview;
    private JTabbedPane tabbedPane1;
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

        preview.setContentType("text/html");
        Theme theme = new Theme(2);

        editor.addKeyListener(new KeyAdapter()
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

                // TODO - Check if the key is markdown character
                preview.setText(theme.MarkdownParser(editor.getText()));

            }
        });
        editor.addInputMethodListener(new InputMethodListener()
        {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event)
            {
                // TODO Parse Again
            }

            @Override
            public void caretPositionChanged(InputMethodEvent event)
            {

            }
        });
    }
}
