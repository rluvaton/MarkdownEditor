package Manager.MarkdownParser;

// Imported Libraries
import java.util.regex.Pattern;
import java.util.regex.Matcher;


// Useful Sites:
// https://regexr.com/

public class MarkdownParser
{
    private String textFormat;

    public MarkdownParser()
    {
        // TODO - Add The Default Style At The Beginning
        this.textFormat = "";
    }

    public MarkdownParser(String text)
    {
        this.textFormat = markdownFromText(text);
    }

    public String markdownFromText(String text)
    {
        // Variable Definition
        String formatted = text;

        // Code Section

        // region Calling Format Functions

        // Format Photos
        formatted = formatPhotos(text, formatted);

        // Format Links
        formatted = formatLinks(text, formatted);

        // Format Heading
        formatted = formatHeading(text, formatted);

        // Format Italic
        formatted = formatItalic(text, formatted);

        // Format Bold
        formatted = formatBold(text, formatted);

        // Format Strike Through
        formatted = formatStrikeThrough(text, formatted);

        // region TODO - Markdown Rules
        // TODO - Lists
        //   TODO - Unordered List           - * at start
        //   TODO - Ordered List             -  number.
        // TODO - CODE
        //   TODO - 1 Line Code              - ``
        //   TODO - Multiline Code           - ``` lang
        // TODO - Tables
        // TODO - Blockquote                 - >
        // TODO - Horizontal Line            - *** or more
        // TODO - Line Breaks                - 2 Spaces
        // TODO - Embedded content           - {content} new line between each {
        // endregion

        // endregion

        return formatted;
    }

    // region Formatting Functions
    /**
     * FormatLinks - Formatting Links To HTML
     * @param text - Original Text
     * @param formatted - Formatted Text Until Then
     * @return - After Add Links HTML Tags
     * @MatchingPattern - XX[text](link)XX
     */
    public String formatLinks(String text, String formatted)
    {
        // Variable Definition
        String regex = "\\[(.*?)\\]\\((.*?)\\)";  // Pattern For Matching [xx](yy)

        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);

        // Code Section

        while (matcher.find())
        {
            // matcher.group(0) -> [text](link)
            // matcher.group(1) -> Text
            // matcher.group(2) -> Link

            formatted = formatted.replace(matcher.group(0),
                                "<a href = \"" + matcher.group(2) + "\">" +
                                                           matcher.group(1) +
                                          "</a>");
        }
        
        return (formatted);
    }

    /**
     * FormatLinks - Formatting Photos To HTML
     * @param text - Original Text
     * @param formatted - Formatted Text Until Then
     * @return - After Add Photos HTML Tags
     * @MatchingPattern - XX![text](link)XX
     */
    public String formatPhotos(String text, String formatted)
    {
        // Variable Definition
        String regex = "!\\[(.*?)\\]\\((.*?)\\)";  // Pattern For Matching [xx](yy)

        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);

        // Code Section

        while (matcher.find())
        {
            // matcher.group(0) -> [text](link)
            // matcher.group(1) -> Text
            // matcher.group(2) -> Link

            formatted = formatted.replace(matcher.group(0),
                    "<img src = \"" + matcher.group(2) + "\" alt = \"" + matcher.group(1) +"\">");
        }

        return (formatted);
    }

    /***
     * formatHeading - Formatting Heading To HTML
     * @param text - Original Text
     * @param formatted - Formatted Text Until Then
     * @return - After Add Heading HTML Tags
     * @MatchingPattern - # - ###### text
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

    /**
     * formatItalic - Formatting Italic To HTML
     * @param text - Original Text
     * @param formatted - Formatted Text Until Then
     * @return - After Add Italic HTML Tags
     * @MatchingPattern - _xx_
     */
    public String formatItalic(String text, String formatted)
    {
        // Variable Definition
        String regex   = "(?m)_(.*)_"; // Pattern For Matching _text_

        // region Explain The Regex
        /*
         * (?m)   - For multiline
         * _(.*)_ - In group to get the text inside the _
         */
        // endregion

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        // Code Section

        while (matcher.find())
        {
            // matcher.group(0) -> The Whole String Found
            // matcher.group(1) -> The Text Between The '_' Sign

            formatted = formatted.replace(matcher.group(0), "<i>" + matcher.group(1) + "</i>");
        }

        return (formatted);
    }

    /**
     * formatBold - Formatting Bold To HTML
     * @param text - Original Text
     * @param formatted - Formatted Text Until Then
     * @return - After Add Bold HTML Tags
     * @MatchingPattern - **xx**
     */
    public String formatBold(String text, String formatted)
    {
        // Variable Definition
        String regex   = "(?m)\\*\\*(.*)\\*\\*"; // Pattern For Matching **text**

        // region Explain The Regex
        /*
         * (?m)         - For multiline
         * \*\*(.*)\*\* - Get The text Between The ** Must Be Between **xx**
         */
        // endregion

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        // Code Section

        while (matcher.find())
        {
            // matcher.group(0) -> The Whole String Found
            // matcher.group(1) -> The Text Between The '**' Sign

            formatted = formatted.replace(matcher.group(0), "<b>" + matcher.group(1) + "</b>");
        }

        //System.out.println(formatted);

        return (formatted);
    }

    /**
     * formatBold - Formatting Bold To HTML
     * @param text - Original Text
     * @param formatted - Formatted Text Until Then
     * @return - After Add Bold HTML Tags
     * @MatchingPattern - **xx**
     */
    public String formatStrikeThrough(String text, String formatted)
    {
        // Variable Definition
        String regex   = "(?m)~~(.*)~~"; // Pattern For Matching **text**

        // region Explain The Regex
        /*
         * (?m)     - For multiline
         * ~~(.*)~~ - Get The text Between The ~~ Must Be Between ~~xx~~
         */
        // endregion

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        // Code Section

        while (matcher.find())
        {
            // matcher.group(0) -> The Whole String Found
            // matcher.group(1) -> The Text Between The '~~' Sign

            formatted = formatted.replace(matcher.group(0), "<del>" + matcher.group(1) + "</del>");
        }

        return (formatted);
    }

    // endregion

    // region Get & Set

    public String getTextFormat()
    {
        return textFormat;
    }

    public void setTextFormat(String textFormat)
    {
        this.textFormat = textFormat;
    }

    // endregion
}
