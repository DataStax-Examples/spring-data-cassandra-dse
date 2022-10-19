package com.datastax.workshop.petclinic.owner.db;


import static com.datastax.oss.driver.api.querybuilder.SchemaBuilder.createIndex;
import static com.datastax.oss.driver.api.querybuilder.SchemaBuilder.createTable;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

/**
 * DataStax Java driver mapper for Owner. The mapper generates the 
 * boilerplate to execute queries and convert the results into 
 * application-level objects.
 * 
 * Documentation of the mapper:
 * - {@link https://docs.datastax.com/en/developer/java-driver/4.9/manual/mapper/}
 */
@Mapper
public interface OwnerReactiveDaoMapper extends OwnerTableDefinition {

    @DaoFactory
    OwnerReactiveDao ownerDao(@DaoKeyspace CqlIdentifier keyspace);
     
    /**
     * Create objects required for this business domain (tables, index, udt) if they do not exist.
     */
    default void createSchema(CqlSession cqlSession) {
        
        /**
         * CREATE TABLE IF NOT EXISTS petclinic_owner (
         *      id         uuid,
         *      first_name text,
         *      last_name  text,
         *      address    text,
         *      city       text,
         *      telephone  text,
         *      PRIMARY KEY ((id))
         *); */
        cqlSession.execute(createTable(TABLE_NAME).ifNotExists()
                .withPartitionKey(COLUMN_ID, DataTypes.UUID)
                .withColumn(COLUMN_FIRSTNAME, DataTypes.TEXT)
                .withColumn(COLUMN_LASTNAME, DataTypes.TEXT)
                .withColumn(COLUMN_ADDRESS, DataTypes.TEXT)
                .withColumn(COLUMN_CITY, DataTypes.TEXT)
                .withColumn(COLUMN_TELEPHONE, DataTypes.TEXT)
                .build());
        
        cqlSession.execute(createIndex(INDEX_ON_NAME).ifNotExists()
                .onTable(TABLE_NAME)
                .andColumn(COLUMN_LASTNAME)
                .build());
    }
}
