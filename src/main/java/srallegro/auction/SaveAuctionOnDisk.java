package srallegro.auction;

import srallegro.user.Database;

import java.io.*;

public class SaveAuctionOnDisk {
    private static final String COMMA_SEPARATOR = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
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
            fileWriter = new FileWriter(filename, true);

            fileWriter.append(auction.getTitle());
            fileWriter.append(COMMA_SEPARATOR);
            fileWriter.append(auction.getDescription());
            fileWriter.append(COMMA_SEPARATOR);
            fileWriter.append(String.valueOf(auction.getCategory()));
            fileWriter.append(COMMA_SEPARATOR);
            fileWriter.append(auction.getSeller().getNick());
            fileWriter.append(COMMA_SEPARATOR);
            fileWriter.append(auction.getWinner().getNick());
            fileWriter.append(COMMA_SEPARATOR);
            fileWriter.append(String.valueOf(auction.getPrice()));
            fileWriter.append(COMMA_SEPARATOR);
            fileWriter.append(String.valueOf(auction.getAuctionNumber()));
            fileWriter.append(COMMA_SEPARATOR);
            fileWriter.append(String.valueOf(auction.getBids()));
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
