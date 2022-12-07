package edu.montana.csci.csci440.model;

import edu.montana.csci.csci440.util.DB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceItem extends Model {

    Long invoiceLineId;
    Long invoiceId;
    Long trackId;
    BigDecimal unitPrice;
    Long quantity;

    String artistName;

    public InvoiceItem(ResultSet results) throws SQLException {
        invoiceLineId = results.getLong("InvoiceLineId");
        invoiceId = results.getLong("InvoiceId");
        trackId = results.getLong("TrackId");
        unitPrice = results.getBigDecimal("UnitPrice");
        quantity = results.getLong("Quantity");
        artistName=getTrack().getArtistName();
        System.out.println(artistName);
    }


    public Track getTrack() {

        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tracks WHERE TrackId=?")) {
            stmt.setLong(1, trackId);
            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                return new Track(results);
            } else {
                return null;
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }
    public Invoice getInvoice() {
        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM invoices WHERE invoiceId=?")) {
            stmt.setLong(1, invoiceId);
            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                return new Invoice(results);
            } else {
                return null;
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    public Long getInvoiceLineId() {
        return invoiceLineId;
    }

    public void setInvoiceLineId(Long invoiceLineId) {
        this.invoiceLineId = invoiceLineId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
