import java.math.BigDecimal;

public class Auction {

    private String title;
    private String description;
    private Category category;
    private User seller;
    private User winner;
    private BigDecimal price;

    public Auction(String title, String description, Category category, User seller, User winner, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.seller = seller;
        this.winner = winner;
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", seller=" + seller +
                ", winner=" + winner +
                ", price=" + price +
                '}';
    }
}

