package srallegro.auction;

import java.io.FileWriter;
import java.io.IOException;

public class SaveAuctionOnDisk {
    private static final String COMMA_SEPARATOR = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String PARAMETERS = "currentUserLogin,title,description,categoryName,amount";
    private static SaveAuctionOnDisk instance;

    private SaveAuctionOnDisk() {
    }

    private static SaveAuctionOnDisk getInstance() {
        if (instance == null) {
            instance = new SaveAuctionOnDisk();
        }
        return instance;
    }

    public static void writeCsvFile(String filename, Auction auction) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filename);
            fileWriter.append(PARAMETERS.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);

            fileWriter.append(auction.getSeller().getNick());
            fileWriter.append(COMMA_SEPARATOR);
            fileWriter.append(auction.getTitle());
            fileWriter.append(COMMA_SEPARATOR);
            fileWriter.append(auction.getDescription());
            fileWriter.append(COMMA_SEPARATOR);
            fileWriter.append(auction.getCategory().toString());
            fileWriter.append(COMMA_SEPARATOR);
            fileWriter.append(String.valueOf(auction.getPrice()));

            fileWriter.append(NEW_LINE_SEPARATOR);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
