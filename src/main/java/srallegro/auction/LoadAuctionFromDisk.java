package srallegro.auction;

import srallegro.exception.AuctionPriceIsBelowZeroOrZeroException;
import srallegro.exception.EmptyDescriptionException;
import srallegro.exception.EmptyTitleException;
import srallegro.user.LoadUserFromDisk;

public class LoadAuctionFromDisk {
    private static LoadAuctionFromDisk instance;

    private LoadAuctionFromDisk()   {
    }

    private static LoadAuctionFromDisk getInstance(){
        if (instance == null){
            instance = new LoadAuctionFromDisk();
        }
        return instance;
    }
    private static final String COMMA_SEPARATOR = ",";
    private static final int AUCTION_TITLE = 0;
    private static final int AUCTION_DESCRIPTION = 1;
    private static final int AUCTION_CATEGORY = 2;
    private static final int AUCTION_SELLER = 3;
    private static final int AUCTION_WINNER = 4;
    private static final int AUCTION_PRICE = 5;
    private static final int AUCTION_NUMBER = 6;
    private static final int AUCTION_BID = 6;

}
