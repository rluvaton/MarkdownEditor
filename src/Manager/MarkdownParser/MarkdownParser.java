package Manager.MarkdownParser;

// Imported Libraries
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MarkdownParser
{
    public MarkdownParser()
    {
        markdownFromText("");
    }

    public String markdownFromText(String text)
    {
        // Variable Definition
        String formatted = text;

        // Code Section

        formatted = formatLinks(text, formatted);
        formatted = formatHeading(text, formatted);

        return formatted;
    }

    /**
     * FormatLinks - Formatting Links To HTML
     * @param text - Original Text
     * @param formatted - Formatted Text Until Then
     * @return - After Add Links HTML Tags
     * @MatchingPattern - XX [text](link) XX
     */
    public String formatLinks(String text, String formatted)
    {
        // Variable Definition
        String regex = "(^|\\W|^\\.)+(\\[(.*?)\\]\\((.*?)\\))($|\\W)+";  // Pattern For Matching [xx](yy)

        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);

        // Code Section

        while (matcher.find())
        {
            // matcher.group(2) -> [text](link)
            // matcher.group(3) -> Text
            // matcher.group(4) -> Link

            formatted = formatted.replace(matcher.group(2),
                                "<a href = \"" + matcher.group(4) + "\">" +
                                                           matcher.group(3) +
                                          "</a>");
        }
        
        return (formatted);
    }

    /***
     * formatHeading - Formatting Heading To HTML
     * @param text - Original Text
     * @param formatted - Formatted Text Until Then
     * @return - After Add Heading HTML Tags
     * @MatchingPattern - ###### - # text
     * @UsefulSite - https://regexr.com/
     */
    public String formatHeading(String text, String formatted)
    {
        // Variable Definition
        String regex   = "(?m)^\\s*(#{1,6})\\s(\\s*)(.*)"; // Pattern For Matching # text

        // region Explain The Regex
        /*
         * (?m)     - For multiline
         * ^        - Start of The Line
         * \s*      - 0 or more spaces
         * (#{1,6)) - In group to get the '#' and only between 1 - 6 '#'
         * \s       - 1 Space Required
         * (\s*)    - In Group To get The All The Spaces Between The '#' and the text and 0 or more spaces
         * (.*)     - Get The content Of The Rest Of The Line
         */
        // endregion

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        // Code Section

        while (matcher.find())
        {
            // matcher.group(0) -> The Whole String Found
            // matcher.group(1) -> The '#' Signs
            // matcher.group(2) -> The ' ' (Space) Signs
            // matcher.group(3) -> The Text


            formatted = formatted.replace(matcher.group(1) + " " + matcher.group(2) + matcher.group(3),
                               "<h" + matcher.group(1).length() + ">" +
                                                matcher.group(2) + matcher.group(3) +
                                        "</h" + matcher.group(1).length() + ">");
        }

        return (formatted);
    }
    

}
