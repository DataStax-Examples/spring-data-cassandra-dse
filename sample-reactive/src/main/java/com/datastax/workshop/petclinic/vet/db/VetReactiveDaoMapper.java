package com.datastax.workshop.petclinic.vet.db;

import static com.datastax.oss.driver.api.querybuilder.SchemaBuilder.createIndex;
import static com.datastax.oss.driver.api.querybuilder.SchemaBuilder.createTable;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

/**
 * DataStax Java driver mapper for Vets. The mapper generates the 
 * boilerplate to execute queries and convert the results into 
 * application-level objects.
 * 
 * Documentation of the mapper:
 * - {@link https://docs.datastax.com/en/developer/java-driver/4.9/manual/mapper/}
 */
@Mapper
public interface VetReactiveDaoMapper extends VetTableDefinition{
    
    @DaoFactory
    VetReactiveDao vetDao(@DaoKeyspace CqlIdentifier keyspace);
    
    /**
     * Create objects required for this business domain (tables, index, udt) if they do not exist.
     */
    default void createSchema(CqlSession cqlSession) {
        
        cqlSession.execute(
                createTable(TABLE_NAME).ifNotExists()
                .withPartitionKey(COLUMN_ID, DataTypes.UUID)
                .withColumn(COLUMN_FIRST_NAME, DataTypes.TEXT)
                .withColumn(COLUMN_LAST_NAME, DataTypes.TEXT)
                .withColumn(COLUMN_SPECIALTIES, DataTypes.setOf(DataTypes.TEXT))
                .build());
        
        cqlSession.execute( 
                createIndex(INDEX_ON_NAME).ifNotExists()
                .onTable(TABLE_NAME)
                .andColumn(COLUMN_LAST_NAME)
                .build());
    }
     
}
