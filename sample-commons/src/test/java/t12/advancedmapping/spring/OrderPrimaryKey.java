package t12.advancedmapping.spring;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

@PrimaryKeyClass
public class OrderPrimaryKey implements Serializable {

    private static final long serialVersionUID = -1604320163930020908L;

    @PrimaryKeyColumn(name = "order_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID orderId;

    @PrimaryKeyColumn(name = "product_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private UUID productId;

    public OrderPrimaryKey(UUID orderId, UUID productId){
        this.orderId = orderId;
        this.productId = productId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public UUID getProductId() {
        return productId;
    }
}
