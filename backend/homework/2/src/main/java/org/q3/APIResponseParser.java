package org.q3;

public class APIResponseParser {
    public static void bookParser(String response) {

        Book book = new Book();

        String title = bookParserXml(response, "title");
        book.setTitle(title);

        String[] authorNameArray={"author","name"};
        String author= bookParserXml(response, authorNameArray);
        book.setAuthor(author);

        String imageUrl= bookParserXml(response, "image_url");
        book.setImageUrl(imageUrl);

        String publicationYearvalue=bookParserXml(response,"original_publication_year");
        int publicationYear=Integer.parseInt(publicationYearvalue);
        book.setPublicationYear(publicationYear);

        String averageRatingvalue=bookParserXml(response,"average_rating");
        double averageValue=Double.parseDouble(averageRatingvalue);
        book.setAverageRating(averageValue);

        String ratingsCountValueOrignal=bookParserXml(response,"ratings_count");
        String ratingsCountValueNew=ratingsCountValueOrignal.replaceAll(",", "");
        int ratingsCount=Integer.parseInt(ratingsCountValueNew);
        book.setRatingsCount(ratingsCount);

        book.printValues();
    }
    public static String bookParserXml(String response,String tag){
        String startingTag="<"+tag;
        String endingTag="</"+tag+">";

        int startingIndexOfTag=response.indexOf(startingTag);
        int endingIndexOfTag=response.indexOf(endingTag,startingIndexOfTag);
        if(startingIndexOfTag!=-1 && endingIndexOfTag!=-1){
            int startingIndexOfValue=startingIndexOfTag+tag.length();

            for(int i=startingIndexOfValue;i<endingIndexOfTag;i++) {
                if(response.charAt(i)=='>') {
                    startingIndexOfValue=i+1;
                    break;
                }
            }

            String value=response.substring(startingIndexOfValue,endingIndexOfTag);
            return value;
        }
        return null;
    }
    public static String bookParserXml(String response,String []tags){

        String parentStartTag="<"+tags[0];
        String parentEndingTag="</"+tags[0]+">";

        int startingIndexOfParentTag=response.indexOf(parentStartTag);
        int endingIndexOfParentTag=response.indexOf(parentEndingTag,startingIndexOfParentTag);
        if(startingIndexOfParentTag!=-1 && endingIndexOfParentTag!=-1){
            String childStartTag="<"+tags[1];
            String childEndTag="</"+tags[1]+">";

            int startingIndexOfChild=response.indexOf(childStartTag);
            int endingIndexOfChild=response.indexOf(childEndTag,startingIndexOfChild);

            if(startingIndexOfChild!=-1 && endingIndexOfChild!=-1) {
                for(int i=startingIndexOfChild;i<endingIndexOfChild;i++) {
                    if(response.charAt(i)=='>') {
                        startingIndexOfChild=i+1;
                        break;
                    }
                }
                String value=response.substring(startingIndexOfChild,endingIndexOfChild);
                return value;
            }
        }
        return null;
    }



    public static void main(String[] args) {
        String response = "<work>" +
                "<id type=\"integer\">2361393</id>" + "<books_count type=\"integer\">813</books_count>" +
                "<ratings_count type=\"integer\">1,16,315</ratings_count>" +
                "<text_reviews_count type=\"integer\">3439</text_reviews_count>" +
                "<original_publication_year type=\"integer\">1854</original_publication_year>" +
                "<original_publication_month type=\"integer\" nil=\"true\"/>" +
                "<original_publication_day type=\"integer\" nil=\"true\"/>" +
                "<average_rating>3.79</average_rating>" + "<best_book type=\"Book\">" +
                "<id type=\"integer\">16902</id>" + "<title>Walden</title>" + "<author>" +
                "<id type=\"integer\">10264</id>" + "<name>Henry David Thoreau</name>" + "</author>" +
                "<image_url>" + "http://images.gr-assets.com/books/1465675526m/16902.jpg" + "</image_url>" +
                "<small_image_url>" + "http://images.gr-assets.com/books/1465675526s/16902.jpg" +
                "</small_image_url>" + "</best_book>" + "</work>";
        bookParser(response);
    }
}

