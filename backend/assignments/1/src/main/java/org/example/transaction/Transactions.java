package org.example.transaction;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Transactions {
    private String type;
    private Data data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String coin;
        private Long quantity= 0L;
        private String walletAddress;
        private Double price;
        private Double volume;

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public Long getQuantity() {
            return quantity;
        }

        public void setQuantity(Long quantity) {
            this.quantity = quantity;
        }

        public String getWalletAddress() {
            return walletAddress;
        }
        @JsonProperty("wallet_address")
        public void setWalletAddress(String walletAddress) {
            this.walletAddress = walletAddress;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Double getVolume() {
            return volume;
        }

        public void setVolume(Double volume) {
            this.volume = volume;
        }
    }
}
