package Manager.MarkdownReader;

// Imported Libraries
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MarkdownReader
{
    public MarkdownReader()
    {

    }

    public String markdownFromText(String text)
    {
        // Variable Definition
        String formatted = text;

        // Code Section

        formatted = formatLinks(text, formatted);

        return formatted;
    }

    /**
     * FormatLinks - Formating Links To HTML
     * @param text - Original Text
     * @param formatted - Formatted Text Until Then
     * @return - After Add Links HTML Tags
     */
    public String formatLinks(String text, String formatted)
    {
        // Variable Definition
        String regex = "(^|\\W)+(\\[(.*?)\\]\\((.*?)\\))($|\\W)+";  // Pattern For Matching [xx](yy)

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        // Code Section

        while (matcher.find())
        {
            // matcher.group(2) -> [text](link)
            // matcher.group(3) -> Text
            // matcher.group(4) -> Link

            formatted = formatted.replace(matcher.group(2),
                    "<a href = \"" + matcher.group(4) + "\">" + matcher.group(3) + "</a>");
        }
        
        return (formatted);
    }
    
    

}
