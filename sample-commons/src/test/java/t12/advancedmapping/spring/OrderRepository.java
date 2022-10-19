package t12.advancedmapping.spring;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Consistency;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;

@Repository
@SuppressWarnings("unchecked")
public interface OrderRepository extends CassandraRepository<Order, OrderPrimaryKey> {

    // find by id
    @Consistency(DefaultConsistencyLevel.LOCAL_ONE)
    Order findByKeyOrderIdAndKeyProductId(UUID orderId, UUID productId);
    
    // Retrieve a Partition
    List<Order> findByKeyOrderId(UUID orderId);
    
    // Retrieve a Partition with a projection on ProductNameAndPrices 
    @Query("SELECT product_name, product_price FROM orders WHERE order_id = :orderId")
    List<ProductNameAndPrice> findProductNamesAndPricesByKeyOrderId(@Param("orderId") UUID orderId);

    // Delete partition
    void deleteByKeyOrderId(UUID orderId);
    
    // Delette by PK
    void deleteByKeyOrderIdAndKeyProductId(UUID orderId, UUID productId);
    
    // Upsert
    Order save(Order order);

}

