package t12.advancedmapping.spring;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * CREATE TABLE IF NOT EXISTS orders(
 * 
 *    
 * 
 * Class to TODO
 *
 * @author Cedrick LUNVEN (@clunven)
 *
 */
@Table(value = "orders")
public class Order implements Serializable {

    /** Serial. */
    private static final long serialVersionUID = 7477768303776564046L;

    @PrimaryKey
    private OrderPrimaryKey key;

    @Column("product_quantity")
    @CassandraType(type = CassandraType.Name.INT)
    private Integer productQuantity;

    @Column("product_name")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String productName;

    @CassandraType(type = CassandraType.Name.DECIMAL)
    @Column("product_price")
    private Float productPrice;

    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    @Column("added_to_order_at")
    private Instant addedToOrderTimestamp;

    public Order(OrderPrimaryKey key, String productName, Integer productQuantity, Float productPrice, Instant addedToOrderTimestamp){
        this.key = key;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.addedToOrderTimestamp = addedToOrderTimestamp;
    }

    /**
     * Getter accessor for attribute 'key'.
     *
     * @return
     *       current value of 'key'
     */
    public OrderPrimaryKey getKey() {
        return key;
    }

    /**
     * Setter accessor for attribute 'key'.
     * @param key
     * 		new value for 'key '
     */
    public void setKey(OrderPrimaryKey key) {
        this.key = key;
    }

    /**
     * Getter accessor for attribute 'productQuantity'.
     *
     * @return
     *       current value of 'productQuantity'
     */
    public Integer getProductQuantity() {
        return productQuantity;
    }

    /**
     * Setter accessor for attribute 'productQuantity'.
     * @param productQuantity
     * 		new value for 'productQuantity '
     */
    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    /**
     * Getter accessor for attribute 'productName'.
     *
     * @return
     *       current value of 'productName'
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Setter accessor for attribute 'productName'.
     * @param productName
     * 		new value for 'productName '
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Getter accessor for attribute 'productPrice'.
     *
     * @return
     *       current value of 'productPrice'
     */
    public Float getProductPrice() {
        return productPrice;
    }

    /**
     * Setter accessor for attribute 'productPrice'.
     * @param productPrice
     * 		new value for 'productPrice '
     */
    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * Getter accessor for attribute 'addedToOrderTimestamp'.
     *
     * @return
     *       current value of 'addedToOrderTimestamp'
     */
    public Instant getAddedToOrderTimestamp() {
        return addedToOrderTimestamp;
    }

    /**
     * Setter accessor for attribute 'addedToOrderTimestamp'.
     * @param addedToOrderTimestamp
     * 		new value for 'addedToOrderTimestamp '
     */
    public void setAddedToOrderTimestamp(Instant addedToOrderTimestamp) {
        this.addedToOrderTimestamp = addedToOrderTimestamp;
    }
    
    
}
