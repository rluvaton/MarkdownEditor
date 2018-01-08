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
        System.out.println(formatPhotos("  ![dscd](DDS)"));
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

        // TODO - Don't Check Between Code Tags

        // Format Horizontal Line
        formatted = formatHorLine(formatted);

        // Format Photos
        formatted = formatPhotos(formatted);

        // Format Links
        formatted = formatLinks(formatted);

        // Format Heading
        formatted = formatHeading(formatted);

        // Format Italic
        formatted = formatItalic(formatted);

        // Format Bold
        formatted = formatBold(formatted);

        // Format Strike Through
        formatted = formatStrikeThrough(formatted);


        // region TODO - Markdown Rules
        // TODO - Lists
        //   TODO - Unordered List           - * at start
        //   TODO - Ordered List             -  number.
        // TODO - CODE
        //   TODO - 1 Line Code              - ``
        //   TODO - Multiline Code           - ``` lang - REGEX FOR GETTING CODE: ALMOST (?:\`\`\`(.*)\n(\n*.*\n*)\n+\`\`\`)
        // TODO - Tables
        // TODO - Blockquote                 - >
        // TODO - Line Breaks                - 2 Spaces
        // TODO - Embedded content           - {content} new line between each {
        // endregion

        // endregion

        return formatted;
    }

    // region Formatting Functions
    /**
     * FormatLinks - Formatting Links To HTML
     * @param formatted - Formatted Text Until Then
     * @return - After Add Links HTML Tags
     * @MatchingPattern - XX[text](link)XX
     */
    public String formatLinks(String formatted)
    {
        // Variable Definition
        String regex = "\\[(.*?)\\]\\((.*?)\\)";  // Pattern For Matching [xx](yy)

        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(formatted);

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
     * @param formatted - Formatted Text Until Then
     * @return - After Add Photos HTML Tags
     * @MatchingPattern - XX![text](link)XX
     */
    public String formatPhotos(String formatted)
    {
        // Code Section

        return (formatted.replaceAll("(?m)!\\[(.*?)\\]\\((.*?)\\)",
                                "<img src = \"$2\" alt = \"$1\">"));
    }

    /***
     * formatHeading - Formatting Heading To HTML
     * @param formatted - Formatted Text Until Then
     * @return - After Add Heading HTML Tags
     * @MatchingPattern - # - ###### text
     */
    public String formatHeading(String formatted)
    {
        // Variable Definition
        String regex   = "(?m)^([ ]*)(#{1,6})\\s([ ]*.*)"; // Pattern For Matching # text

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

        Matcher matcher = pattern.matcher(formatted);

        // Code Section

        while (matcher.find())
        {
            // matcher.group(0) -> The Whole String Found
            // matcher.group(1) -> The Spaces Before The '#' Sign
            // matcher.group(2) -> The '#' signs
            // matcher.group(3) -> The Text


            formatted = formatted.replace(matcher.group(0),
                               matcher.group(1) +
                                        "<h" + matcher.group(2).length() + ">" +
                                                matcher.group(3) +
                                        "</h" + matcher.group(2).length() + ">");
        }

        return (formatted);
    }

    /**
     * formatItalic - Formatting Italic To HTML
     * @param formatted - Formatted Text Until Then
     * @return - After Add Italic HTML Tags
     * @MatchingPattern - _xx_
     */
    public String formatItalic(String formatted)
    {
        // Code Section

        // region Explain The Regex
        /*
         * (?m)   - For multiline
         * _(.*)_ - In group to get the text inside the _
         */
        // endregion

        return ((formatted.replaceAll("(?m)_(.*)_", "<i>$1</i>")));
    }

    /**
     * formatBold - Formatting Bold To HTML
     * @param formatted - Formatted Text Until Then
     * @return - After Add Bold HTML Tags
     * @MatchingPattern - **xx**
     */
    public String formatBold(String formatted)
    {
        // Code Section


        // region Explain The Regex
        /*
         * (?m)         - For multiline
         * \*\*(.*)\*\* - Get The text Between The ** Must Be Between **xx**
         */
        // endregion

        return (formatted.replaceAll("(?m)\\*\\*(.*)\\*\\*", "<b>$1</b>"));
    }

    /**
     * formatBold - Formatting Strike Through To HTML
     * @param formatted - Formatted Text Until Then
     * @return - After Add Strike Through HTML Tags
     * @MatchingPattern - ~~xx~~
     */
    public String formatStrikeThrough(String formatted)
    {
        // Code Section

        // region Explain The Regex
        /*
         * (?m)     - For multiline
         * ~~(.*)~~ - Get The text Between The ~~ Must Be Between ~~xx~~
         */
        // endregion

        return (formatted.replaceAll("(?m)~~(.*)~~", "<del>$1</del>"));
    }


    /**
     * formatBold - Formatting Horizontal Line To HTML
     * @param formatted - Formatted Text Until Then
     * @return - After Add Horizontal Line HTML Tags
     * @MatchingPattern - ***+
     */
    public String formatHorLine(String formatted)
    {
        // Code Section

        // region Explain The Regex
        /*
         * (?m)     - For multiline
         * ~~(.*)~~ - Get The text Between The ~~ Must Be Between ~~xx~~
         */
        // endregion

        return (formatted.replaceAll("(?m)^\\*{3,}$", "<hr>"));
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
